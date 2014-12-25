package com.softserve.edu.controller;

import com.softserve.edu.entity.Feedback;
import com.softserve.edu.entity.Hosting;
import com.softserve.edu.entity.User;
import com.softserve.edu.service.FeedbackService;
import com.softserve.edu.service.HostingService;
import com.softserve.edu.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FeedbacksController {
    @Autowired
    private HostingService hostingService;
    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private ProfileService profileService;
    
    @RequestMapping(value = "/feedbacks", method = RequestMethod.GET)
    public String feedbacksShow(
            @RequestParam(value = "hostingId") int hostingId, Model model) {
        Hosting hosting = hostingService.getHosting(hostingId);
        model.addAttribute("hosting", hosting);
        
        return "feedbacks";
    }
    
    @RequestMapping(value = "/feedbacks-delete", method = RequestMethod.POST)
    public String deleteFeedback(
            @RequestParam(value = "feedbackId") int feedbackId,
            @RequestParam(value = "hostingId") int hostingId, Model model) {
        model.addAttribute("hostingId", hostingId);
        feedbackService.deleteFeedbackById(feedbackId);
        
        return "redirect:/feedbacks";
    }
    
    @RequestMapping(value = "/feedbacks-send", method = RequestMethod.POST)
    public String sendFeedback(@ModelAttribute("feedbackId") Feedback feedback,
            Model model, @RequestParam(value = "hostingId") int hostingId) {
        
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = profileService.getUserByLogin(currentPrincipalName);
        
        Hosting hosting = hostingService.getHosting(hostingId);
        
        feedback.setAuthor(user);
        feedback.setHosting(hosting);
        
        feedbackService.createFeedback(feedback);
        hosting.addFeedback(feedback);
        
        model.addAttribute("hosting", hosting);
        
        return "feedbacks";
    }
    
}
