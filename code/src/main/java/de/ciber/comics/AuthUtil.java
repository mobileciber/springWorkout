package de.ciber.comics;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class AuthUtil {

	public void hint() {
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		md5.setEncodeHashAsBase64(true);
		
		// HASH AND SALT A RAW PASSWORD:
		
		String username = "user";
		String rawPassword = "myPassword123";
		String hash = md5.encodePassword(rawPassword, username);
		System.out.println(hash);

		// CHECK A RAW PASSWORD:
		
		String suppliedUsername = "user";
		String suppliedPassword = "myPassword123";

		String hashFromDB = "91QJHb4r0nynKJcW88O9wA==";
		
		boolean authenticated = md5.isPasswordValid(hashFromDB,
				suppliedPassword, suppliedUsername);

		if (authenticated) {
			System.out.println("OK");
		} else {
			System.out.println("NOT OK");
		}
	}

}
