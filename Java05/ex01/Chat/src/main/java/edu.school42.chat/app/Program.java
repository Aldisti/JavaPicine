
package edu.school42.chat.app;

import edu.school42.chat.models.*;
import edu.school42.chat.repositories.*;
import java.util.*;
import java.io.*;
import java.sql.*;
import javax.sql.*;
import com.zaxxer.hikari.*;

public class Program {
	
	private static HikariDataSource	ds;
	private static HikariConfig		config = new HikariConfig();

	static {
		config.setJdbcUrl("jdbc:postgresql://127.0.0.1:5432/chatrooms");
		config.setUsername("admin");
		config.setPassword("admin");
		ds = new HikariDataSource(config);
	}

	public void					loadSql(String path) {
		BufferedReader	file;
		Connection		con;
		String			line;

		try {
			file = new BufferedReader(this.getClass().getClassLoader().getResourceAsStream(path));
			con = ds.getConnection();
			while ((line = file.readLine()) != null) {
				while (!line.endsWith(";")) {
					line += file.readLine();
				}
				con.prepareStatement(line.replace(";", " ")).executeQuery();
			}
		}
		catch (Exception e) {
			System.out.println("'loadSql' says: " + e.getMessage());
			System.exit(1);
		}
	}

	public static void			main(String[] args) {
		Program						inst = new Program();
		Scanner						kb = new Scanner(System.in);
		MessagesRepositoryJdbcImpl	mrj;
		Message						tmp;

		inst.loadSql("schema.sql");
		inst.loadSql("data.sql");
		mrj = new MessagesRepositoryJdbcImpl(ds);
		System.out.print("->");
		try {
			System.out.println(mrj.findById(kb.nextLong()).toString());
		}
		catch (Exception e) {
			System.out.println("'main' says: " + e.getMessage());
			System.exit(1);
		}
	}
}

