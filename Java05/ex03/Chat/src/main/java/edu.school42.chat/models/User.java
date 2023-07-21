package edu.school42.chat.models;

import java.util.*;

public class User {

	private long				id;
	private String			login;
	private String			password;
	private List<Chatroom>	created;
	private List<Chatroom>	socialize;

	public	User(long id, String login, String password) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.created = new ArrayList<Chatroom>();
		this.socialize= new ArrayList<Chatroom>();
	}

	public	User(Object id, String login, String password) {
		if (id != null) {
			throw new InvalidParamsException("User invalid params exception");
		}
		this.id = -1;
		this.login = login;
		this.password = password;
	}

	public long				getId() {
		return (this.id);
	}

	public String			getLogin() {
		return (this.login);
	}

	public List<Chatroom>	getCreated() {
		return (this.created);
	}

	public List<Chatroom>	getSocialize() {
		return (this.socialize);
	}

	private String			getPassword() {
		return (this.password);
	}

	public void				addCreated(Chatroom chat) {
		this.created.add(chat);
	}

	public void				addSocialize(Chatroom chat) {
		this.socialize.add(chat);
	}

	@Override
	public boolean			equals(Object o) {
		if (this == o) {
			return (true);
		}
		if (o == null || this.getClass() != o.getClass()) {
			return (false);
		}
		User	u = (User) o;
		return (this.id == u.getId()
				&& this.login.equals(u.getLogin())
				&& this.password.equals(u.getPassword()));
	}

	@Override
	public int				hashCode() {
		return (Objects.hash(this.id, this.login, this.password));
	}

	@Override
	public String			toString() {
		return ("id: " + this.id + "\tlogin: " + this.login
				+ "\tpassword: " + this.password + "\tcreated: "
				+ this.created.size() + "\tsocialize: " + this.socialize.size());
	}
}
