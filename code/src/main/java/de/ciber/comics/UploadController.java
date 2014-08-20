package de.ciber.comics;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * Unused, left only for documentnation purposes
 */
@Controller
public class UploadController {
	@RequestMapping(value = "/upload", method = RequestMethod.GET) 
	public String uploadForm() {
		return "upload";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody String upload(@RequestParam("file") MultipartFile file) {
		if (!file.isEmpty()) {
			return String.format("on: %s, n: %s, ct: %s, s: %d",
					file.getOriginalFilename(), file.getName(),
					file.getContentType(), file.getSize());
		} else {
			return "Upload failed.";
		}
	}
}
