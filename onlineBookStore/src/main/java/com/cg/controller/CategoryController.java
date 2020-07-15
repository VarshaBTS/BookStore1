package com.cg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entity.Book;
import com.cg.entity.Category;
import com.cg.service.CategoryService;
@RestController
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private CategoryService service;
	
	@PostMapping("/create")
	public ResponseEntity<Category> addCategory(@RequestBody Category category ) {
			Category cat=service.addCategory(category);
			ResponseEntity<Category> responseEntity = new ResponseEntity<Category>(cat,HttpStatus.OK);
			return responseEntity;
		}

	
	@PostMapping("/createbook")
	public Book addBook(@RequestBody Book book ) {
			Book b=service.addBook(book);
			//ResponseEntity<Book> responseEntity = new ResponseEntity<Book>(b,HttpStatus.OK);
			return b;
		}
	
	@PutMapping("/test/{CategoryId}/{book_id}")
	public Book assignbooktoC(@PathVariable(value="CategoryId")int CategoryId,
			@PathVariable(value="book_id")int book_id) {
		return service.btoC(CategoryId, book_id);
		
	}
	
	
}
