package com.test.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.test.mvc.model.User;

@Controller
public class UserController {

	@RequestMapping(value = "/userForm", method = RequestMethod.GET)
	public String userForm(Model model) {
		model.addAttribute("user", new User());
		return "userForm";
	}

	@RequestMapping(value = "/userForm", method = RequestMethod.POST)
	public String userFormSubmit(@ModelAttribute
	User user, Model model) {
		model.addAttribute("user", user);
		return "userFormResult";
	}

}
