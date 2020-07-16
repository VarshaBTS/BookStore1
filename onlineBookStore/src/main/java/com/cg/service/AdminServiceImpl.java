package com.cg.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dao.BookyDao;
import com.cg.dao.CategoryDao;
import com.cg.entity.Book;
import com.cg.entity.Category;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
	@Autowired
	private CategoryDao dao;
	
	@Autowired
	private BookyDao dao1;
	
	@Override
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

	@Override
	public Optional<Category> getCategoryById(int cid) {
		
		return dao.findById(cid);
	}

	@Override
	public List<Category> getAllCategory(){
		return dao.findAll();
	}

	@Override
	public Book updateBook(Book book) {
		Book b=dao1.getOne(book.getBook_id());
		if(b!=null)
		{
			b.setTitle(book.getTitle());
			b.setAuthor(book.getAuthor());
			b.setDescription(book.getDescription());
			b.setISBN(book.getISBN());
			b.setPrice(book.getPrice());
			b.setPublished_Date(book.getPublished_Date());
			b.setIcon(book.getIcon());
		}
		return dao1.save(b);
	}

	@Override
	public List<Book> listOfBook() {
		// TODO Auto-generated method stub
		return dao1.findAll();
	}
	
	
	



}
