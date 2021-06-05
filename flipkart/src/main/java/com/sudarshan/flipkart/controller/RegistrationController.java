package com.sudarshan.flipkart.controller;

import java.util.Objects;

import javax.imageio.spi.RegisterableService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sudarshan.flipkart.dto.EmailDTO;
import com.sudarshan.flipkart.dto.RegisterDTO;
import com.sudarshan.flipkart.dto.ResponseDTO;
import com.sudarshan.flipkart.dto.UpdatePasswordDTO;
import com.sudarshan.flipkart.service.RegistrationService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/register")
public class RegistrationController {

	@Autowired
	private RegistrationService service;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);
	
	@PostMapping(value = "/create")
	public ResponseEntity<RegisterDTO> register(@RequestBody RegisterDTO registerDTO){
		
		try {
			LOGGER.debug(getClass().getSimpleName()+" : entered register method");
			LOGGER.debug(getClass().getSimpleName()+" : "+registerDTO.toString());
			RegisterDTO dtoAfterSave = service.validate(registerDTO);
			if(Objects.nonNull(dtoAfterSave)) {
				LOGGER.debug(getClass().getSimpleName()+" : user registered successfully");
				return new ResponseEntity<>(dtoAfterSave, HttpStatus.CREATED);
			} else {
				LOGGER.debug(getClass().getSimpleName()+" : user registration failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PutMapping(value = "/updatePassword")
	public ResponseEntity<RegisterDTO> updatePassword(@RequestBody UpdatePasswordDTO updatePasswordDTO){
		
		try {
			LOGGER.debug(getClass().getSimpleName()+" : executing updatePassword method");
			RegisterDTO dtoAfterValidate = service.validatePasswordDto(updatePasswordDTO);
			if(Objects.nonNull(dtoAfterValidate)) {
				LOGGER.debug(getClass().getSimpleName()+" : password updated successfully");
				return new ResponseEntity<>(dtoAfterValidate, HttpStatus.OK);
			} else {
				LOGGER.debug(getClass().getSimpleName()+" : password updated failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PutMapping(value = "/deleteUser")
	public ResponseEntity<RegisterDTO> deleteUser(@RequestParam String emailId) {
		
		LOGGER.debug(getClass().getSimpleName()+" : executing deleteUser method");
		try {
			RegisterDTO dtoAfterDelete = service.deleteUser(emailId);
			if(Objects.nonNull(dtoAfterDelete)) {
				LOGGER.debug(getClass().getSimpleName()+" : user deleted successfully");
				return new ResponseEntity<>(dtoAfterDelete, HttpStatus.OK);
			}else {
				LOGGER.debug(getClass().getSimpleName()+" : user not deleted");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PutMapping(value = "/updateEmailId")
	public ResponseEntity<RegisterDTO> updateEmail(@RequestParam String emailId, @RequestParam String newEmailId){
		
		try {
			LOGGER.debug(getClass().getSimpleName()+" : executing updateEmail method "+emailId);
			RegisterDTO dtoAfterUpdate = service.updateEmailId(emailId, newEmailId);
			if(Objects.nonNull(dtoAfterUpdate)) {
				LOGGER.debug(getClass().getSimpleName()+" : "+dtoAfterUpdate.toString());
				return new ResponseEntity<>(dtoAfterUpdate, HttpStatus.OK);
			}else {
				LOGGER.debug(getClass().getSimpleName()+" : email update failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value = "/generateOtp")
	public ResponseEntity<String> generateOtp(){
		
		try {
			LOGGER.debug(getClass().getSimpleName()+" : executing generateOtp method ");
			String otpp = service.generateOtp();
			if(!otpp.equals("")) {
				LOGGER.debug(getClass().getSimpleName()+" : otp checked successfully!");
				return new ResponseEntity<>(otpp, HttpStatus.OK);
			}else {
				LOGGER.debug(getClass().getSimpleName()+" : otp did not match");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
