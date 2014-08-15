package com.openCasa.cloud.web.controller;

import java.security.Principal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	private final static Logger logger = LogManager.getLogger(HomeController.class.getName());
//	private final static Logger LOG = Logger.getLogger(HomeController.class.getName());

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Principal principal) {
		// return principal != null ? "home/homeSignedIn" : "home/homeNotSignedIn";
		if (logger.isDebugEnabled()) {
			logger.debug("Home Controller");
		}
		return "home";
	}

	@RequestMapping("/hello")
	public String hello(@RequestParam(value = "name", required = false, defaultValue = "World")
	String name, Model model) {
		model.addAttribute("name", name);
		return "helloworld";
	}

	@RequestMapping(value = { "/welcome**" }, method = RequestMethod.GET)
	public ModelAndView welcomePage() {
 
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Hello World");
		model.addObject("message", "This is welcome page!");
		model.setViewName("home");
		return model;
 
	}
	
}