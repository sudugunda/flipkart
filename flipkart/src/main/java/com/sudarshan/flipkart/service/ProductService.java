package com.sudarshan.flipkart.service;

import java.util.List;

import com.sudarshan.flipkart.dto.CartDTO;
import com.sudarshan.flipkart.dto.ProductDTO;

public interface ProductService {
	
	List<ProductDTO> getAllProducts();

	List<ProductDTO> addProducts(List<ProductDTO> list);
	
	ProductDTO addProduct(ProductDTO product);
	
	CartDTO addToCart(CartDTO dto);
	
	List<CartDTO> getFromCart();
}
