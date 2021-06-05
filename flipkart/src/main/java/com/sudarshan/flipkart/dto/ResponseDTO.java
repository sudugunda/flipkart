package com.sudarshan.flipkart.dto;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ResponseDTO implements Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResponseDTO.class);
	
	private String message = "Error";
	
	public ResponseDTO() {
		LOGGER.debug(getClass().getSimpleName()+" : object created");
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ResponseDTO [message=" + message + "]";
	}

}
