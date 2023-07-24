package edu.school42.repositories;

import edu.school42.models.Product;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.sql.*;
import javax.sql.*;

public class ProductsRepositoryJdbcImpl implements ProductsRepository {

	private DataSource	ds;
	private Connection	con;

	public	ProductsRepositoryJdbcImpl(DataSource ds) throws SQLException {
		this.ds = ds;
		this.con = ds.getConnection();
	}

	@Override
	public List<Product>		findAll() {
		List<Product>	tmp = new ArrayList<Product>();
		ResultSet		rs;

		try {
			rs = this.con.prepareStatement("SELECT * FROM products").executeQuery();
			while (rs.next()) {
				tmp.add(new Product(rs.getLong("id"), rs.getString("name"), rs.getInt("price")));
			}
		}
		catch (Exception e) {
			System.out.println("'findAll' says: \"" + e.getMessage() + "\"");
		}
		return (tmp);
	}

	@Override
	public Optional<Product>	findById(Long id) {
		ResultSet	rs;

		if (id == null) {
			return (Optional.empty());
		}
		try {
			rs = this.con.prepareStatement("SELECT * FROM products WHERE id = " + id).executeQuery();
			while (rs.next()) {
				return (Optional.of(new Product(rs.getLong("id"), rs.getString("name"), rs.getInt("price"))));
			}
		}
		catch (Exception e) {
			System.out.println("'findById' says: \"" + e.getMessage() + "\"");
		}
		return (Optional.empty());
	}

	@Override
	public void					update(Product product) {
		String	query = "UPDATE products SET name = ";

		if (product.getId() == null) {
			return ;
		}
		if (product.getName() != null) {
			query += "'" + product.getName() + "'";
		}
		else {
			query += "'null'";
		}
		query += ", price = " + product.getPrice();
		query += " WHERE id = " + product.getId();
		try {
			this.con.prepareStatement(query).execute();
		}
		catch (Exception e) {
			System.out.println("'update' says: \"" + e.getMessage() + "\"");
		}
	}

	@Override
	public void					save(Product product) {
		String	query = "INSERT INTO products(id, name, price) VALUES";
		if (this.findById(product.getId()).isPresent()) {
			return ;
		}
		query += " (" + product.getId();
		if (product.getName() != null) {
			query += ", " + product.getName();
		}
		else {
			query += ", " + "'null'";
		}
		query += ", " + product.getPrice() + ")";
		System.out.println("\nsave: " + query + "\n");
		try {
			this.con.prepareStatement(query).execute();
		}
		catch (Exception e) {
			System.out.println("'save' says: \"" + e.getMessage() + "\"");
		}
	}

	@Override
	public void					delete(Long id) {
		if (!this.findById(id).isPresent()) {
			return ;
		}
		try {
			this.con.prepareStatement("DELETE FROM products WHERE id = " + id).execute();
		}
		catch (Exception e) {
			System.out.println("'save' says: \"" + e.getMessage() + "\"");
		}
	}
}

