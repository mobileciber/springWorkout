package de.ciber.comics;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/secured", method = RequestMethod.GET)
	public String secured(Locale locale, Model model) {
		logger.info("HomeController.secured(..)");
		
		model.addAttribute("serverTime", "SECURED AREA" );
		
		return "home";
	}
	
	@RequestMapping(value = "/unsecured", method = RequestMethod.GET)
	public String unsecured(Locale locale, Model model) {
		logger.info("HomeController.unsecured(..)");
		
		model.addAttribute("serverTime", "UNSECURED AREA" );
		
		return "home";
	}

	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("HomeController.home(..)");
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/login.html", method = RequestMethod.GET)
	public String login(Model model, @RequestParam(required=false, value="login_error") String loginError) {
		System.out.println("*** GET PARAM WAS [" + loginError + "] ***");
		if ("1".equals(loginError)) {
			model.addAttribute("loginError", true);
			// TODO enable auto-locking users after 3 failed login attempts
//			if (userExistsInDatabase) {
//				failedLogins += 1;
//			}
		}
		return "login";
	}
	
}
