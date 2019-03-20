package com.springdata.domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;

@Entity
@Access(value=AccessType.FIELD)
public class Product {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="product_id")
	private Integer id;
	private String code;
	private String name;
	private String photo;
	private double price;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Product() {
	}

	public Product(String code, String name, String photo, double price) {
		this.code = code;
		this.name = name;
		this.photo = photo;
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", code=" + code + ", name=" + name + ", photo=" + photo + ", price=" + price
				+ "]";
	}
	

}
