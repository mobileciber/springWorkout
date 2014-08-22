package de.ciber.comics.view.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import de.ciber.comics.RegistrationInfo;
import de.ciber.comics.RegistrationService;
import de.ciber.comics.view.model.RegistrationForm;

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
	public String doRegister(@Valid @ModelAttribute("formBean") RegistrationForm form, BindingResult result, Model model, HttpServletRequest request) {
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
		registrationService.registerUser(form, request.getRemoteAddr());
		
		return "redirect:/accountCreated.html";
	}
	
	@RequestMapping(value = "/accountCreated.html", method = RequestMethod.GET)
	public String accountCreated(Model model) {
		model.addAttribute("pagename", "accountCreated");
		return "home";
	}
	
	@RequestMapping(value = "/activateAccount.html", method = RequestMethod.GET)
	public String activateAccount(@RequestParam(required=false, value="key") String key,
			@RequestParam(required=false, value="id") Long id, Model model) {
		boolean success = registrationService.activateUser(key, id);
		
		model.addAttribute("activationOkay", success);
		model.addAttribute("pagename", "activateAccount");
		
		return "home";
	}
	
}
