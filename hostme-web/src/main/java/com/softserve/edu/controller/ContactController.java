package com.softserve.edu.controller;

import com.softserve.edu.entity.Contact;
import com.softserve.edu.entity.User;
import com.softserve.edu.service.ContactService;
import com.softserve.edu.service.ImageService;
import com.softserve.edu.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes({"loggedUser", "image_url"})
public class ContactController {
    
    @Autowired
    private ContactService contactService;
    
    @Autowired
    private ProfileService profileService;
    
    @Autowired
    private ImageService imageService;
    
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String listContacts(Model map) {
        User user = new User();
        map.addAttribute("user", user);
        
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentPrincipalName = authentication.getName();
            
            map.addAttribute("loggedUser",
                    profileService.getUserByLogin(currentPrincipalName));
            map.addAttribute("image_url", imageService.getImagePath());
            return "redirect:/profile";
        } else {
            return "index";
        }
    }
    
    @RequestMapping("/")
    public String home() {
        return "redirect:/index";
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addContact(@ModelAttribute("contact") Contact contact,
            BindingResult result) {
        contactService.addContact(contact);
        return "redirect:/index";
    }
    
    @RequestMapping("/logout")
    public String logout() {
        return "index";
        
    }
    
    @RequestMapping("/delete/{contactId}")
    public String deleteContact(@PathVariable("contactId") Integer contactId) {
        contactService.removeContact(contactId);
        return "redirect:/index";
    }
}
