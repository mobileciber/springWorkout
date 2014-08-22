package de.ciber.comics.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * RegistrationDetails entity
 */
@Entity
public class RegistrationDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idRegistrationDetails")
	private long id;
	
	private String ip;
	
	private long date;
	
	private String activationKey;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public long getDate() {
		return date;
	}
	
	public void setDate(long date) {
		this.date = date;
	}

	public String getActivationKey() {
		return activationKey;
	}

	public void setActivationKey(String activationKey) {
		this.activationKey = activationKey;
	}

	@Override
	public String toString() {
		return "RegistrationDetails [id=" + id + ", ip=" + ip + ", date="
				+ date + ", activationKey=" + activationKey + "]";
	}
}
