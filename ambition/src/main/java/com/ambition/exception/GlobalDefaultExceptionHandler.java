package com.ambition.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

	@ExceptionHandler(NoHandlerFoundException.class)
	public ModelAndView handlerNoHandlerFoundException() {

		ModelAndView mav = new ModelAndView("error");
		mav.addObject("errorTitle", "The page is not constructed!");

		mav.addObject("errorDescription", "The page you are looking for is not available now!");

		mav.addObject("title", "404 Error Page");
		return mav;
	}

	@ExceptionHandler(ProductNotFoundException.class)
	public ModelAndView handlerProductNotFoundException() {

		ModelAndView mav = new ModelAndView("error");
		mav.addObject("errorTitle", "Product is not avialble");

		mav.addObject("errorDescription", "The product you are looking for is not available right now!");

		mav.addObject("title", "Product Unavailble");
		return mav;
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handlerException(Exception ex) {

		ModelAndView mav = new ModelAndView("error");

		mav.addObject("errorTitle", "Contact Your Administrator!!");

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);

		mav.addObject("errorDescription", sw.toString());

		mav.addObject("title", "Error");

		return mav;
	}

}
