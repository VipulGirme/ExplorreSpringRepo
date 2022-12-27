package com.example.demo.model.request;

import lombok.Data;

@Data
public class AuthorCreationRequest {
	
	private String firstName;
	private String lastName;
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

}
