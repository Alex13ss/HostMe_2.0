package com.softserve.edu.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.softserve.edu.dto.ConversationDto;
import com.softserve.edu.dto.GroupDto;
import com.softserve.edu.model.Group;
import com.softserve.edu.model.User;
import com.softserve.edu.service.ConversationService;
import com.softserve.edu.service.GroupService;
import com.softserve.edu.service.NotificationService;
import com.softserve.edu.service.ProfileService;
import com.softserve.edu.service.UserService;

@Controller
public class GroupController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private ConversationService conversationService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private UserService userService;

    @Autowired
    private NotificationService notificationService;

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
        return "redirect:/group?id={id}";
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
    public @ResponseBody Set<GroupDto> findApprovedGroups() {
        Set<GroupDto> groups = groupService.findApprovedGroups();
        return groups;
    }

    @RequestMapping(value = "/my-groups", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Set<GroupDto> findMyGroups(Principal principal) {
        User creatorUser = profileService.getUserByLogin(principal.getName());
        Set<GroupDto> groups = groupService.getGroupsByCreator(creatorUser);
        return groups;
    }

    @RequestMapping(value = "/interesting-groups", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<GroupDto> findSubscribedGroups(Principal principal) {
        User interestedUser = profileService
                .getUserByLogin(principal.getName());
        List<GroupDto> groups = groupService
                .getGroupsByInterestedUser(interestedUser);
        return groups;
    }

    @RequestMapping(value = "/all-groups", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Set<GroupDto> findAll() {
        Set<GroupDto> groups = groupService.findAll();
        return groups;
    }

    @RequestMapping(value = "/pending-groups", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Set<GroupDto> findPendingGroups() {
        Set<GroupDto> groups = groupService.findPendingGroups();
        return groups;
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
        return "redirect:/group?id={id}";
    }

    @RequestMapping("/group/subscribe/{id}")
    public String subscribeGroup(@PathVariable("id") Long groupId,
            Principal principal) {
        Group group = groupService.findOne(groupId);
        User user = profileService.getUserByLogin(principal.getName());
        groupService.subscribe(user, group);
        return "redirect:/group?id={id}";
    }

    @RequestMapping("/group/unsubscribe/{id}")
    public String unsubscribeGroup(@PathVariable("id") Long groupId,
            Principal principal) {
        User user = profileService.getUserByLogin(principal.getName());
        groupService.unsubscribe(user.getUserId(), groupId);
        return "redirect:/group?id={id}";
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
        group.setCreatedAt(newGroup.getCreatedAt());
        group.setCreatorUser(newGroup.getCreatorUser());
        group.setGroupName(newGroup.getGroupName());
        group.setGroupDescription(newGroup.getGroupDescription());
        groupService.saveGroup(group);
        return group;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        String datePattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, true));
    }

}
