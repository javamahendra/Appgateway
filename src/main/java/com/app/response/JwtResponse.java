package com.app.response;

public class JwtResponse {

	private String token;
	private String type = "Basic";
	private String firstname;
	private String email;

	public JwtResponse() {
		super();
	}

	public JwtResponse(String token, String type, String firstname, String email) {
		super();
		this.token = token;
		this.type = type;
		this.firstname = firstname;
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "JwtResponse [token=" + token + ", type=" + type + ", firstname=" + firstname + ", email=" + email + "]";
	}
}
