package com.app.schema;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.reinert.jjschema.Attributes;
import com.github.reinert.jjschema.Media;
import com.github.reinert.jjschema.Attributes;

@Attributes(title = "Employee", description = "Schema for an employee")
public class Employee {

	@Media(type = "GET", binaryEncoding = "")
	@Attributes(required = true, description = "ID of the employee")
	private int id;

	@Attributes(required = true, minLength = 1, maxLength = 15, description = "Name of the employee")
	private String name;

	@Attributes(required = true, description = "Email of the employee")
	private String email;

	@Attributes(description = "ProfileImage of the employee")
	@JsonProperty("profile_image")
	private String profile_image;

	@Attributes(required = true, description = "Age in years of the employee")
	private int age;

	@Attributes(required = true, description = "Salary of the employee")
	private int salary;

	@Attributes(required = true, description = "Gender of the employee")
	private Gender gender;

	@Attributes(required = true, minItems = 1, maxItems = 3, minLength = 1, maxLength = 30, description = "Address lines of the employee")
	private List<String> address;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProfile_image() {
		return profile_image;
	}

	public void setProfile_image(String profile_image) {
		this.profile_image = profile_image;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public List<String> getAddress() {
		return address;
	}

	public void setAddress(List<String> address) {
		this.address = address;
	}
	// setters and getters are omitted for convinience
}
