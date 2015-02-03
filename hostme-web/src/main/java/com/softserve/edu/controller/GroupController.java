package com.softserve.edu.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping(value = "/all-groups", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Set<GroupDto> findAll() {
        Set<GroupDto> groups = groupService.findAll();
        return groups;
    }

    @RequestMapping(value = "/my-groups", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Set<GroupDto> getMyGroups() {
        User creatorUser = profileService.getUserByLogin(SecurityContextHolder
                .getContext().getAuthentication().getName());
        Set<GroupDto> groups = groupService.getGroupsByCreator(creatorUser);
        return groups;
    }

    @RequestMapping(value = "/interesting-groups", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<GroupDto> getInterestingGroups() {
        User interestedUser = profileService
                .getUserByLogin(SecurityContextHolder.getContext()
                        .getAuthentication().getName());
        return groupService.getGroupsByInterestedUser(interestedUser);
    }

    /**
     * MustTODO!!! ^_^
     */
    @RequestMapping(value = "/updates-of-groups", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<GroupDto> getUpdatedInterestingGroups() {
        User interestedUser = profileService
                .getUserByLogin(SecurityContextHolder.getContext()
                        .getAuthentication().getName());
        return notificationService.findAllGroupsByNotifications(interestedUser);
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
        addLatestConversationsByGroupId(model, id);
        return "group";
    }

    @RequestMapping(value = "/group", method = RequestMethod.POST)
    public String editGroup(@ModelAttribute("group") final Group group,
            RedirectAttributes redirectAttributes) {
        groupService.update(group);
        redirectAttributes.addAttribute("id", group.getId()).addFlashAttribute(
                "groupEdited", true);
        return "redirect:/group?id={id}";
    }

    @RequestMapping("/group/add-to-interesting/{id}")
    public String addToInteresting(@PathVariable("id") Long id) {
        Group group = groupService.findOne(id);
        User user = profileService.getUserByLogin(SecurityContextHolder
                .getContext().getAuthentication().getName());
        groupService.saveInterestedUser(user, group);
        return "redirect:/group?id={id}";
    }

    @RequestMapping("/group/remove-from-interesting/{id}")
    public String removeFromInteresting(@PathVariable("id") Long id) {
        Group group = groupService.findOne(id);
        User user = profileService.getUserByLogin(SecurityContextHolder
                .getContext().getAuthentication().getName());
        groupService.removeInterestingRelationship(user, group);
        return "redirect:/group?id={id}";
    }

    private void addLatestConversationsByGroupId(Model model, Long id) {
        List<ConversationDto> conversations = conversationService
                .findLatestConversationsDtoByGroupId(id);
        Long conversationsSize = conversationService.countByGroupId(id);
        model.addAttribute("conversationDtos", conversations);
        model.addAttribute("conversationsSize", conversationsSize);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        String datePattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, true));
    }

}
