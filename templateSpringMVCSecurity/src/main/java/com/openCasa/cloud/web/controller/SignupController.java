package com.openCasa.cloud.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.openCasa.cloud.web.model.Account;
import com.openCasa.cloud.web.model.SignupForm;
import com.openCasa.cloud.web.utils.AjaxUtils;
import com.openCasa.cloud.web.utils.MessageHelper;

@Controller
public class SignupController {

    private static final String SIGNUP_VIEW_NAME = "signup/signup";

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "signup")
	public String signup(Model model, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith) {
		model.addAttribute(new SignupForm());
        if (AjaxUtils.isAjaxRequest(requestedWith)) {
            return SIGNUP_VIEW_NAME.concat(" :: signupForm");
        }
        return SIGNUP_VIEW_NAME;
	}
	
	@RequestMapping(value = "signup", method = RequestMethod.POST)
	public String signup(@Valid @ModelAttribute SignupForm signupForm, Errors errors, RedirectAttributes ra) {
		if (errors.hasErrors()) {
			return SIGNUP_VIEW_NAME;
		}
		Account account = accountRepository.save(signupForm.createAccount());
		userService.signin(account);
        // see /WEB-INF/i18n/messages.properties and /WEB-INF/views/homeSignedIn.html
        MessageHelper.addSuccessAttribute(ra, "signup.success");
		return "redirect:/";
	}
}