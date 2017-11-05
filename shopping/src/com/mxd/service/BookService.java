package com.mxd.service;

import java.util.ArrayList;

import com.mxd.domain.Book;
import com.mxd.utils.SqlHelper;

public class BookService {
	
	public Book getBookById(String id){
		String sql = "select * from book where id = ?";
		String[] paras = {id};
		Book book = new Book();
		ArrayList al = new SqlHelper().executeQuery(sql, paras);
		if(al.size()==1){
			Object[]  object = (Object[]) al.get(0);
			book.setId(Integer.parseInt(object[0].toString()));
			book.setName(object[1].toString());
			book.setAuthor(object[2].toString());
			book.setPublishHouse(object[3].toString());
			book.setPrice(Float.parseFloat(object[4].toString()));
			book.setNums(Integer.parseInt(object[5].toString()));
		}
		
		return book;
	}
	
	public  ArrayList<Book> getAllBook(){
		String sql = "select * from book where 1 = ?";
		String[] paras = {"1"};
		ArrayList al = new SqlHelper().executeQuery(sql, paras);
		
		ArrayList<Book> newAl = new ArrayList<Book>();
		for(int i = 0;i<al.size();i++){
			Object[]  object = (Object[]) al.get(i);
			Book book = new Book();
			book.setId(Integer.parseInt(object[0].toString()));
			book.setName(object[1].toString());
			book.setAuthor(object[2].toString());
			book.setPublishHouse(object[3].toString());
			book.setPrice(Float.parseFloat(object[4].toString()));
			book.setNums(Integer.parseInt(object[5].toString()));
			newAl.add(book);
		}
		return newAl;
	}
}
