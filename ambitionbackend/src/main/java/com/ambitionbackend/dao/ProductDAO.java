package com.ambitionbackend.dao;

import java.util.List;

import com.ambitionbackend.dto.Product;

public interface ProductDAO {
	Product get(int ProductId);
	List<Product> list();
	boolean add(Product product);
	boolean update(Product product);
	boolean delete(Product product);
	
	// business class
	List<Product> listActiveProducts();	
	List<Product> listActiveProductsByCategory(int categoryId);
	List<Product> getLatestActiveProducts(int count);

}
