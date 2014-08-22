package de.ciber.comics;

import java.net.URLEncoder;
import java.util.Collection;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Component;

import de.ciber.comics.db.UserRepository;
import de.ciber.comics.db.entity.Address;
import de.ciber.comics.db.entity.ContactDetails;
import de.ciber.comics.db.entity.RegistrationDetails;
import de.ciber.comics.db.entity.User;
import de.ciber.comics.db.entity.UserRole;
import de.ciber.comics.db.entity.UserStatus;
import de.ciber.comics.view.model.UserSession;

@Component
public class RegistrationService {
	
	private static final Logger logger = LoggerFactory.getLogger(RegistrationService.class);
	
	@Autowired
	UserRepository repository;
	
	@Autowired
	private JavaMailSender mailer;
	
	@Autowired
	private SimpleMailMessage templateMsg;
	
	@Autowired
	ReloadableResourceBundleMessageSource props;
	
	private static Md5PasswordEncoder pwdEncoder;
	
	static {
		pwdEncoder = new Md5PasswordEncoder();
		pwdEncoder.setEncodeHashAsBase64(true);
	}
	
	public Collection<User> getRegisteredUsers() {
		return repository.findAll();
	}
	
	public void registerUser(RegistrationInfo registrationInfo, String clientIpAddress) {
		User newUser = new User();
		
		// copy data from 'registrationInfo' into 'newUser'
		newUser.setFirstname(registrationInfo.getFirstname());
		newUser.setLastname(registrationInfo.getLastname());
		newUser.setTitle(registrationInfo.getTitle());
		newUser.setPassword(pwdEncoder.encodePassword(registrationInfo.getPassword(), registrationInfo.getUsername()));
		newUser.setRole(UserRole.ROLE_USER);
		newUser.setUsername(registrationInfo.getUsername());
		
		if (registrationInfo.hasAvatarImage()) {
			newUser.setAvatarImage(registrationInfo.getAvatarImage());
		}

		// user has to opt-in via a link to complete registration
		newUser.setStatus(UserStatus.REGISTRATION_NOT_COMPLETE);
		String activationKey = pwdEncoder.encodePassword(registrationInfo.getEmail(), 42).replace('=', 'x');
				
		Address newAddress = new Address();
		newAddress.setStreet(registrationInfo.getStreet());
		newAddress.setHouseNumber(registrationInfo.getHousenumber());
		newAddress.setPostalCode(registrationInfo.getPostalcode());
		newAddress.setTown(registrationInfo.getTown());
		newAddress.setCareOf(registrationInfo.getCareof());
		newAddress.setCompany(registrationInfo.getCompany());
		
		ContactDetails newContactDetails = new ContactDetails();
		
		RegistrationDetails newRegistrationDetails = new RegistrationDetails();
		newRegistrationDetails.setDate(System.currentTimeMillis());
		newRegistrationDetails.setIp(clientIpAddress);
		newRegistrationDetails.setActivationKey(activationKey);
		
		newUser.setAddress(newAddress);
		newUser.setContactDetails(newContactDetails);
		newUser.setRegistrationDetails(newRegistrationDetails);
		
		newUser = repository.save(newUser);
		
		// send confirmation link via email
		String confirmationLink = UserSession.SYSTEM_BASE_URL + "activateAccount.html?key=" + URLEncoder.encode(activationKey)
				+ "&id=" + newUser.getId();

		String mailText = props.getMessage("email.confirmaccount.text", new Object[]{
				registrationInfo.getFirstname(),
				registrationInfo.getLastname(),
				confirmationLink}, Locale.getDefault());
		String mailSubject = props.getMessage("email.confirmaccount.subject", null, Locale.getDefault());
		
		SimpleMailMessage message = new SimpleMailMessage(templateMsg);
		message.setTo(registrationInfo.getEmail());
		message.setText(mailText);
		message.setSubject(mailSubject);
		
		try {
			mailer.send(message);
		} catch (MailException e) {
			logger.debug("Failed to send confirmation link email", e);
		}
	}

	public boolean doesUsernameExist(String username) {
		return repository.findByUsername(username) != null;
	}

	public boolean activateUser(String key, Long id) {
		boolean success = false;
		
		if (id == null) {
			return false;
		}
		
		User user = repository.findOne(id);
		if (user != null && user.getStatus().equals(UserStatus.REGISTRATION_NOT_COMPLETE)) {
			String storedKey = user.getRegistrationDetails().getActivationKey();
			if (storedKey.equals(key)) {
				// everything ok, activate user
				user.setStatus(UserStatus.ACTIVE);
				repository.save(user);
				success = true;
			}
		}
		
		return success;
	}
}
