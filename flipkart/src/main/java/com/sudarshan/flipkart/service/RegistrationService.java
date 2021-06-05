package com.sudarshan.flipkart.service;

import com.sudarshan.flipkart.dto.LoginDTO;
import com.sudarshan.flipkart.dto.RegisterDTO;
import com.sudarshan.flipkart.dto.UpdatePasswordDTO;

public interface RegistrationService {

	RegisterDTO validate(RegisterDTO registerDTO);
	
	RegisterDTO checkIfUserExists(String emailId);
	
	Boolean checkIfUserIsActive(String emailId);

	RegisterDTO validatePasswordDto(UpdatePasswordDTO updatePasswordDTO);

	RegisterDTO deleteUser(String emailId);

	RegisterDTO validateLogin(LoginDTO loginDTO);
	
	RegisterDTO updateEmailId(String emailId, String newEmailId);
	
	String generateOtp();
	
	
	
	
	

}
