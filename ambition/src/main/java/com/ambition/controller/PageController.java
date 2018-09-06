package com.ambition.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ambitionbackend.dao.CategoryDAO;
import com.ambitionbackend.dao.ProductDAO;
import com.ambitionbackend.dto.Category;
import com.ambitionbackend.dto.Product;


@Controller
public class PageController {

	@Autowired
	private CategoryDAO categoryDAO;
	
	
	@Autowired
	private ProductDAO productDAO;
	
	@RequestMapping(value = { "/", "/home", "/index" })
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("page");
		mav.addObject("title","Home");
		//passing object
		mav.addObject("categories", categoryDAO.list());
		mav.addObject("userClickHome",true);
		return mav;

	}
	
	@RequestMapping(value = "/about" )
	public ModelAndView about() {
		ModelAndView mav = new ModelAndView("page");
		mav.addObject("title","About Us");
		mav.addObject("userClickAbout",true);
		return mav;

	}
	
	@RequestMapping(value = "/contact")
	public ModelAndView contact() {
		ModelAndView mav = new ModelAndView("page");
		mav.addObject("title","Contact Us");
		mav.addObject("userClickContact",true);
		return mav;

	}
/*
 * method to load all the products
 */
	@RequestMapping(value = { "show/all/products" })
	public ModelAndView showAllProducts() {
		ModelAndView mav = new ModelAndView("page");
		mav.addObject("title","All Products");
		//passing object
		mav.addObject("categories", categoryDAO.list());
		mav.addObject("userClickAllProducts",true);
		return mav;

	}
	
	@RequestMapping(value = { "show/category/{id}/products" })
	public ModelAndView showCategoryProducts(@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("page");
		//categoryDAO to fetch single products
		Category category=null;
		category =categoryDAO.get(id);
		mav.addObject("title",category.getName());
		//passing list of category
		mav.addObject("categories", categoryDAO.list());
		//passing single category
		mav.addObject("category",category);
		mav.addObject("userClickCategoryProducts",true);
		return mav;

	}
	
	
	/*
	 * Viewing a single product
	 * */
	
	@RequestMapping(value = "/show/{id}/product") 
	public ModelAndView showSingleProduct(@PathVariable int id) {
		
		ModelAndView mv = new ModelAndView("page");
		
		Product product = productDAO.get(id);
		
		// update the view count
		product.setViews(product.getViews() + 1);
		productDAO.update(product);
		//---------------------------
		
		mv.addObject("title", product.getName());
		mv.addObject("product", product);
		
		mv.addObject("userClickShowProduct", true);
		
		
		return mv;
		
	}
}
