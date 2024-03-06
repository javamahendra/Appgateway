package com.app.confg;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.datasource")
public class ConfigProperties {

	private String url;
	private String username;
	private String password;
	private String driverClassName;
	private String dialect;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

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

	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public String getDialect() {
		return dialect;
	}

	public void setDialect(String dialect) {
		this.dialect = dialect;
	}

	@Override
	public String toString() {
		return "ConfigProperties [url=" + url + ", username=" + username + ", password=" + password
				+ ", driverClassName=" + driverClassName + ", dialect=" + dialect + "]";
	}

}
