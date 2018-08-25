package com.ambitionbackend.test;

import static org.junit.Assert.assertEquals;

import javax.persistence.Column;

import org.junit.BeforeClass;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ambitionbackend.dao.ProductDAO;
import com.ambitionbackend.dto.Product;

public class ProductTestCase {
	
	private static AnnotationConfigApplicationContext context;
	private static ProductDAO productDAO;
	private Product product;
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.ambitionBackend");
		context.refresh();
		productDAO = (ProductDAO) context.getBean("productDAO");
		
	}
	
	public void testCRUDProduct() {
		//create Operation
		product = new Product();
		product.setName("oppo f1");
		product.setBrand("OPPO");
		product.setDiscription("This is selfi phone");
		product.setUnitPrice(25000);
		product.setActive(true);
		product.setCategoryId(3);
		product.setSupplierId(3);
		
	   assertEquals("somthing went wrong while inserted new product", true , productDAO.add(product));
	   
	   
	   //reading and updating Category
	   
	   product = productDAO.get(2);
	   product.setName("Samsung Galaxy s7");
	   assertEquals("somthing went to wrong while updating the exiting record", true 
			   , productDAO.update(product));
	   
	   assertEquals("somthing went to wrong while deleting the exiting record", true 
			   , productDAO.delete(product));
	   
	   assertEquals("somthing went to wrong while updating the exiting record", 6
			   , productDAO.list().size());
	   
	   
	   

	}

}
