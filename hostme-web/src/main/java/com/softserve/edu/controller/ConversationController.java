package com.softserve.edu.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.softserve.edu.dto.ConversationDto;
import com.softserve.edu.model.Conversation;
import com.softserve.edu.service.ConversationService;

@Controller
public class ConversationController {

    @Autowired
    private ConversationService conversationService;

    @RequestMapping(value = "/conversations", method = RequestMethod.GET)
    public String conversationsIndex(@RequestParam("group_id") long id,Model model) {
	List<ConversationDto> conversations = conversationService.findAllConversationsDtoByGroupId(id);
	Long conversationsSize = conversationService.countByGroupId(id);
	model.addAttribute("conversationDtos", conversations);
	model.addAttribute("conversationsSize", conversationsSize);
	return "conversations";
    }
    
    @RequestMapping(value = "/conversation", method = RequestMethod.GET)
    public String showConversation(@RequestParam("id") long id, Model model) {
	Conversation conversation = conversationService.findOne(id);
	model.addAttribute("conversation", conversation);
	return "conversation";
    }

}
