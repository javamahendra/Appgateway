package com.app.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "header_keys")
public class HeaderKeys extends Base {

	private String stage;
	@Column(name = "client_id")
	private String clientid = "de528351-a04f-40de-b411-d1b1d800012a";
	@Column(name = "client_secret")
	private String clientsecret = "988d5a53-a634-4b0a-a86c-8e1dd2649092";

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userid")
	private User user;

	public HeaderKeys() {
		clientid = UUID.randomUUID().toString();
		clientsecret = UUID.randomUUID().toString();
	}

	public HeaderKeys(String stage) {
		//clientid = UUID.randomUUID().toString();
		//clientsecret = UUID.randomUUID().toString();
		this.stage = stage;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getClientid() {
		return clientid;
	}

	public void setClientid(String clientid) {
		this.clientid = clientid;
	}

	public String getClientsecret() {
		return clientsecret;
	}

	public void setClientsecret(String clientsecret) {
		this.clientsecret = clientsecret;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "HeaderKeys [stage=" + stage + ", clientid=" + clientid + ", clientsecret=" + clientsecret + "]";
	}

}
