package com.icoding.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.icoding.domain.User;
import com.icoding.service.RoleService;
import com.icoding.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes("student")
public class HomeController extends GenericController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = { "/admin/home", "/admin" }, method = RequestMethod.GET)
	public String adminHome(Locale locale, Model model) {
		model.addAttribute("pageName", "Quản trị người dùng");
		return "home";
	}

	@RequestMapping(value = "/userHome/new", method = RequestMethod.POST)
	@ResponseBody
	public String addUserHome(@RequestParam(value = "password") String password,
			@RequestParam(value = "userName") String userName, @RequestParam(value = "fullname") String fullname,
			@RequestParam(value = "birthDate") String birthDate, @RequestParam(value = "email") String email,
			@RequestParam(value = "address") String address, @RequestParam(value = "phone") String phone,
			@RequestParam(value = "state") String state, @RequestParam(value = "gender") String gender) {
		User user = new User();
		user.setUsername(userName);
		user.setPassword(encoder.encode(password));
		user.setFullName(fullname);
		user.setBirthDate(birthDate);
		user.setAddress(address);
		user.setEmail(email);
		user.setRole(roleService.getRoleUser());
		user.setState(state);
		if (gender.equalsIgnoreCase("true")) {
			user.setGender(true);
		}
		user.setGender(false);
		user.setPhone(phone);
		try {
			userService.saveOrUpdate(user);
			return "true";
		} catch (Exception e) {
			return "false";
		}
	}

}
