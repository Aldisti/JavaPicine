
package edu.school42.chat.repositories;

import edu.school42.chat.models.*;
import java.sql.*;
import javax.sql.*;
import java.util.*;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {

	private DataSource	ds;
	private Connection	con;

	public	MessagesRepositoryJdbcImpl(DataSource ds) throws SQLException {
		this.ds = ds;
		this.con = ds.getConnection();
	}

	public void					closeConnection() {
		try {
			this.con.close();
		}
		catch (SQLException e) {
			System.out.println("'closeConnection' says: " + e.getMessage());
			System.exit(1);
		}
	}

	private User				getUser(long id) {
		String		query = "SELECT * FROM chat.user WHERE id = ";
		ResultSet	rs;

		try {
			rs = this.con.prepareStatement(query + id).executeQuery();
			while (rs.next()) {
				return (new User(rs.getLong("id"), rs.getString("login"), rs.getString("pass")));
			}
		}
		catch (Exception e) {
			System.out.println("'getUser' says: " + e.getMessage());
			this.closeConnection();
			System.exit(1);
		}
		return (null);
	}

	private Chatroom			getRoom(long id) {
		String		query = "SELECT * FROM chat.room WHERE id = ";
		ResultSet	rs;

		try {
			rs = this.con.prepareStatement(query + id).executeQuery();
			while (rs.next()) {
				return (new Chatroom(rs.getLong("id"), rs.getString("name"), null));
			}
		}
		catch (Exception e) {
			System.out.println("'getUser' says: " + e.getMessage());
			this.closeConnection();
			System.exit(1);
		}
		return (null);
	}

	@Override
	public void					update(Message msg) {
		String		query = "UPDATE chat.message WHERE id = " + msg.getId() + " SET author = ";

		if (msg.getAuthor() == null || msg.getRoom() == null
			|| msg.getAuthor().getId() == -1 || msg.getRoom().getId() == -1) {
			throw new NotSavedSubEntityException("Not Saved Sub Entity Exception");
		}
		query += msg.getAuthor().getId() + ", room = " + msg.getRoom().getId();
		query += ", text = " + msg.getText();
		query += ", time = " + msg.getTime();
		System.out.println("query: " + query);
		try {
			this.con.prepareStatement(query).execute();
		}
		catch (SQLException e) {
			System.out.println("'update' says: " + e.getMessage());
			this.closeConnection();
			System.exit(1);
		}
	}

	@Override
	public void					save(Message msg) {
		String		query = "INSERT INTO chat.message(author, room, text, time) VALUES (";

		ResultSet	rs;

		if (msg.getAuthor().getId() == -1 || msg.getRoom().getId() == -1) {
			throw new NotSavedSubEntityException("Not Saved Sub Entity Exception");
		}
		try {
			rs = this.con.prepareStatement("SELECT * FROM chat.user WHERE id = " + msg.getAuthor().getId()).executeQuery();
			if (!rs.next()) {
				throw new NotSavedSubEntityException("user(id = " + msg.getAuthor().getId() + ") not found");
			}
			query += msg.getAuthor().getId() + ", ";
			rs = this.con.prepareStatement(("SELECT * FROM chat.room WHERE id = " + msg.getRoom().getId())).executeQuery();
			if (!rs.next()) {
				throw new NotSavedSubEntityException("chatroom(id = " + msg.getRoom().getId() + ") not found");
			}
			query += msg.getRoom().getId() + ", '" + msg.getText() + "', '" + msg.getTime() + "')";
			this.con.prepareStatement(query).execute();
			rs = this.con.prepareStatement("SELECT * FROM chat.message",
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery();
			rs.last();
			msg.setId(rs.getLong("id"));
		}
		catch (SQLException e) {
			System.out.println("'save' says: " + e.getMessage());
			this.closeConnection();
			System.exit(1);
		}
	}

	@Override
	public Optional<Message>	findById(long id) {
		String		query = "SELECT * FROM chat.message WHERE id = ";
		ResultSet	rs;

		try {
			rs = this.con.prepareStatement(query + id).executeQuery();
			while (rs.next()) {
				return (Optional.of(new Message(rs.getLong("id"), this.getUser(rs.getLong("author")),
						this.getRoom(rs.getLong("room")), rs.getString("text"), rs.getString("time"))));
			}
		}
		catch (Exception e) {
			System.out.println("'findById' says: " + e.getMessage());
			this.closeConnection();
			System.exit(1);
		}
		return (null);
	}
}

