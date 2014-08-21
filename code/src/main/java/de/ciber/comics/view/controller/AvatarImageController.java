package de.ciber.comics.view.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpUtils;

import org.apache.commons.io.IOUtils;
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
	public void getImage(HttpServletRequest request,
			HttpServletResponse response, @PathVariable("id") final long id)
			throws IOException {
		response.setContentType("image/jpeg");

		byte[] imageBytes = repository.findOne(id).getAvatarImage();

		if (imageBytes == null || imageBytes.length <= 0) {
			// FIXME This is horribly ugly; find a better solution
			//       to get the raw bytes from the default image!
			
			URL url = new URL(HttpUtils.getRequestURL(request).toString());
			URL newUrl = new URL("http://" + url.getHost()
					+ ":8080" + request.getContextPath()
					+ "/images/login_icon.jpg");
			InputStream is = newUrl.openStream();
			
			imageBytes = IOUtils.toByteArray(is);
		}
		response.getOutputStream().write(imageBytes);
		response.getOutputStream().flush();
	}
}
