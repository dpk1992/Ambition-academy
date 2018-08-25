package com.ambitionbackend.daoimpl;

import java.util.List;

import javax.management.loading.PrivateClassLoader;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ambitionbackend.dao.ProductDAO;
import com.ambitionbackend.dto.Product;

@Transactional
@Repository("ProductDAO")
public class ProductImpl implements ProductDAO {

	@Autowired
	private SessionFactory sessionFactory;

	// Single product
	@Override
	public Product get(int ProductId) {
		try {
			return sessionFactory.getCurrentSession().get(Product.class, Integer.valueOf(ProductId));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

    //Add list of products
	@Override
	public List<Product> list() {

		return sessionFactory.getCurrentSession().createQuery("From Product", Product.class).getResultList();
	}

   //Insert product
	@Override
	public boolean add(Product product) {
		try {
			sessionFactory.getCurrentSession().persist(product);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// update product
	@Override
	public boolean update(Product product) {
		try {
			sessionFactory.getCurrentSession().update(product);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// Soft delete
	@Override
	public boolean delete(Product product) {
		try {
			product.setActive(false);
			return this.update(product);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Product> listActiveProducts() {
		String setActiveProduct = "FROM Product WHERE active = :active";
		
		return sessionFactory.getCurrentSession()
						.createStoredProcedureCall(setActiveProduct, Product.class)
						.setParameter("active", true)
						.getResultList();
				
	}

	@Override
	public List<Product> listActiveProductsByCategory(int categoryId) {
String setActiveProductByCategory = "FROM Product WHERE active = :active And categoryId = :categoryId";
		
		return sessionFactory.getCurrentSession()
						.createStoredProcedureCall(setActiveProductByCategory, Product.class)
						.setParameter("active", true)
						.setParameter("categoryId", true)
						.getResultList();
		
	}

	@Override
	public List<Product> getLatestActiveProducts(int count) {
		return sessionFactory.getCurrentSession()
				.createStoredProcedureCall("FROM PRODUCT WHERE active = :active ORDER BY Id", Product.class)
				.setParameter("active", true)
				.setFirstResult(0)
				.setMaxResults(count)
				.getResultList();
	}

}
