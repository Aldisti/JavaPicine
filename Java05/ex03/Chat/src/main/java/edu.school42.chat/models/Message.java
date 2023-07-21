package edu.school42.chat.models;

import java.util.*;

public class Message {

	private long			id;
	private User		author;
	private Chatroom	room;
	private String		text;
	private String		time;

	public	Message(long id, User author, Chatroom room, String text, String time) {
		this.id = id;
		this.author = author;
		this.room = room;
		this.text = text;
		this.time = time;
	}

	public	Message(Object id, User author, Chatroom room, String text, String time) {
		if (id != null) {
			throw new InvalidParamsException("Message invalid params exception");
		}
		this.id = -1;
		this.author = author;
		this.room = room;
		this.text = text;
		this.time = time;
	}

	public long			getId() {
		return (this.id);
	}

	public User			getAuthor() {
		return (this.author);
	}

	public Chatroom		getRoom() {
		return (this.room);
	}

	public String		getText() {
		return (this.text);
	}

	public String		getTime() {
		return (this.time);
	}

	public void			setId(long id) {
		this.id = id;
	}

	public void			setText(String text) {
		this.text = text;
	}

	public void			setDateTime(String time) {
		this.time = time;
	}

	@Override
	public boolean		equals(Object o) {
		if (this == o) {
			return (true);
		}
		if (o == null || this.getClass() != o.getClass()) {
			return (false);
		}
		Message	m = (Message) o;
		return (this.id == m.getId()
				&& this.author.equals(m.getAuthor())
				&& this.room.equals(m.getRoom())
				&& this.text.equals(m.getText())
				&& this.time.equals(m.getTime()));
	}

	@Override
	public int		hashCode() {
		return (Objects.hash(this.id, this.author, this.room, this.text, this.time));
	}

	@Override
	public String	toString() {
		return ("id: " + this.id + "\tauthor: " + this.author.getLogin()
				+ "\troom: " + this.room.getName() + "\ttime: " + this.time);
	}
}
