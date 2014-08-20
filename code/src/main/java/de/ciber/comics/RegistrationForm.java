package de.ciber.comics;

import java.io.IOException;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public class RegistrationForm implements RegistrationInfo {
	
	private MultipartFile avatar;

	@NotEmpty
	@Size(max=45)
	private String username;
	
	@NotEmpty
	@Size(max=45)
	private String password;
	
	@NotEmpty
	@Size(max=45)
	private String passwordrepeat; // equality with 'password' is ensured by RegistrationController
	
	@NotEmpty
	@Email
	@Size(max=45)
	private String email;
	
	@NotEmpty
	@Email
	@Size(max=45)
	private String emailrepeat; // equality with 'email' is ensured by RegistrationController
	
	private String title;         // optional
	
	@NotEmpty
	@Size(max=45)
	private String firstname;
	
	@NotEmpty
	@Size(max=45)
	private String lastname;
	
	@NotEmpty
	@Size(max=45)
	private String street;
	
	@NotEmpty
	@Size(max=45)
	private String housenumber;
	
	@NotEmpty
	@Size(max=45)
	private String postalcode;
	
	@NotEmpty
	@Size(max=45)
	private String town;
	
	private String careof;        // optional
	
	private String company;       // optional
	
	@AssertTrue
	private boolean acceptterms;

	public MultipartFile getAvatar() {
		return avatar;
	}

	public void setAvatar(MultipartFile avatar) {
		this.avatar = avatar;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordrepeat() {
		return passwordrepeat;
	}

	public void setPasswordrepeat(String passwordrepeat) {
		this.passwordrepeat = passwordrepeat;
	}

	@Override
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailrepeat() {
		return emailrepeat;
	}

	public void setEmailrepeat(String emailrepeat) {
		this.emailrepeat = emailrepeat;
	}

	@Override
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	@Override
	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Override
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	@Override
	public String getHousenumber() {
		return housenumber;
	}

	public void setHousenumber(String housenumber) {
		this.housenumber = housenumber;
	}

	@Override
	public String getPostalcode() {
		return postalcode;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	@Override
	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	@Override
	public String getCareof() {
		return careof;
	}

	public void setCareof(String careof) {
		this.careof = careof;
	}

	@Override
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Boolean getAcceptterms() {
		return acceptterms;
	}

	public void setAcceptterms(Boolean acceptterms) {
		this.acceptterms = acceptterms;
	}

	@Override
	public boolean hasAvatarImage() {
		return !avatar.isEmpty();
	}

	@Override
	public byte[] getAvatarImage() {
		if (hasAvatarImage()) {
			try {
				return avatar.getBytes();
			} catch (IOException e) {
				throw new IllegalStateException("Failed to retrieve avatar image", e);
			}
		} else {
			throw new IllegalStateException("No avatar image available");
		}
	}
	
}
