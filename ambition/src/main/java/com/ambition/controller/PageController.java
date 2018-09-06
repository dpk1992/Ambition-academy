package com.ambition.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ambitionbackend.dao.CategoryDAO;
import com.ambitionbackend.dto.Category;


@Controller
public class PageController {

	@Autowired
	private CategoryDAO categoryDAO;
	
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
}
