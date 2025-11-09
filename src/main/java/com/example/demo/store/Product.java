package com.example.demo.store;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * each class field correspond to a column in table
 */
@Entity
public class Product {

	@Id
	private String name;
	private Double price;

	public Product(String name, Double price) {
		this.name = name;
		this.price = price;
	}
	
	public Product() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
