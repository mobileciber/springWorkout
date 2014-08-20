package de.ciber.comics.view.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import de.ciber.comics.db.UserRepository;

/**
 * For simply dumping an avatar image blob
 */
@Controller
public class AvatarImageController {
	@Autowired
	UserRepository repository;

	@RequestMapping("/getImage/{id}")
	public void getImage(HttpServletResponse response,
			@PathVariable("id") final long id) throws IOException {
		response.setContentType("image/jpeg");

		byte[] imageBytes = repository.findOne(id).getAvatarImage();
		response.getOutputStream().write(imageBytes);
		response.getOutputStream().flush();
	}
}
