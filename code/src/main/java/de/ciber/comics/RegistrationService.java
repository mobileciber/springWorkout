package de.ciber.comics;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Component;

import de.ciber.comics.db.UserRepository;
import de.ciber.comics.db.entity.Address;
import de.ciber.comics.db.entity.ContactDetails;
import de.ciber.comics.db.entity.RegistrationDetails;
import de.ciber.comics.db.entity.User;
import de.ciber.comics.db.entity.UserRole;
import de.ciber.comics.db.entity.UserStatus;

@Component
public class RegistrationService {	// TODO maybe rename to UserService?
	
	@Autowired
	UserRepository repository;
	
	private static Md5PasswordEncoder pwdEncoder;
	
	static {
		pwdEncoder = new Md5PasswordEncoder();
		pwdEncoder.setEncodeHashAsBase64(true);
	}
	
	public Collection<User> getRegisteredUsers() {
		return repository.findAll();
	}
	
	public void registerUser(RegistrationInfo registrationInfo) {
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

		// TODO change this when introducing double opt-in
		newUser.setStatus(UserStatus.ACTIVE);
		
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
		newRegistrationDetails.setIp("127.0.0.1"); // FIXME get IP address from RegistrationController
		
		newUser.setAddress(newAddress);
		newUser.setContactDetails(newContactDetails);
		newUser.setRegistrationDetails(newRegistrationDetails);
		
		repository.save(newUser);
	}

	public boolean doesUsernameExist(String username) {
		return repository.findByUsername(username) != null;
	}
}
