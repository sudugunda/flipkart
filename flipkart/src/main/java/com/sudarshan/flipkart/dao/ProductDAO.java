package com.sudarshan.flipkart.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sudarshan.flipkart.dto.ProductDTO;

public interface ProductDAO extends JpaRepository<ProductDTO, Integer> {

}
