package de.ciber.comics.view.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import de.ciber.comics.db.UserRepository;
import de.ciber.comics.db.entity.User;
import de.ciber.comics.db.entity.UserRole;
import de.ciber.comics.view.model.UserSession;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value = "/secured", method = RequestMethod.GET)
	public String secured(Locale locale, Model model) {
		logger.info("HomeController.secured(..)");
		
		model.addAttribute("title", "CiberComics SECURED");
		model.addAttribute("message", "SECURED AREA" );
		
		return "home";
	}
	
	@RequestMapping(value = "/unsecured", method = RequestMethod.GET)
	public String unsecured(Locale locale, Model model) {
		logger.info("HomeController.unsecured(..)");
		
		model.addAttribute("title", "CiberComics UNSECURED");
		model.addAttribute("message", "UNSECURED AREA" );
		
		return "home";
	}

	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpSession session) {
		logger.info("HomeController.home(..)");
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("title", "CiberComics HOME");
		model.addAttribute("message", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/authSuccess", method = RequestMethod.GET)
	public RedirectView authSuccess(HttpSession session) {
		logger.info("HomeController.authSuccess(..)");
		
		UserSession userSession = (UserSession) session.getAttribute(UserSession.SESSION_ATTRIBUTE_NAME);
		
		// populate userSession object if necessary
		if (userSession != null && !userSession.isComplete()) {
			User userEntity = userRepository.findByUsername(userSession.getUsername());
        	userSession.setId(userEntity.getId());
        	userSession.setTitle(userEntity.getTitle());
        	userSession.setFirstname(userEntity.getFirstname());
        	userSession.setLastname(userEntity.getLastname());
        	userSession.setAdmin(userEntity.getRole() == UserRole.ROLE_ADMIN);
        	userSession.setComplete(true);
        	session.setAttribute(UserSession.SESSION_ATTRIBUTE_NAME, userSession);
		}
		
		return new RedirectView("/", true);
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
		
		model.addAttribute("title", "LOGIN PAGE");
		model.addAttribute("pagename", "login");
		return "home";
	}
}
