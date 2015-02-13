package com.softserve.edu.controller.admin;

import java.util.List;
import java.util.Random;

import com.softserve.edu.model.User;
import com.softserve.edu.service.RoleService;
import com.softserve.edu.service.UserService;
import com.softserve.edu.service.implementation.RegistrationSendMailImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminController {
	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	static final int LEN = 6;
	static Random rnd = new Random();

	String randomString() {
		StringBuilder sb = new StringBuilder(LEN);
		for (int i = 0; i < LEN; i++)
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		return sb.toString();
	}

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

	@RequestMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable("id") Integer id) {
		userService.removeUser(id);
		return "redirect:/usersManager";
	}

	@RequestMapping(value = "/resetPass/{id}")
	public String resetPass(@PathVariable("id") Integer id) {
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
		return "redirect:/usersManager";
	}

	@RequestMapping(value = "/user-update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody User updateUserState(@RequestBody User user) {
		User newUser = userService.getUser(user.getUserId());
		user.setPassword(newUser.getPassword());
		userService.saveUser(user);
		return user;
	}
}
