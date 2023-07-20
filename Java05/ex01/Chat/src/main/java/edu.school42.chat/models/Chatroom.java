package edu.school42.chat.models;

import java.util.*;

public class Chatroom {

	private long				id;
	private String			name;
	private User			owner;
	private List<Message>	messages;

	public	Chatroom(long id, String name, User owner) {
		this.name = name;
		this.owner = owner;
		this.messages = new LinkedList<Message>();
	}

	public long				getId() {
		return (this.id);
	}

	public String			getName() {
		return (this.name);
	}

	public User				getOwner() {
		return (this.owner);
	}

	public List<Message>	getMessages() {
		return (this.messages);
	}

	public Message			getMessage(long id) {
		for (Message msg : this.messages) {
			if (msg.getId() == id) {
				return (msg);
			}
		}
		return (null);
	}

	public void				addMessage(Message msg) {
		if (msg != null) {
			this.messages.add(msg);
		}
	}

	@Override
	public boolean			equals(Object o) {
		if (this == o) {
			return (true);
		}
		if (o == null || this.getClass() != o.getClass()) {
			return (false);
		}
		Chatroom	c = (Chatroom) o;
		return (this.id == c.getId()
				&& this.name.equals(c.getName())
				&& this.owner.equals(c.getOwner()));
	}

	@Override
	public int				hashCode() {
		return (Objects.hash(this.id, this.name, this.owner));
	}

	@Override
	public String			toString() {
		return ("id: " + this.id + "\tname: " + this.name
				+ "\towner: " + this.owner.getLogin());
	}
}


