package edu.school42.models;

import java.util.StringJoiner;

public class User {

	private String	name;
	private Integer	balance;

	public	User() {
		this.name = "Default";
		this.balance = 0;
	}

	public	User(String name, Integer balance) {
		this.name = name;
		this.balance = balance;
	}

	public String	deposit(Integer value) {
		this.balance += value;
		return ("Your new balance is: " + this.balance);
	}

	@Override
	public String	toString() {
		return (new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
			.add("name='" + name + "'")
			.add("balance=" + balance)
			.toString());
	}
}

