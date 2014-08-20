package de.ciber.comics;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import de.ciber.comics.db.UserRepository;
import de.ciber.comics.db.entity.User;
import de.ciber.comics.db.entity.UserRole;
import de.ciber.comics.view.model.UserSession;

public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
	
	private UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if (authentication.isAuthenticated()) {
        	String username = authentication.getName();
        	User userEntity = userRepository.findByUsername(username);
        	
        	UserSession userSession = new UserSession();
        	userSession.setUsername(username);
        	userSession.setId(userEntity.getId());
        	userSession.setTitle(userEntity.getTitle());
        	userSession.setFirstname(userEntity.getFirstname());
        	userSession.setLastname(userEntity.getLastname());
        	userSession.setAdmin(userEntity.getRole() == UserRole.ROLE_ADMIN);
        	
        	request.getSession().setAttribute(UserSession.SESSION_ATTRIBUTE_NAME, userSession);
        }        
    }

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
}