
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

