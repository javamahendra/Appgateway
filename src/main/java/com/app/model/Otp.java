package com.app.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "otp")
public class Otp extends Base {

	private String otp;
	private String email;
	private String userid;

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Override
	public String toString() {
		return "Otp [otp=" + otp + ", email=" + email + ", userid=" + userid + "]";
	}

}
