package edu.school42.numbers;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

public class EmbeddedDataSourceTest {

	@Test
	public void	checkConnection(int n) {
		EmbeddedDatabaseBuilder	edb = new EmbeddedDatabaseBuilder();
		Connection				con;

		edb.addScripts("/schema.sql", "/data.sql");
		con = edb.getConnection();
		assertNotNull(con);
		assertTrue(con.isValid());
	}
}

