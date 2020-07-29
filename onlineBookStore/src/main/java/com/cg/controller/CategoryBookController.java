package com.cg.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entity.Book;
import com.cg.entity.Category;
import com.cg.service.AdminService;
@RestController
@RequestMapping("/bookstore")
@CrossOrigin(origins="http://localhost:4200", maxAge=3600)
public class CategoryBookController {
	
	@Autowired
	private AdminService service;
	
	
	@PostMapping("/create")
	public ResponseEntity<String> addCategory(@RequestBody Category category ) {
		    service.addCategory(category);
			ResponseEntity<String> responseEntity = new ResponseEntity<String>("Added succesfully",HttpStatus.OK);
			return responseEntity;
	}

	
	@PostMapping("/createbook")
	public Book addBook(@RequestBody Book book ) {
			Book b=service.addBook(book);
			return b;
	}
	
	@PutMapping("/assignBookToCat/{categoryId}/{book_id}")
	public Book assignbooktoC(@PathVariable(value="categoryId")int categoryId,
			@PathVariable(value="book_id")int book_id) {
		return service.btoC(categoryId, book_id);
		
	}
	/*
	@PutMapping("/UpdateCategory")
	public Category updateC(@RequestBody Category category) {
		return service.UpdateCategory(category);
		
	}*/
	
	@PutMapping("/UpdateCategory/{categoryId}/{categoryName}")
	public Category updateC(@PathVariable("categoryId") int categoryId,@PathVariable("categoryName") String categoryName) {
		return service.UpdateCategory(categoryId,categoryName);
		
	}

	
	
	@GetMapping("/categoryID/{categoryId}")
	public List<Book> getCategoryByID(@PathVariable("categoryId") int categoryId) {
		List<Book> cat=service.getCategoryBId(categoryId);
		return cat;
		
	}
	
	@GetMapping("/GetAllCategory")
	public ResponseEntity<List<Category>> getAllCategory() {
		List<Category> catlist = service.getAllCategory();
		return new ResponseEntity<List<Category>>(catlist, new HttpHeaders(), HttpStatus.OK);
		
	}
	
	@DeleteMapping(path="/deleteCat/{categoryId}")
	public ResponseEntity<String> removeCategory(@PathVariable int categoryId) {
		
		ResponseEntity<String> rs =  new ResponseEntity<String>(service.removeCategory(categoryId),HttpStatus.OK);
		
		return rs;
	} 
	
	@PutMapping("/UpdateBook")
	public Book updateBook(@RequestBody Book book) {
		Book b = service.updateBook(book);
		return b;
	}
	
	@GetMapping("/GetAllBook")
	public ResponseEntity<List<Book>> getAllBook() {
		List<Book> booklist = service.listOfBook();
		return new ResponseEntity<List<Book>>(booklist, new HttpHeaders(), HttpStatus.OK);

	}
	
	@DeleteMapping(path="/deleteBook/{book_id}")
	public ResponseEntity<String> removeBook(@PathVariable int book_id) {
		
		ResponseEntity<String> rs =  new ResponseEntity<String>(service.removeBook(book_id),HttpStatus.OK);
		
		return rs;
	} 
	
	
}
