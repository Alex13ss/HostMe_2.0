package com.softserve.edu.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.softserve.edu.dto.ConversationDto;
import com.softserve.edu.dto.GroupDto;
import com.softserve.edu.model.Group;
import com.softserve.edu.model.User;
import com.softserve.edu.service.ConversationService;
import com.softserve.edu.service.GroupService;
import com.softserve.edu.service.ImageService;
import com.softserve.edu.service.NotificationService;
import com.softserve.edu.service.ProfileService;

/**
 * @author Oleksandr Bandurka
 */
@Controller
public class GroupController {

    private static final String GROUP_LINK = "redirect:/group?id={id}";

    @Autowired
    private GroupService groupService;

    @Autowired
    private ConversationService conversationService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private ImageService imageService;

    @ModelAttribute("group")
    public Group constructGroup() {
        return new Group();
    }

    @RequestMapping(value = "/groups", method = RequestMethod.POST)
    public String doAddGroup(Model model,
            @ModelAttribute("group") @Valid final Group group,
            final BindingResult result, RedirectAttributes redirectAttributes,
            HttpSession httpSession) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.group",
                    result);
            redirectAttributes.addFlashAttribute("group", group);
            redirectAttributes.addFlashAttribute("groupNotCreated", true);
            return "redirect:/groups";
        }
        groupService.create(group);
        redirectAttributes.addAttribute("id", group.getId()).addFlashAttribute(
                "groupCreated", true);
        return GROUP_LINK;
    }

    @RequestMapping(value = "/add-group-img", method = RequestMethod.POST)
    public String addGroupPhoto(@RequestParam("file") MultipartFile file,
            @ModelAttribute("group") final Group group,
            RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("id", group.getId());
        imageService.addImageToGroup(file, group);
        return GROUP_LINK;
    }

    @RequestMapping("/group/remove/{id}")
    public String removeGroup(@PathVariable Long id) {
        Group group = groupService.findOne(id);
        groupService.delete(group);
        return "redirect:/groups";
    }

    @RequestMapping(value = "/groups", method = RequestMethod.GET)
    public String groupsCreationShow() {
        return "groups";
    }

    @RequestMapping(value = "/approved-groups", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<GroupDto> findApprovedGroups(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "size") Integer size,
            @RequestParam(value = "orderBy") String orderBy,
            @RequestParam(value = "orderType") String orderType) {
        return groupService.findApprovedGroups(page, size, orderBy, orderType);
    }

    @RequestMapping(value = "/my-groups", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<GroupDto> findMyGroups(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "size") Integer size,
            @RequestParam(value = "orderBy") String orderBy,
            @RequestParam(value = "orderType") String orderType) {
        User creatorUser = profileService.getCurrentUser();
        return groupService.getGroupsByCreator(creatorUser, page, size,
                orderBy, orderType);
    }

    @RequestMapping(value = "/interesting-groups", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<GroupDto> findSubscribedGroups(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "size") Integer size,
            @RequestParam(value = "orderBy") String orderBy,
            @RequestParam(value = "orderType") String orderType) {
        User interestedUser = profileService.getCurrentUser();
        return groupService.getGroupsByInterestedUser(interestedUser, page,
                size, orderBy, orderType);
    }

    @RequestMapping(value = "/all-groups", params = { "page", "size",
            "orderBy", "orderType" }, method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<GroupDto> findAllGroups(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "size") Integer size,
            @RequestParam(value = "orderBy") String orderBy,
            @RequestParam(value = "orderType") String orderType) {
        return groupService.findAll(page, size, orderBy, orderType);
    }

    @RequestMapping(value = "/pending-groups", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<GroupDto> findPendingGroups(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "size") Integer size,
            @RequestParam(value = "orderBy") String orderBy,
            @RequestParam(value = "orderType") String orderType) {
        return groupService.findPendingGroups(page, size, orderBy, orderType);
    }

    @RequestMapping(value = "/group", method = RequestMethod.GET)
    public String showGroup(@RequestParam("id") long id, Model model) {
        Group group = groupService.findOne(id);
        User user = profileService.getCurrentUser();
        boolean isInterested = groupService.checkInterestedByGroupAndUser(
                group, user);
        boolean isCreator = groupService.checkIsItGroupCreator(group, user);
        model.addAttribute("group", group);
        model.addAttribute("isInterested", isInterested);
        model.addAttribute("isCreator", isCreator);
        model.addAttribute("subscribers",
                groupService.getCurrentSubscribers(id));
        addLatestConversationsByGroupId(model, id);
        return "group";
    }

    @RequestMapping(value = "/group", method = RequestMethod.POST)
    public String editGroup(@ModelAttribute("group") final Group group,
            RedirectAttributes redirectAttributes) {
        Group newGroup = groupService.findOne(group.getId());
        group.setInterestedUsers(newGroup.getInterestedUsers());
        group.setStatus(newGroup.getStatus());
        notificationService.addNotification(group, "Group was edited");
        groupService.update(group);
        redirectAttributes.addAttribute("id", group.getId()).addFlashAttribute(
                "groupEdited", true);
        return GROUP_LINK;
    }

    @RequestMapping("/group/subscribe/{id}")
    public String subscribeGroup(@PathVariable("id") Long groupId) {
        Group group = groupService.findOne(groupId);
        User user = profileService.getCurrentUser();
        if (groupService.checkInterestedByGroupAndUser(group, user)) {
            groupService.unsubscribe(user, group);
        } else {
            groupService.subscribe(user, group);
        }
        return GROUP_LINK;
    }

    private void addLatestConversationsByGroupId(Model model, Long id) {
        List<ConversationDto> conversations = conversationService
                .findLatestConversationsDtoByGroupId(id);
        Long conversationsSize = conversationService.countByGroupId(id);
        model.addAttribute("conversationDtos", conversations);
        model.addAttribute("conversationsSize", conversationsSize);
    }

    @RequestMapping(value = "/group-status-update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Group updateGroupStatus(@RequestBody Group group) {
        Group newGroup = groupService.findOne(group.getId());
        newGroup.setStatus(group.getStatus());
        groupService.saveGroup(newGroup);
        return group;
    }

    @RequestMapping(value = "/groups-paging", params = { "size", "sender" }, method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Long getPaging(
            @RequestParam(value = "size") Long size,
            @RequestParam(value = "sender") String sender) {
        User currentUser = profileService.getCurrentUser();
        return groupService.getGroupsPaging(size, sender, currentUser);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        String datePattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, true));
    }

}
