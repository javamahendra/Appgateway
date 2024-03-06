package com.app.response;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class Error POJO.
 * 
 * @author Ashok Samrat
 * @version 1.0
 * @since 1.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)

public class Error {

	@JsonProperty("message")
	private String message;

	@JsonProperty("error_cd")
	private String errorcd;
	
	@JsonProperty("code")
	private String code;

	@JsonProperty("desc")
	private String desc;
	
	@JsonProperty("info")
	private String info;

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Error() {

	}

	public Error(String message, String errorcd) {
		this.message = message;
		this.errorcd = errorcd;
	}
	
	public Error(String message, String errorcd, String info) {
		this.message = message;
		this.errorcd = errorcd;
		this.info = info;
	}
	
	public Error(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrorcd() {
		return errorcd;
	}

	public void setErrorcd(String errorcd) {
		this.errorcd = errorcd;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "Error [message=" + message + ", errorcd=" + errorcd + ", code=" + code
				+ ", desc=" + desc + "]";
	}
}
