package edu.school42.repositories;

import edu.school42.models;

import java.util.List;
import java.util.Optional;

interface ProductsRepository {
	List<Product>		findAll();
	Optional<Product>	findById(Long id);
	void				update(Product product);
	void				save(Product product);
	void				delete (Long id);
}

