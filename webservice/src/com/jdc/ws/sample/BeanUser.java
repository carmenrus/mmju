package com.jdc.ws.sample;

import javax.ws.rs.FormParam;
import javax.ws.rs.HeaderParam;

public class BeanUser {
	
	@FormParam("first")
	private String firstName;
	@FormParam("last")
	private String lastName;
	@HeaderParam("Content-Type")
	private String content;

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
