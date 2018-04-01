package com.imran.controller;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.imran.model.Book;
import com.imran.service.BookService;

@Controller
@RequestMapping(value = "/book")
public class BookController {
	
	@Autowired
	BookService bookService;


	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(ModelMap map) {
		List<Book> bookList = new ArrayList<Book>();
		bookList = bookService.bookList();
		map.addAttribute("bookList", bookList);
		return "bookHome";
	}
	
	@RequestMapping(value = "/singleShow", method = RequestMethod.GET)
	public String singlePrint(ModelMap map, HttpServletRequest reqParams) {		
		     System.out.println("Book Id:"+reqParams.getParameter("id"));
		     long id = Long.parseLong(reqParams.getParameter("id"));
		     
		 	Book singleObj = null;
			List<Book> bookList =  bookService.getById(id);
			for(Book b: bookList){
				singleObj = b;
			}
			if(singleObj == null){
				map.addAttribute("message", "Post not found");	
				return "redirect:/book/";
			}
			map.put("singleObj",singleObj);

		return "singlePrint";
	}


}

