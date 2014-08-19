package de.ciber.comics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserTestController {
	@Autowired
	private RegistrationService dao;
	
	@RequestMapping("/users")
	public @ResponseBody String listUsers() {
		return StringUtils.collectionToCommaDelimitedString(dao.getRegisteredUsers());
	}
}
