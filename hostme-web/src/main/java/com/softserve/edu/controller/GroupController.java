package com.softserve.edu.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.softserve.edu.dto.ConversationDto;
import com.softserve.edu.model.Group;
import com.softserve.edu.service.ConversationService;
import com.softserve.edu.service.GroupService;

@Controller
public class GroupController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private ConversationService conversationService;

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
    public String groupCreationShow(Model model) {
        Set<Group> groups = groupService.findAll();
        model.addAttribute("groups", groups);
        return "groups";
    }

    @RequestMapping(value = "/group", method = RequestMethod.GET)
    public String showGroups(@RequestParam("id") long id, Model model) {
        Group group = groupService.findOne(id);
        model.addAttribute("group", group);
        addLatestConversationsByGroupId(model, id);
        return "group";
    }

    
    // WARNING
    
    
    @RequestMapping(value = "/group-editing", method = RequestMethod.GET)
    public String groupEditingShow(@RequestParam(value = "id") Long id,
            Model model) {
        Group group = groupService.findOne(id);
        model.addAttribute("group", group);
        return "group-editing";
    }

    @RequestMapping(value = "/group-editing", method = RequestMethod.POST)
    public String editGroup(@ModelAttribute("group") Group group,
            @RequestParam("id") Long id) {
        group.setId(id);
        groupService.update(group);
        return "redirect:/group?id={id}";
    }
    
    
    // WARNING
    

    private void addLatestConversationsByGroupId(Model model, Long id) {
        List<ConversationDto> conversations = conversationService
                .findLatestConversationsDtoByGroupId(id);
        Long conversationsSize = conversationService.countByGroupId(id);
        model.addAttribute("conversationDtos", conversations);
        model.addAttribute("conversationsSize", conversationsSize);
    }

}
