package com.sudarshan.flipkart.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
public class RegisterDTO implements Serializable{

	private static final Logger LOGGER = LoggerFactory.getLogger(RegisterDTO.class);
	
	@GenericGenerator(name = "s", strategy = "increment")
	@GeneratedValue(generator = "s")
	@Id
	private Integer userId;
	private String userName;
	private String password;
	private String cPassword;
	private Boolean isActive;
	private String emailId;
	private Long mobileNo;
	private String gender;
	
	public RegisterDTO() {
		LOGGER.debug("creating object of"+ getClass().getSimpleName());
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getcPassword() {
		return cPassword;
	}

	public void setcPassword(String cPassword) {
		this.cPassword = cPassword;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "RegisterDTO [userId=" + userId + ", userName=" + userName + ", password=" + password + ", isActive="
				+ isActive + ", emailId=" + emailId + ", mobileNo=" + mobileNo + "]";
	}
	

}
