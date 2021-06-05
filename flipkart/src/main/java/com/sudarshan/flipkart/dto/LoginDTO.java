package com.sudarshan.flipkart.dto;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginDTO implements Serializable{

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginDTO.class);
	
	private String emailId;
	private String password;
	
	public LoginDTO() {
		LOGGER.debug(getClass().getSimpleName()+" : object created");
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginDTO [emailId=" + emailId + ", password=" + password + "]";
	}

}
