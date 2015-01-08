package com.softserve.edu.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softserve.edu.model.Conversation;
import com.softserve.edu.model.Gender;
import com.softserve.edu.model.Hosting;
import com.softserve.edu.service.ConversationService;

@Controller
public class ConversationController {

    @Autowired
    private ConversationService conversationService;

    @RequestMapping(value = "/conversations", method = RequestMethod.GET)
    public String hostingCreationShow(Model model) {
	Set<Conversation> conversations = conversationService.findAll();
	System.out.println(conversations.size());
	model.addAttribute("conversations", conversations);
	return "conversations";
    }

}
