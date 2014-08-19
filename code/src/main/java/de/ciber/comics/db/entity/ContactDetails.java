package de.ciber.comics.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * ContactDetails entity
 */
@Entity
public class ContactDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idContactDetails")
	private long id;
	
	private String telephoneNumber;
	private String mobileNumber;
	private String fax;
	private String email;
	private String skypeName;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@Deprecated
	public String getFax() {
		return fax;
	}

	@Deprecated
	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSkypeName() {
		return skypeName;
	}

	public void setSkypeName(String skypeName) {
		this.skypeName = skypeName;
	}
	
	@Override
	public String toString() {
		return "ContactDetails [id=" + id + ", telephoneNumber="
				+ telephoneNumber + ", mobileNumber=" + mobileNumber + ", fax="
				+ fax + ", email=" + email + ", skypeName=" + skypeName + "]";
	}
}
