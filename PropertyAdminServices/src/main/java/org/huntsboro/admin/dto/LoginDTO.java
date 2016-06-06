package org.huntsboro.admin.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;


public class LoginDTO implements Serializable {

	private String email;
	private String password;
	
	public LoginDTO(){}
	
	public LoginDTO(@JsonProperty("username") String email, @JsonProperty("password") String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
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
	
	
	
	
	
}
