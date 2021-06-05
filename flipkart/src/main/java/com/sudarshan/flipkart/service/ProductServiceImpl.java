package com.sudarshan.flipkart.service;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sudarshan.flipkart.dao.CartDAO;
import com.sudarshan.flipkart.dao.ProductDAO;
import com.sudarshan.flipkart.dto.CartDTO;
import com.sudarshan.flipkart.dto.ProductDTO;
import com.sudarshan.flipkart.dto.RegisterDTO;

@Service
public class ProductServiceImpl implements ProductService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Autowired
	private ProductDAO productDao;
	
	@Autowired
	private CartDAO cartDao;
	
	@Override
	public List<ProductDTO> getAllProducts() {

		try {
			LOGGER.debug(getClass().getSimpleName()+" : getAllProducts method called");
			List<ProductDTO> dtosAfterFetch = productDao.findAll();
			LOGGER.debug(getClass().getSimpleName()+ dtosAfterFetch);
			if(dtosAfterFetch.size()>0) {
				LOGGER.debug(getClass().getSimpleName()+" : got products from db successfully");
				LOGGER.debug(getClass().getSimpleName()+" : dtos from db "+dtosAfterFetch);
				return dtosAfterFetch;
			}else {
				LOGGER.debug(getClass().getSimpleName()+" : didn't got products from db");
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<ProductDTO> addProducts(List<ProductDTO> list) {

		try {
			LOGGER.debug(getClass().getSimpleName()+" : addProducts method called");
			List<ProductDTO> dtosAfterSave = productDao.saveAll(list);
			if(dtosAfterSave.size()>0) {
				LOGGER.debug(getClass().getSimpleName()+" : saved products to db successfully");
				LOGGER.debug(getClass().getSimpleName()+" : dtos from db "+dtosAfterSave);
				return dtosAfterSave;
			}else {
				LOGGER.debug(getClass().getSimpleName()+" : products save failed");
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ProductDTO addProduct(ProductDTO product) {

		try {
			LOGGER.debug(getClass().getSimpleName()+" : addProduct method called");
			ProductDTO dtoAfterSave = productDao.save(product);
			if(Objects.nonNull(dtoAfterSave)) {
				LOGGER.debug(getClass().getSimpleName()+" : saved product to db successfully");
				LOGGER.debug(getClass().getSimpleName()+" : dto from db "+dtoAfterSave);
				return dtoAfterSave;
			}else {
				LOGGER.debug(getClass().getSimpleName()+" : products save failed");
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public CartDTO addToCart(CartDTO dto) {
		
		try {
			LOGGER.debug(getClass().getSimpleName()+" : addToCart method called");
			LOGGER.debug(getClass().getSimpleName()+" : calling product dao");
			CartDTO dtoAfterSave = cartDao.save(dto);
			if(Objects.nonNull(dtoAfterSave)) {
				LOGGER.debug(getClass().getSimpleName()+" : product saved to db successfully");
				LOGGER.debug(getClass().getSimpleName()+" : "+dtoAfterSave);
				return dtoAfterSave;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	@Override
	public List<CartDTO> getFromCart() {
		
		try {
			LOGGER.debug(getClass().getSimpleName()+" : getFromCart method called");
			List<CartDTO> dtosAfterFetch = cartDao.findAll();
			LOGGER.debug(getClass().getSimpleName()+ dtosAfterFetch);
			if(dtosAfterFetch.size()>0) {
				LOGGER.debug(getClass().getSimpleName()+" : got products from db successfully");
				LOGGER.debug(getClass().getSimpleName()+" : dtos from db "+dtosAfterFetch);
				return dtosAfterFetch;
			}else {
				LOGGER.debug(getClass().getSimpleName()+" : didn't got products from db");
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	

}
