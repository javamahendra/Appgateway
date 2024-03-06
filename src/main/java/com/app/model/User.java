package com.app.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "usr_tab")
public class User extends Base {

	private String firstname;
	private String lastname;
	private String email;
	private String mobileno;
	private String password;

	@OneToMany(cascade = CascadeType.DETACH, mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true)
	List<HeaderKeys> keys;

	private boolean isDisable;

	public User() {
		super();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isDisable() {
		return isDisable;
	}

	public void setDisable(boolean isDisable) {
		this.isDisable = isDisable;
	}

	public List<HeaderKeys> getKeys() {
		return keys;
	}

	public void setKeys(List<HeaderKeys> keys) {
		this.keys = keys;
	}

	@Override
	public String toString() {
		return "User [firstname=" + firstname + ", lastname=" + lastname + ", email=" + email + ", mobileno=" + mobileno
				+ ", password=" + password + ", isDisable=" + isDisable + "]";
	}
}
