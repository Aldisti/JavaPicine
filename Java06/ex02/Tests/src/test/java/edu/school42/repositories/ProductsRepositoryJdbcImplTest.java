package edu.school42.repositories;

import edu.school42.models.Product;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import java.sql.*;
import javax.sql.*;
import java.util.List;
import java.util.LinkedList;
import java.util.Arrays;

class ProductsRepositoryJdbcImplTest {

	private DataSource					ds;
	private ProductsRepositoryJdbcImpl	prj;

	final List<Product>	EXPECTED_FIND_ALL_PRODUCTS = new LinkedList<Product>(Arrays.toList(
			new Product(1L, "Galaxy S21 5G", 600),
			new Product(2L, "Gear S3 Frontier", 300),
			new Product(3L, "Mi True Earbuds", 25),
			new Product(4L, "Lenovo Legion 5", 1500),
			new Product(5L, "Gan iCarry 365", 40)));
	final Product	EXPECTED_FIND_BY_ID_PRODUCT_1 = new Product(1L, "Galaxy S21 5G", 600);
	final Product	EXPECTED_FIND_BY_ID_PRODUCT_2 = new Product(3L, "Mi True Earbuds", 25);
	final Product	EXPECTED_FIND_BY_ID_PRODUCT_3 = new Product(4L, "Lenovo Legion 5", 1500);
	final Product	EXPECTED_UPDATED_PRODUCT = new Product(1L, "Missile", 10000);

	@BeforeEach
	public void		init() throws SQLException {
		this.ds = new EmbeddedDatabaseBuilder()
			.setType(EmbeddedDatabaseType.HSQL)
			.addScript("/schema.sql")
			.addScript("/data.sql")
			.build();
		this.prj = new ProductsRepositoryJdbcImpl(ds);
	}

	@Test
	public void		findAllTest() {
		assertEquals(EXPECTED_FIND_ALL_PRODUCTS, this.prj.findAll());
	}

	@Test
	public void		findByIdTest() {
		assertEqual(EXPECTED_FIND_BY_ID_PRODUCT_1, this.prj.findById(1L).get());
		assertEqual(EXPECTED_FIND_BY_ID_PRODUCT_2, this.prj.findById(3L).get());
		assertEqual(EXPECTED_FIND_BY_ID_PRODUCT_3, this.prj.findById(4L).get());
	}
}

