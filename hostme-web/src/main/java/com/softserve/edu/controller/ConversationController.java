package com.softserve.edu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.softserve.edu.model.Conversation;
import com.softserve.edu.service.ConversationService;

@Controller
public class ConversationController {

    @Autowired
    private ConversationService conversationService;

    //This controlles created only for test use
    @RequestMapping(value = "/conversations", method = RequestMethod.GET)
    public String conversationsIndex(Model model) {
	List<Conversation> conversations = conversationService.findAll(new PageRequest(0, 5, Direction.DESC, "id"));
	model.addAttribute("conversations", conversations);
	return "conversations";
    }
    
    @RequestMapping(value = "/conversation", method = RequestMethod.GET)
    public String showConversation(@RequestParam("id") long id, Model model) {
	Conversation conversation = conversationService.findOne(id);
	model.addAttribute("conversation", conversation);
	return "conversation";
    }

}
