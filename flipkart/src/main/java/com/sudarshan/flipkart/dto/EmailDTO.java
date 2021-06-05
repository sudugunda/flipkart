package com.sudarshan.flipkart.dto;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailDTO implements Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmailDTO.class);
	
	private String emailId;
	
	public EmailDTO() {
		LOGGER.debug(getClass().getSimpleName()+" object created");
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Override
	public String toString() {
		return "EmailDTO [emailId=" + emailId + "]";
	}

}
