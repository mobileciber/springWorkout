package de.ciber.comics;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	@ModelAttribute("formBean")
	public RegistrationForm createFormBean() {
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
		// custom validation
		if (!form.getPassword().equals(form.getPasswordrepeat())) {
			result.addError(new FieldError("formBean", "passwordrepeat", form.getPasswordrepeat(), false,
					new String[]{"errors.mismatch.password"}, new String[]{}, "must match password"));
		}
		if (!form.getEmail().equals(form.getEmailrepeat())) {
			result.addError(new FieldError("formBean", "emailrepeat", form.getEmailrepeat(), false,
					new String[]{"errors.mismatch.email"}, new String[]{}, "must match email"));
		}
		
		if (result.hasErrors()) {
			logger.debug("VALIDATION ERROR");
			model.addAttribute("pagename", "register");
			return "home";
		}
		
		// TODO create new user account
		logger.debug("VALIDATION OK");
		
		return "redirect:/login.html"; // TODO redirect to a "account created"-page instead
	}
}
