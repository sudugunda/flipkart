package com.sudarshan.flipkart.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
public class CartDTO implements Serializable {

private static final Logger LOGGER = LoggerFactory.getLogger(ProductDTO.class);
	
	@GenericGenerator(name = "sc", strategy = "increment")
	@GeneratedValue(generator = "sc")
	@Id
	private Integer cartId;
	private String productName;
	private Integer price;
	private String imgUrl;
	private Integer rating;
	private int qty;
	
	public CartDTO() {
		LOGGER.debug(getClass().getSimpleName()+" : object created");
	}
	
	public Integer getProductId() {
		return cartId;
	}

	public void setProductId(Integer productId) {
		this.cartId = productId;
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

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	@Override
	public String toString() {
		return "CartDTO [cartId=" + cartId + ", productName=" + productName + ", price=" + price + ", imgUrl=" + imgUrl
				+ ", rating=" + rating + ", qty=" + qty + "]";
	}

}
