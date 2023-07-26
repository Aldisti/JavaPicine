package edu.school42.models;

import java.util.StringJoiner;

public class Product {

	private String	name;
	private Integer	price;

	public	Product() {
		this.name = "Default";
		this.price = 0;
	}

	public	Product(String name, Integer price) {
		this.name = name;
		this.price = price;
	}

	public Integer	increment(Integer value) {
		this.price += value;
		return (this.price);
	}

	@Override
	public String	toString() {
		return (new StringJoiner(", ", Product.class.getSimpleName() + "[", "]")
			.add("name='" + name + "'")
			.add("price=" + price)
			.toString());
	}
}

