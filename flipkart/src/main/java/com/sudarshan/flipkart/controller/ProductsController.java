package com.sudarshan.flipkart.controller;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sudarshan.flipkart.dto.CartDTO;
import com.sudarshan.flipkart.dto.LoginDTO;
import com.sudarshan.flipkart.dto.ProductDTO;
import com.sudarshan.flipkart.dto.RegisterDTO;
import com.sudarshan.flipkart.service.ProductService;
import com.sudarshan.flipkart.service.RegistrationService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/products")
public class ProductsController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductsController.class);
	
	@Autowired
	private ProductService productService;
	
	@GetMapping(value = "/getProducts")
	public ResponseEntity<List<ProductDTO>> getProducts(){
		
		try {
			LOGGER.debug(getClass().getSimpleName()+" : getProducts method is called");
			
				List<ProductDTO> dtosFromDb = productService.getAllProducts();
				
				if(Objects.nonNull(dtosFromDb)) {
					LOGGER.debug(getClass().getSimpleName()+" : got Products successful");
					LOGGER.debug(getClass().getSimpleName()+" : "+dtosFromDb.toString());
					return new ResponseEntity<List<ProductDTO>>(dtosFromDb, HttpStatus.OK);
				}else {
					LOGGER.debug(getClass().getSimpleName()+" : Failed to get products");
				}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@PostMapping(value = "/addProducts")
	public ResponseEntity<List<ProductDTO>> addProducts(@RequestBody List<ProductDTO> list){
		
		try {
			LOGGER.debug(getClass().getSimpleName()+" : addProducts method is called");
			
				List<ProductDTO> dtosFromDb = productService.addProducts(list);
				LOGGER.debug(dtosFromDb.toString());
				if(Objects.nonNull(dtosFromDb)) {
					LOGGER.debug(getClass().getSimpleName()+" : got Products successful");
					LOGGER.debug(getClass().getSimpleName()+" : "+dtosFromDb.toString());
					return new ResponseEntity<List<ProductDTO>>(dtosFromDb, HttpStatus.OK);
				}else {
					LOGGER.debug(getClass().getSimpleName()+" : Failed to get products");
				}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@PostMapping(value = "/addProduct")
	public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO dto){
		
		try {
			LOGGER.debug(getClass().getSimpleName()+" : addProducts method is called");
			LOGGER.debug(getClass().getSimpleName()+" : addProducts method is called");
			
				ProductDTO dtoFromDb = productService.addProduct(dto);
				if(Objects.nonNull(dtoFromDb)) {
					LOGGER.debug(getClass().getSimpleName()+" : got Products successful");
					LOGGER.debug(getClass().getSimpleName()+" : "+dtoFromDb.toString());
					return new ResponseEntity<ProductDTO>(dtoFromDb, HttpStatus.OK);
				}else {
					LOGGER.debug(getClass().getSimpleName()+" : Failed to get products");
				}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@PostMapping(value = "/addToCart")
	public ResponseEntity<CartDTO> addToCart(@RequestBody CartDTO dto){
		
		try {
			LOGGER.debug(getClass().getSimpleName()+" : addToCart method is called");
			LOGGER.debug(getClass().getSimpleName()+" : "+dto);
			
				CartDTO dtoFromDb = productService.addToCart(dto);
				if(Objects.nonNull(dtoFromDb)) {
					LOGGER.debug(getClass().getSimpleName()+" : got Products successful");
					LOGGER.debug(getClass().getSimpleName()+" : "+dtoFromDb.toString());
					return new ResponseEntity<CartDTO>(dtoFromDb, HttpStatus.OK);
				}else {
					LOGGER.debug(getClass().getSimpleName()+" : Failed to get products");
				}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value = "/getFromCart")
	public ResponseEntity<List<CartDTO>> getFromCart(){
		
		try {
			LOGGER.debug(getClass().getSimpleName()+" : getFromCart method is called");
			
				List<CartDTO> dtosFromDb = productService.getFromCart();
				
				if(Objects.nonNull(dtosFromDb)) {
					LOGGER.debug(getClass().getSimpleName()+" : got Products successful");
					LOGGER.debug(getClass().getSimpleName()+" : "+dtosFromDb.toString());
					return new ResponseEntity<List<CartDTO>>(dtosFromDb, HttpStatus.OK);
				}else {
					LOGGER.debug(getClass().getSimpleName()+" : Failed to get products");
				}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

}
