package com.sudarshan.flipkart.service;

import java.util.List;
import java.util.Objects;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.sudarshan.flipkart.controller.RegistrationController;
import com.sudarshan.flipkart.dao.RegistrationDAO;
import com.sudarshan.flipkart.dto.EmailDTO;
import com.sudarshan.flipkart.dto.LoginDTO;
import com.sudarshan.flipkart.dto.RegisterDTO;
import com.sudarshan.flipkart.dto.UpdatePasswordDTO;

@Service
public class RegistrationServiceImpl implements RegistrationService {

	@Autowired
	private JavaMailSender mailsender;
	
	@Autowired
	private RegistrationDAO registrationDAO;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationServiceImpl.class);
	
	@Override
	public RegisterDTO validate(RegisterDTO registerDTO) {
		try {
			LOGGER.debug(getClass().getSimpleName()+" : validate method called");
			LOGGER.debug(getClass().getSimpleName()+" : "+registerDTO.toString());
			if(!StringUtils.isEmpty(registerDTO.getUserName()) && !StringUtils.isEmpty(registerDTO.getEmailId()) && Objects.nonNull(registerDTO.getMobileNo())) {
				LOGGER.debug(getClass().getSimpleName()+" : fields validated successfully");
				if(Objects.nonNull(checkIfUserExists(registerDTO.getEmailId()))) {
					LOGGER.debug(getClass().getSimpleName()+" : user already registered no need to register");
					return null;
				}else {
					LOGGER.debug(getClass().getSimpleName()+" : user not registered need to register");
					registerDTO.setIsActive(true);
					registerDTO.setPassword(generatePassword());
					LOGGER.debug(getClass().getSimpleName()+" : email sent successfully");
					RegisterDTO dtoAfterSave = registrationDAO.save(registerDTO);
					if(Objects.nonNull(dtoAfterSave)) {
						sendMail(registerDTO);
						LOGGER.debug(getClass().getSimpleName()+" : user registered successfully");
						LOGGER.debug(getClass().getSimpleName()+" : user from db "+dtoAfterSave);
						return dtoAfterSave;
					}else {
						LOGGER.debug(getClass().getSimpleName()+" : user registration failed");
						return null;
					}
				}
			}else {
				LOGGER.debug(getClass().getSimpleName()+" : fields validation failed");
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public String generateOtp() {
		LOGGER.debug(getClass().getSimpleName()+" : generateOtp method called");
		String values = "0123456789"; 
		
		Random rndm_method = new Random();
		String otp = "";
		
		for (int i = 0; i < 4; i++) 
        {  
            otp = otp + 
              values.charAt(rndm_method.nextInt(values.length())); 
        }
		
		LOGGER.debug(getClass().getSimpleName()+ otp +" : otp generated successfully!");
		return otp;
	}
	
	public String generatePassword()
	{
		LOGGER.debug(getClass().getSimpleName()+" : generatePassword method called");
		System.out.println("Generating password using random() : "); 
        System.out.print("Your new password is : "); 
        String Capital_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
        String Small_chars = "abcdefghijklmnopqrstuvwxyz"; 
        String numbers = "0123456789"; 
                String symbols = "!@#$%^&*_=+-/.?<>)"; 
  
  
        String values = Capital_chars + Small_chars + 
                        numbers + symbols; 
   
        Random rndm_method = new Random(); 
  
        String password = ""; 
  
        for (int i = 0; i < 10; i++) 
        {  
            password = password + 
              values.charAt(rndm_method.nextInt(values.length())); 
        }
        LOGGER.debug(getClass().getSimpleName()+" : password generated successfully");
        return password; 
	}
	
	private void sendMail(RegisterDTO registerDTO) {
		
		LOGGER.debug(getClass().getSimpleName()+" : sendMail method called");
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		try {
			mailMessage.setTo(registerDTO.getEmailId());
			mailMessage.setSubject("Registered successfully");
			mailMessage.setText("Hi "+registerDTO.getUserName()+ " thank you for registering with us. Please find your first time password "+registerDTO.getPassword()+". Please click on this link and update your password: http://localhost:8080/amazon/register/changePassword"+"Your otp is : "+generateOtp());
			LOGGER.debug(getClass().getSimpleName()+" : mail created and ready to send");
			mailsender.send(mailMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	private void sendMailOtp(RegisterDTO registerDTO) {
//
//		LOGGER.debug(getClass().getSimpleName()+" : sendMailOtp method called");
//		SimpleMailMessage mailMessage = new SimpleMailMessage();
//		try {
//			mailMessage.setTo(registerDTO.getEmailId());
//			mailMessage.setSubject("Otp sent successfully");
//			mailMessage.setText("Hi "+registerDTO.getUserName()+ " please enter otp and update password. Your OTP is : "+ generateOtp());
//			LOGGER.debug(getClass().getSimpleName()+" : mail created and ready to send");
//			mailsender.send(mailMessage);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	@Override
	public RegisterDTO validatePasswordDto(UpdatePasswordDTO updatePasswordDTO) {
		
		LOGGER.debug(getClass().getSimpleName()+" : executing validatePasswordDto method");
		if(Objects.nonNull(updatePasswordDTO)) {
			LOGGER.debug(getClass().getSimpleName()+" : updatePasswordDTO is not null");
			if(!StringUtils.isEmpty(updatePasswordDTO.getEmailId()) && 
					!StringUtils.isEmpty(updatePasswordDTO.getPassword()) &&
					!StringUtils.isEmpty(updatePasswordDTO.getNewPassword()) &&
					!StringUtils.isEmpty(updatePasswordDTO.getConfirmPassword())){
				LOGGER.debug(getClass().getSimpleName()+" : All fields Are not null");
				List<RegisterDTO> registerDTOs = registrationDAO.findByEmailIdAndPassword(updatePasswordDTO.getEmailId(), updatePasswordDTO.getPassword());
				if(registerDTOs.size()>0 && registerDTOs.get(0).getIsActive()) {
					RegisterDTO registerDTO = registerDTOs.get(0);
					LOGGER.debug(getClass().getSimpleName()+" : User exists");
					LOGGER.debug(getClass().getSimpleName()+" : user before updating password : "+registerDTO.toString());
					registerDTO.setPassword(updatePasswordDTO.getNewPassword());
					RegisterDTO dtoAfterUpdate = registrationDAO.save(registerDTO);
					LOGGER.debug(getClass().getSimpleName()+" : "+dtoAfterUpdate);
					if(Objects.nonNull(dtoAfterUpdate)) {
						LOGGER.debug(getClass().getSimpleName()+" : user after updating password : "+dtoAfterUpdate.toString());
						LOGGER.debug(getClass().getSimpleName()+" : Password updated successfully");
						return dtoAfterUpdate;
					}else {
						LOGGER.debug(getClass().getSimpleName()+" : Password update failed");
						return null;
					}
				}else {
					LOGGER.debug(getClass().getSimpleName()+" : User not exists or inactive");
					return null;
				}
			}else {
				LOGGER.debug(getClass().getSimpleName()+" : Some or all fields are null");
			}
		}else {
			LOGGER.debug(getClass().getSimpleName()+" : updatePasswordDTO is null");
		}
		return null;
	}

	@Override
	public RegisterDTO deleteUser(String emailId) {
		
		try {
			LOGGER.debug(getClass().getSimpleName()+" executing deleteUser method");
			if(!StringUtils.isEmpty(emailId)) {
				RegisterDTO registerDTO = checkIfUserExists(emailId);
				if(checkIfUserIsActive(registerDTO.getEmailId())) {
					LOGGER.debug(getClass().getSimpleName()+" : user exists to delete");
					registerDTO.setIsActive(false);
					registrationDAO.save(registerDTO);
					LOGGER.debug(getClass().getSimpleName()+" : user deleted");
					return registerDTO;
				}else {
					LOGGER.debug(getClass().getSimpleName()+" : user not deleted");
					return null;
				}
				
			}else {
				LOGGER.debug(getClass().getSimpleName()+" : email missing");
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	
	}

	@Override
	public RegisterDTO validateLogin(LoginDTO loginDTO) {
		
		try {
			LOGGER.debug(getClass().getSimpleName()+" : validateLogin method called");
			if(Objects.nonNull(loginDTO)) {
				if(!StringUtils.isEmpty(loginDTO.getEmailId()) && !StringUtils.isEmpty(loginDTO.getPassword())) {
					List<RegisterDTO> registerDTOs = registrationDAO.findByEmailIdAndPassword(loginDTO.getEmailId(), loginDTO.getPassword());
					if(registerDTOs.size()>0) {
						RegisterDTO dto = registerDTOs.get(0);
						if(dto.getIsActive()) {
							return dto;
						}else {
							return null;
						}
						
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public RegisterDTO checkIfUserExists(String emailId) {
	
		try {
			LOGGER.debug(getClass().getSimpleName()+" : checkIfUserExists method called");
			LOGGER.debug(getClass().getSimpleName()+" : "+emailId);
			List<RegisterDTO> registerDTOs = registrationDAO.findByEmailId(emailId);
			if(registerDTOs.size()>0) {
				RegisterDTO registerDTO = registerDTOs.get(0);
				LOGGER.debug(getClass().getSimpleName()+" : user with emailId "+registerDTO.getEmailId()+" exists no need to register");
				return registerDTO;
			}else {
				LOGGER.debug(getClass().getSimpleName()+" : user with emailId "+emailId+" does not exists need to register");
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Boolean checkIfUserIsActive(String emailId) {
		
		try {
			LOGGER.debug(getClass().getSimpleName()+" : executing checkIfUserIsActive method : "+emailId);
			List<RegisterDTO> registerDTOs = registrationDAO.findByEmailId(emailId);
			if(registerDTOs.size()>0) {
				LOGGER.debug(getClass().getSimpleName()+" : user from db : "+registerDTOs.get(0).toString());
				return registerDTOs.get(0).getIsActive()?true:false;
			}else {
				LOGGER.debug(getClass().getSimpleName()+" : user doesn't exists");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
		
	}

	@Override
	public RegisterDTO updateEmailId(String emailId, String newEmailId) {
		
		try {
			LOGGER.debug(getClass().getSimpleName()+" : executing updateEmailId method");
			LOGGER.debug(getClass().getSimpleName()+" : "+emailId);
			RegisterDTO registerDTO = checkIfUserExists(emailId);
			registerDTO.setEmailId(newEmailId);
			LOGGER.debug(getClass().getSimpleName()+" : update emailId to registerdto "+registerDTO.toString());
			RegisterDTO dtoAfterSave = registrationDAO.save(registerDTO);
			if(Objects.nonNull(dtoAfterSave)) {
				return dtoAfterSave;
			}else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
