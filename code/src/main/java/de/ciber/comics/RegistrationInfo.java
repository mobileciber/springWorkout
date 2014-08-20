package de.ciber.comics;

public interface RegistrationInfo {

	public String getUsername();

	public String getPassword();

	public String getEmail();

	public String getTitle();

	public String getFirstname();

	public String getLastname();

	public String getStreet();

	public String getHousenumber();

	public String getPostalcode();

	public String getTown();

	public String getCareof();

	public String getCompany();

	public boolean hasAvatarImage();

	public byte[] getAvatarImage();
}