package com.softserve.edu.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softserve.edu.dto.ConversationCreateDto;
import com.softserve.edu.dto.ConversationDto;
import com.softserve.edu.dto.PostDto;
import com.softserve.edu.dto.UserDto;
import com.softserve.edu.model.Conversation;
import com.softserve.edu.model.Group;
import com.softserve.edu.model.Post;
import com.softserve.edu.model.User;
import com.softserve.edu.repositories.ConversationRepository;
import com.softserve.edu.service.ConversationService;
import com.softserve.edu.service.GroupService;
import com.softserve.edu.service.NotificationService;
import com.softserve.edu.service.PostService;
import com.softserve.edu.service.ProfileService;
import com.softserve.edu.service.UserService;

@Controller
public class ConversationController {

    @Autowired
    private ConversationService conversationService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private UserService userService;

    @Autowired
    private NotificationService notificationService;
    
    @Autowired
    private PostService	 postService;

    /**
     * Shows list of all conversations in group with id from request
     * @param id group id
     * @param model
     * @return
     */
    @RequestMapping(value = "/conversations", method = RequestMethod.GET)
    public String conversationsIndex(@RequestParam("group_id") long id, ModelMap model) {
	List<ConversationDto> conversations = conversationService.findAllConversationsDtoByGroupId(id);
	Long conversationsSize = conversationService.countByGroupId(id);
	model.addAttribute("conversationDtos", conversations);
	model.addAttribute("conversationsSize", conversationsSize);
	model.addAttribute("groupId", id);
	return "conversations";
    }

    /**
     * Shows conversation with id from request
     * @param id conversation id
     * @param model
     * @return
     */
    @RequestMapping(value = "/conversation", method = RequestMethod.GET)
    public String showConversation(@RequestParam("id") long id, ModelMap model) {
	Conversation conversation = conversationService.findOne(id);
	model.addAttribute("conversation", conversation);
	return "conversation";
    }

    /**
     * Creates a new instance of conversation from request and persists it to the database
     * @param conversation Conversation from request
     * @param model 
     * @return redirects to newly created conversation 
     */
    @RequestMapping(value = "/conversationCreate", method = RequestMethod.POST)
    public String createConversation(@ModelAttribute("conversation") ConversationCreateDto conversationDto,
            ModelMap model) {
        Conversation conversation = getConversationFromDto(conversationDto);
        notificationService.addNotification(conversation.getGroup(), "Created new conversation");
        return "redirect:/conversation?id=" + conversation.getId();
    }
    
    /**
     * Deletes conversation with id from request. Deletes only if user is the owner 
     * @param id
     * @return
     */
    @RequestMapping("/conversationDelete/{id}")
    public String deleteConversation(@PathVariable Long id) {
	Conversation conversation = conversationService.findOne(id);
	User owner = conversation.getOwner();
	if (profileService.getCurrentUser().getUserId() == owner.getUserId()) {
	    conversationService.delete(conversation);
	}
        return "redirect:/conversations?group_id=" + conversation.getGroup().getId();
    }

    @RequestMapping(value="/findUser.json", method=RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<UserDto> findUserJson(@RequestParam(value = "input") String input) {
	return userService.getUserDtoList(userService.findUsersByNamesOrLogin(input));
    }
    
    
    @RequestMapping(value="/findPosts.json", method=RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<PostDto> findPostsJson(@RequestParam(value = "conversationId") Long id) {
	return postService.findByConversationId(id);
    }
    
    @RequestMapping(value = "/sendPost", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<PostDto> sendPost(
	    @RequestParam(value = "conversationId") Long id,
	    @RequestParam(value = "message") String message) {
	Post post = new Post();
	post.setAuthor(profileService.getCurrentUser());
	post.setContent(message);
	post.setConversation(conversationService.findOne(id));
	post.setPostedAt(Calendar.getInstance());
	postService.save(post);
	return postService.findByConversationId(id);
    }
    
    private Conversation getConversationFromDto(ConversationCreateDto dto) {
	Conversation conversation = new Conversation();
	
	conversation.setTitle(dto.getTitle());
	conversation.setCreatedAt(Calendar.getInstance());
	
	Long groupId = Long.parseLong(dto.getGroupId());
	Group group = groupService.findOne(groupId);
	conversation.setGroup(group);
	
	User currentUser = profileService.getCurrentUser();
	conversation.setOwner(currentUser);
	
	Set<User> moderators = getUsersFromIds(dto.getModeratorLogins());
	conversation.setModerators(moderators);
	
	Set<Post> posts = new HashSet<Post>();
	Post post = new Post();
	post.setContent(dto.getMessage());
	post.setPostedAt(Calendar.getInstance());
	post.setAuthor(currentUser);
	post.setConversation(conversation);
	posts.add(post);
	conversation.setPosts(posts);

	return conversationService.save(conversation);
    }
    
    private Set<User> getUsersFromIds(List<String> ids) {
	Set<User> moderators = new HashSet<User>();
	if (ids != null) {
	    for (String id : ids) {
		if (id != null) {
		    User user = userService.getUser(Integer.parseInt(id));
		    moderators.add(user);
		}
	    }
	}
	return moderators;
    }
}
