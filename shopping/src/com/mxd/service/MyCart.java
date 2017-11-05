package com.mxd.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.mxd.domain.Book;

public class MyCart {
	HashMap<String , Book> myCart = new HashMap<String , Book>();
	
	public void addBook(String id){
		if(myCart.containsKey(id)){
			Book book = myCart.get(id);
			int shoppingNum = book.getShoppingNum();
			book.setShoppingNum(shoppingNum+1);
		}else{
			myCart.put(id,new BookService().getBookById(id));
		}
	}
	
//	public void addBook(String id , Book book){
//		if(myCart.containsKey(id)){
//			int shoppingNum = book.getShoppingNum();
//			book.setShoppingNum(shoppingNum+1);
//		}
//		myCart.put(id, book);
//	}
	
	public void delBook(String id ){
		myCart.remove(id);
	}
	
	public void updateBook(String id , String num){
		Book book = myCart.get(id);
		book.setShoppingNum(Integer.valueOf(num));
	}
	
	public void clearBook(String id , Book book){
		myCart.clear();
	}
	
	public ArrayList showMyCart(){
		Iterator iterator = myCart.keySet().iterator();
		ArrayList<Book> al = new ArrayList<Book>();
		while(iterator.hasNext()){
			String id = (String)iterator.next();
			Book book = myCart.get(id);
			al.add(book);
		}
		return al;
	}
	
	public int getTotalSum(){
		Iterator iterator = myCart.keySet().iterator();
		int totalSum = 0;
		while(iterator.hasNext()){
			String id = (String)iterator.next();
			Book book = myCart.get(id);
			totalSum = totalSum + book.getShoppingNum();
		}
		return totalSum;
	}
	
	public float getTotalPrice(){
		Iterator iterator = myCart.keySet().iterator();
		float totalPrice = 0;
		while(iterator.hasNext()){
			String id = (String)iterator.next();
			Book book = myCart.get(id);
			int shoppingNum = book.getShoppingNum();
			totalPrice = totalPrice + book.getPrice()*shoppingNum;
		}
		return totalPrice;
	}
	
	
}
