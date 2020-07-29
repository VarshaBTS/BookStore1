package com.cg.service;

import java.util.List;
import java.util.Optional;

import com.cg.entity.Book;
import com.cg.entity.Category;
public interface AdminService {
	Category addCategory (Category category);
	Book addBook (Book book);
	Book btoC(int cid,int bid);
	
	public Category UpdateCategory(Integer c,String n);
	
	List<Category> getAllCategory();
	List<Book> listOfBook();
	public Book updateBook (Book book);
	String removeCategory(Integer categoryId);
	String removeBook(Integer bid);
	
	List<Book> getCategoryBId(int cid);

}
