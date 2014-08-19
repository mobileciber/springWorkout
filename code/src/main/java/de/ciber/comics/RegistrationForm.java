package de.ciber.comics;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class RegistrationForm {
	
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHousenumber() {
		return housenumber;
	}

	public void setHousenumber(String housenumber) {
		this.housenumber = housenumber;
	}

	public String getPostalcode() {
		return postalcode;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getCareof() {
		return careof;
	}

	public void setCareof(String careof) {
		this.careof = careof;
	}

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
	
}
