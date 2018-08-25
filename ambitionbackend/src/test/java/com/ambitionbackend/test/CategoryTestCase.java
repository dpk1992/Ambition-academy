package com.ambitionbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ambitionbackend.dao.CategoryDAO;
import com.ambitionbackend.dto.Category;

public class CategoryTestCase {
	
	public static AnnotationConfigApplicationContext context;
	
	public static CategoryDAO categoryDAO;
	
	public Category category;
	
	@BeforeClass
	public static void init()
	
	{
		context = new AnnotationConfigApplicationContext();
		context.scan("com.ambitionbackend");
		context.refresh();
		
		categoryDAO = (CategoryDAO)context.getBean("categoryDAO");
	}

	/*	
	@Test
	public void testAddCategory(){
		
		category = new Category();
		category.setName("Television");
		category.setDescription("this is description of tv");
		category.setImageUrl("CAT_4");
		
		assertEquals("successfully added",true,categoryDAO.add(category));
	}
	
	 @Test
	 public void testGetCategory(){
		 category = categoryDAO.get(3);
		 assertEquals("successfully fatche","Television",category.getName());
	 }
	
	 @Test
	 public void testUpdateCategory(){
		 category = categoryDAO.get(1);
		 category.setName("Mobile");
		 assertEquals("successfully updated ",true,categoryDAO.update(category));
	 }
	
	 @Test
	 public void testUpdateCategory(){
		 category = categoryDAO.get(1);
		 
		 assertEquals("successfully delete ",false,categoryDAO.delete(category));
	 }
	 
	
	 @Test
	 public void testListCategory(){
		 
		 
		 assertEquals("successfully fatche the list of category ",2,categoryDAO.list().size());
	 }*/
	
	
	@Test
	public void testCRUDCategory(){
		
		//Add operation
		category = new Category();
		category.setName("Laptop");
		category.setDescription("this is description of Laptop");
		category.setImageUrl("CAT_1");
		
		assertEquals("successfully added inside the table",true,categoryDAO.add(category));
		
		category = new Category();
		category.setName("Mobile");
		category.setDescription("this is description of MObile");
		category.setImageUrl("CAT_2");
		
		assertEquals("successfully added inside the table",true,categoryDAO.add(category));
		
		category = new Category();
		category.setName("Television");
		category.setDescription("this is description of Television");
		category.setImageUrl("CAT_3");
		
		assertEquals("successfully added inside the table",true,categoryDAO.add(category));
		
		//fetching and updating  the category
		category = categoryDAO.get(3);
		category.setName("Radio");
		assertEquals("successfully updated ",true,categoryDAO.update(category));
		 
		 //deleting the single category 
          category = categoryDAO.get(1);
		 
		 assertEquals("successfully delete ",true,categoryDAO.delete(category));
		 
		 //fetching the list
		 assertEquals("successfully fatche the list of category ",14,categoryDAO.list().size());
	}
}
