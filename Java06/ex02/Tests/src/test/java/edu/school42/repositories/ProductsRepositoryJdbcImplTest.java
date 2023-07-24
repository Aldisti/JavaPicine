package edu.school42.repositories;

import edu.school42.models.Product;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import java.sql.*;
import javax.sql.*;
import java.util.List;

class ProductsRepositoryJdbcImplTest {

	@Test
	public void		findAllTest() {
		ProductsRepositoryJdbcImpl	prj;
		DataSource					ds;
		List<Product>				tmp;

		ds = new EmbeddedDatabaseBuilder()
			.setType(EmbeddedDatabaseType.HSQL)
			.addScript("/schema.sql")
			.addScript("/data.sql")
			.build();
		prj = new ProductsRepositoryJdbcImpl(ds);
		tmp = prj.findAll();
		for (Product p : tmp) {
			System.out.println(p);
		}
	}
}

