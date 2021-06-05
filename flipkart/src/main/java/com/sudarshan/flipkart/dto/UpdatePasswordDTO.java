package com.sudarshan.flipkart.dto;

import java.io.Serializable;

import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpdatePasswordDTO implements Serializable{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UpdatePasswordDTO.class);
	
	private String emailId;
	private String password;
	private String newPassword;
	private String confirmPassword;
	
	public UpdatePasswordDTO() {
		LOGGER.debug(getClass().getSimpleName()+" : creating update password object");
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

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@Override
	public String toString() {
		return "UpdatePasswordDTO [emailId=" + emailId + ", password=" + password + ", newPassword=" + newPassword
				+ ", confirmPassword=" + confirmPassword + "]";
	}

}
