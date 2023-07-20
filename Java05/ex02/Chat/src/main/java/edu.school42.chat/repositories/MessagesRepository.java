
package edu.school42.chat.repositories;

import edu.school42.chat.models.*;
import java.sql.*;
import javax.sql.*;
import java.util.*;

public interface MessagesRepository {
	Optional<Message>	findById(long id);
//	void				delete(Message msg);
	void				save(Message msg);
//	void				update(Message msg);
//	List<Message>		findAll();
}

