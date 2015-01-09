package com.softserve.edu.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softserve.edu.model.Group;
import com.softserve.edu.service.GroupService;

@Controller
public class GroupController {
    
//    @Autowired
    private GroupService groupService;
    
    @RequestMapping(value= "/groups", method = RequestMethod.GET)
    public String groupCreationShow(Model model) {
        Set<Group> groups = groupService.findAll();
        System.out.println(groups.size());
        model.addAttribute("groups", groups);
        return "groups";
    }
    
}
