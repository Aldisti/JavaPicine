package edu.school42.models;

public class User {

	private String	name;
	private Integer		balance;

	public	User() {
		this.name = "Default";
		this.balance = 0;
	}

	public	User(String name, Integer balance) {
		this.name = name;
		this.balance = balance;
	}

	public Integer	deposit(Integer value) {
		this.balance += value;
		return (this.balance);
	}

	@Override
	public String	toString() {
		return ("{name: " + this.name + ", balance: " + this.balance + "}");
	}
}

