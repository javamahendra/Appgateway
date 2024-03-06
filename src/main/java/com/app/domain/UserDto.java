package com.app.domain;

import java.util.List;

import com.app.model.HeaderKeys;

public class UserDto {

	private String id;
	private String firstname;
	private String lastname;
	private String email;
	private String mobileno;
	private String password;

	List<HeaderKeys> keys;

	private boolean isDisable;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public List<HeaderKeys> getKeys() {
		return keys;
	}

	public void setKeys(List<HeaderKeys> keys) {
		this.keys = keys;
	}

	public boolean isDisable() {
		return isDisable;
	}

	public void setDisable(boolean isDisable) {
		this.isDisable = isDisable;
	}

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
				+ ", mobileno=" + mobileno + ", password=" + password + ", keys=" + keys + ", isDisable=" + isDisable
				+ "]";
	}
}
