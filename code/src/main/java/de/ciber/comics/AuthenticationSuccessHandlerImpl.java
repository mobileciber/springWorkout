package de.ciber.comics;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import de.ciber.comics.view.model.UserSession;

public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
	
	@Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String username = authentication.getName();
//        	User userEntity = userRepository.findByUsername(username);
        	
    	UserSession userSession = new UserSession();
    	userSession.setUsername(username);
//        	userSession.setId(userEntity.getId());
//        	userSession.setTitle(userEntity.getTitle());
//        	userSession.setFirstname(userEntity.getFirstname());
//        	userSession.setLastname(userEntity.getLastname());
//        	userSession.setAdmin(userEntity.getRole() == UserRole.ROLE_ADMIN);
    	
    	// TODO At this point, we would like to load the complete user entity from the database
    	//      (see commented code) and make it available in our UserSession object.
    	//      
    	//      What we did not manage to get working is the injection of "userRepository" into
    	//      this class (apparently because they live in different application contexts).
        	
    	request.getSession().setAttribute(UserSession.SESSION_ATTRIBUTE_NAME, userSession);
        
    	response.sendRedirect(request.getContextPath()); // we want to be sent to "/comics"
    }
}