package com.sudarshan.flipkart.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sudarshan.flipkart.dto.CartDTO;

public interface CartDAO extends JpaRepository<CartDTO, Integer> {

}
