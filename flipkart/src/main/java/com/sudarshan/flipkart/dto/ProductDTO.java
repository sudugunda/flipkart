package com.sudarshan.flipkart.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
public class ProductDTO implements Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductDTO.class);
	
	@GenericGenerator(name = "s", strategy = "increment")
	@GeneratedValue(generator = "s")
	@Id
	private Integer productId;
	private String productName;
	private Integer price;
	private String imgUrl;
	private Integer rating;
	
	public ProductDTO() {
		LOGGER.debug(getClass().getSimpleName()+" : object created");
	}
	
	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "ProductDTO [productId=" + productId + ", productName=" + productName + ", price=" + price + ", imgUrl="
				+ imgUrl + ", rating=" + rating + "]";
	}

}
