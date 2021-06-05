package com.sudarshan.flipkart.controller;

import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sudarshan.flipkart.dto.LoginDTO;
import com.sudarshan.flipkart.dto.RegisterDTO;
import com.sudarshan.flipkart.service.RegistrationService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/login")
public class LoginController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private RegistrationService registrationService;
	
	public LoginController() {
		LOGGER.debug(getClass().getSimpleName()+" : object created");
	}
	
	@PostMapping(value = "/userLogin")
	public ResponseEntity<RegisterDTO> login(@RequestBody LoginDTO loginDTO){
		
		try {
			LOGGER.debug(getClass().getSimpleName()+" : login method is called");
			if(Objects.nonNull(loginDTO)) {
				LOGGER.debug(getClass().getSimpleName()+" : "+loginDTO.toString());
				RegisterDTO dtoFromDb = registrationService.validateLogin(loginDTO);
				
				if(Objects.nonNull(dtoFromDb)) {
					LOGGER.debug(getClass().getSimpleName()+" : login successful");
					LOGGER.debug(getClass().getSimpleName()+" : "+dtoFromDb.toString());
					return new ResponseEntity<RegisterDTO>(dtoFromDb, HttpStatus.OK);
				}else {
					LOGGER.debug(getClass().getSimpleName()+" : login Failed");
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

}
