package de.ciber.comics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * Handles requests for the register page.
 */
@Controller
@SessionAttributes("formBean")
public class RegistrationController {
	
	private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);
	
	@Autowired
	private RegistrationService registrationService;
	
	@ModelAttribute("formBean")
	public RegistrationInfo createFormBean() {
		return new RegistrationForm();
	}
	
	@RequestMapping(value = "/register.html", method = RequestMethod.GET)
	public String register(Model model) {
		model.addAttribute("title", "REGISTER PAGE");
		model.addAttribute("pagename", "register");
		return "home";
	}
	
	@RequestMapping(value = "/register.html", method = RequestMethod.POST)
	public String doRegister(@Valid @ModelAttribute("formBean") RegistrationForm form, BindingResult result, Model model) {
		// custom validation (creating "extra" errors)
		if (!form.getPassword().equals(form.getPasswordrepeat())) {
			result.addError(new FieldError("formBean", "passwordrepeat", form.getPasswordrepeat(), false,
					new String[]{"errors.mismatch.password"}, new String[]{}, "must match password"));
		}
		if (!form.getEmail().equals(form.getEmailrepeat())) {
			result.addError(new FieldError("formBean", "emailrepeat", form.getEmailrepeat(), false,
					new String[]{"errors.mismatch.email"}, new String[]{}, "must match email"));
		}
		
		if (registrationService.doesUsernameExist(form.getUsername())) {
			result.addError(new FieldError("formBean", "username", form.getUsername(), false,
					new String[]{"errors.username.already.exists"}, new String[]{}, "user names must be unique"));
		}
		
		if (result.hasErrors()) {
			logger.debug("VALIDATION ERROR");
			model.addAttribute("pagename", "register");
			return "home";
		}
		
		logger.debug("VALIDATION OK");
		
		// create new user account
		registrationService.registerUser(form);
		
		return "redirect:/login.html"; // TODO redirect to a "account created"-page instead
	}
}
