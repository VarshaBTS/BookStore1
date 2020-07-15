package com.cg.service;

import com.cg.entity.Book;
import com.cg.entity.Category;
public interface CategoryIService {
	Category addCategory (Category category);
	Book addBook (Book book);
	Book btoC(int cid,int bid);
	public Category UpdateCategory(Category c);

}
