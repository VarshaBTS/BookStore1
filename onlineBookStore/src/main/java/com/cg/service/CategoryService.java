package com.cg.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dao.BookyDao;
import com.cg.dao.CategoryDao;
import com.cg.entity.Book;
import com.cg.entity.Category;

@Service
@Transactional
public class CategoryService implements CategoryIService {
	@Autowired
	private CategoryDao dao;
	
	@Autowired
	private BookyDao dao1;
	
	public Category addCategory(Category category) {
	return dao.save(category);
	}

	@Override
	public Book addBook(Book book) {
		return dao1.save(book);
	}

	@Override
	public Book btoC(int cid, int bid) {
		
		Book b=dao1.getOne(bid);
		if(b!=null) {
			Category c=dao.getOne(cid);
			b.setCategory(c);
		}
		return dao1.save(b);
	}

	@Override
	public Category UpdateCategory(Category c) {
		Category ct=dao.getOne(c.getCategoryId());
		if(ct!=null) {
			ct.setCategoryName(c.getCategoryName());
		}
		return dao.save(ct);
	}
	



}
