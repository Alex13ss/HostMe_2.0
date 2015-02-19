package com.softserve.edu.controller.admin;

import java.util.List;

import com.softserve.edu.model.User;
import com.softserve.edu.model.UserState;
import com.softserve.edu.service.RandomPass;
import com.softserve.edu.service.RoleService;
import com.softserve.edu.service.UserService;
import com.softserve.edu.service.implementation.RegistrationSendMailImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AdminController implements RandomPass {


	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;

	@Autowired
	private RegistrationSendMailImpl registrationSendMail;

	@RequestMapping(value = "/usersManager")
	public String userManager(Model model) {
		model.addAttribute("users", userService.getAllUsers());
		return "usersManager";
	}

	@RequestMapping(value = "/all-users", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<User> getAllUsers() {
		List<User> admin= userService.findUsersByNamesOrLogin("Admin");
		List<User> users = userService.getAllUsers();
		users.removeAll(admin);
		return users;
	}
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping("/banUser/{id}")
	public String banUser(@PathVariable("id") Integer id,RedirectAttributes redirectAttributes) {
		User user = userService.getUser(id);
		UserState status = user.getUserState();
		if(status==UserState.ACTIVE){
			status=UserState.INACTIVE;
		}
		else status=UserState.ACTIVE;
		user.setUserState(status);
		userService.updateUser(user);
		redirectAttributes.addFlashAttribute(
                "userBaned", true);
		return "redirect:/usersManager";
	}
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/resetPass/{id}")
	public String resetPass(@PathVariable("id") Integer id,RedirectAttributes redirectAttributes) {
		String newPass = randomString();
		User user = userService.getUser(id);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(newPass));
		userService.updateUser(user);
		Thread mailSenderThread = new Thread(new Runnable() {
			public void run() {
				registrationSendMail.sendNewPassMail(user, newPass);
			}
		});
		mailSenderThread.start();
		redirectAttributes.addFlashAttribute(
                "passReset", true);
		return "redirect:/usersManager";
	}

	@RequestMapping(value = "/user-update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody User updateUserState(@RequestBody User user) {
		User newUser = userService.getUser(user.getUserId());
		user.setPassword(newUser.getPassword());
		userService.updateUser(user);
		return user;
	}
}
