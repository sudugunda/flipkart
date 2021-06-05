package com.sudarshan.flipkart.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sudarshan.flipkart.dto.RegisterDTO;

public interface RegistrationDAO extends JpaRepository<RegisterDTO, Integer>{

	List<RegisterDTO> findByUserNameAndPassword(String userName, String password);
	
	List<RegisterDTO> findByEmailId(String emailId);
	
	List<RegisterDTO> findByEmailIdAndPassword(String emailId, String password);
}
