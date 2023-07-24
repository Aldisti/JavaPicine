package edu.school42.models;

public class Product {
	
	private Long	id;
	private String	name;
	private int		price;

	public	Product(Long id, String name, int price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public Long		getId() {
		return (this.id);
	}

	public String	getName() {
		return (this.name);
	}

	public int		getPrice() {
		return (this.price);
	}

	public void		setId(Long id) {
		this.id = id;
	}

	public void		setName(String name) {
		this.name = name;
	}

	public void		setPrice(int price) {
		this.price = price;
	}

	@Override
	public String	toString() {
		return ("[id=" + this.id + ", name=" + this.name + ", price=" + this.price + "]");
	}
}

