
package edu.school42.chat.repositories;

import edu.school42.chat.models.*;
import java.sql.*;
import javax.sql.*;

public interface MessagesRepository {
	Optional<Message>	findById(Long id);
	void				delete(Message msg);
	void				save(Message msg);
	void				update(Message msg);
	List<Message>		findAll();
}

public class MessagesRepositoryJdbcImpl implements MessagesRepository {

	private DataSource	ds;

	private MessagesRepositoryJdbcImpl(DataSource ds) {
		this.ds = ds;
	}

	private User				getUser(Connection con, long id) {
		String		query = "SELECT * FROM chat.user WHERE id = ";
		ResultSet	rs;

		try {
			rs = con.prepareStatement(query + id).executeQuery();
			while (rs.next()) {
				return (new User(rs.getLong("id"), rs.getString("login"), rs.getString("pass")));
			}
		}
		catch (Exception e) {
			System.out.println("'getUser' says: " + e.getMessage());
			System.exit(1);
		}
		return (null);
	}

	private Chatroom			getRoom(Connection con, long id) {
		String		query = "SELECT * FROM chat.room WHERE id = ";
		ResultSet	rs;

		try {
			rs = con.prepareStatement(query + id).executeQuery();
			while (rs.next()) {
				return (new Chatroom(rs.getLong("id"), rs.getString("name"), null));
			}
		}
		catch (Exception e) {
			System.out.println("'getUser' says: " + e.getMessage());
			System.exit(1);
		}
		return (null);
	}

	public Optional<Message>	findById(long id) {
		String		query = "SELECT * FROM chat.messages WHERE id = ";
		Connection	con;
		ResultSet	rs;

		try {
			con = this.ds.getConnection();
			rs = con.prepareStatement(query + id).executeQuery();
			while (rs.next()) {
				return (Optional.of(new Message(rs.getLong("id"), this.getUser(con, rs.getLong("author")),
						this.getChatroom(con, rs.getLong("room")), rs.getString("text"), rs.getString("time"))));
			}
		}
		catch (Exception e) {
			System.out.println("'findById' says: " + e.getMessage());
			System.exit(1);
		}
		return (null);
	}

//public List<Message>	findAll() {
//	String			query = "SELECT * FROM chat.messages";
//	Connection		con;
//	ResultSet		rs;
//	List<Message>	messages = null;

//	try {
//		con = this.ds.getConnection();
//		rs = con.prepareStatement(query).executeQuery();
//		messages = new LinkedList<Message>();
//		while (rs.next()) {
//			messages.add(new Message(rs.getLong("id"), rs.getLong("author"),
//					rs.getLong("room"), rs.getString("text"), rs.getString("time")));
//		}
//	}
//	catch (Exception e) {
//		System.out.println("'findAll' says: " + e.getMessage());
//		System.exit(1);
//	}
//	return (messages);
//}
}

