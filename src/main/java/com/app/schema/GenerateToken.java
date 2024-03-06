package com.app.schema;

import com.github.reinert.jjschema.Attributes;

@Attributes(title = "GenerateToken", description = "Schema for GenerateToken")
public class GenerateToken {

	@Attributes(required = true, minLength = 1, maxLength = 15, description = "Email of the Login User")
	private String email;
	@Attributes(required = true, minLength = 1, maxLength = 15, description = "Password of the Login User")
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginRequest [email=" + email + ", password=" + password + "]";
	}

}
