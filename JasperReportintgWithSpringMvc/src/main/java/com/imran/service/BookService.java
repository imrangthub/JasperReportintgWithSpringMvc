package com.imran.service;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.sql.DataSource;
import org.springframework.stereotype.Service;

import com.imran.model.Book;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashMap;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;


@Service
public class BookService {
	
	@Autowired
	DataSource dataSource;
	private Statement stmt;
	
	public List<Book> bookList(){
		List<Book> bookList = new ArrayList<Book>();
		
		String getAllDataQuery = "SELECT * FROM `book` ORDER BY ID"; 
//		   System.out.println(getAllDataQuery);
		try {
			this.stmt = dataSource.getConnection().createStatement();
			ResultSet tempResult = stmt.executeQuery(getAllDataQuery);
			while(tempResult.next()){
				Book bookObj = new Book();
				bookObj.setId(Long.parseLong(tempResult.getString("id")));
				bookObj.setName(tempResult.getString("name"));
				bookObj.setType(tempResult.getString("type"));
				bookList.add(bookObj);
			}
			stmt.close();
			dataSource.getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookList;		
	}
	
	public List<Book>  getById(long id){
		List<Book> singleBook = new ArrayList<Book>();
		String getQuery ="select * from book WHERE id='"+id+"'";
	        System.out.println(getQuery);
		try {
			stmt = dataSource.getConnection().createStatement();
			ResultSet tempResult = stmt.executeQuery(getQuery);
		
			while(tempResult.next()){
				Book obj = new Book();
				obj.setId(Long.parseLong(tempResult.getString("id")));
				obj.setName(tempResult.getString("name"));
				obj.setType(tempResult.getString("type"));
				singleBook.add(obj);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return singleBook;	
	}

}

