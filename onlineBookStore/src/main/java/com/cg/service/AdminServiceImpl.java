package com.cg.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dao.BookDao;
import com.cg.dao.CategoryDao;
import com.cg.entity.Book;
import com.cg.entity.Category;
import com.cg.exceptions.CategoryException;
import com.cg.exceptions.CategoryProjectException;
import com.cg.exceptions.ErrorCode;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
	@Autowired
	private CategoryDao dao;
	
	@Autowired
	private BookDao dao1;
	
	private static final String ACTION_1 = "Exception while writing data to persistant layer";
	
	@Override
	public Category addCategory(Category category) {
		 if (validateCategory(category)) {
	            try {
		       dao.save(category);
	            } catch (Exception e) {
	                throw new CategoryProjectException(ErrorCode.SYSTEM_EXCEPTION, ACTION_1, e);
	            }
	            return category;
	        }
	        throw new CategoryException(ErrorCode.BAD_DATA, "Valid date is required");
		
	}

	@Override
	public Book addBook(Book book) {
		if (validateBook(book)) {
            try {
		     dao1.save(book);
            } catch (Exception e) {
                throw new CategoryProjectException(ErrorCode.SYSTEM_EXCEPTION, ACTION_1, e);
            }
		return book;
		 }
        throw new CategoryException(ErrorCode.BAD_DATA, "Valid date is required");
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
		/*Category ct=dao.getOne(c.getCategoryId());
		if(ct!=null) {
			ct.setCategoryName(c.getCategoryName());
		}
		return dao.save(ct);
		*/
		Category ct=dao.getOne(c.getCategoryId());
		if(ct!=null) {
			ct.setCategoryName(c.getCategoryName());
			dao.save(ct);
			if(!validateCategory(c)) {
				throw new CategoryException(ErrorCode.BAD_DATA,"Category Name should not be empty");
			}
		}
		return dao.save(ct);
		
		/*
		if(validateCategory(c)) {
			try {
				Category ct=dao.getOne(c.getCategoryId());
				ct.setCategoryName(c.getCategoryName());
				dao.save(ct);
			}
			catch(Exception e) {
				throw new CategoryProjectException(ErrorCode.SYSTEM_EXCEPTION, ACTION_1, e);
				//CategoryException(ErrorCode.BAD_DATA,"Category Name should not be empty");
			}
			return c;
		}
		throw new CategoryException(ErrorCode.BAD_DATA,"Category Name should not be empty");
		*/
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
	public String removeCategory(Integer CategoryId) {
		 if (CategoryId == null) {
	            throw new CategoryException(ErrorCode.BAD_DATA, "Valid CategoryId is required");
	        }
		 
	        try {
			 dao.removeCategory(CategoryId);	
			 return "deleted";
	        } 
	        
	        catch (Exception e) {
	            throw new CategoryProjectException(ErrorCode.SYSTEM_EXCEPTION,"Can not delete the category as it contains books", e);
	        }	
	
	}
	
	@Override
	public String removeBook(Integer bid) {
		if(bid==null) {
			throw new CategoryException(ErrorCode.BAD_DATA, "Valid BookId is required");
		}
		else {
			dao1.deleteById(bid);
		}
		return "deleted";
		
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
		return dao1.findAll();
	}
	
	private Boolean validateCategory(Category category) {
        if (category.getCategoryName() == null || category.getCategoryName().isEmpty()) {
            throw new CategoryException(ErrorCode.BAD_DATA, "Category Name should not be empty");
        }
        return true;
    }

	private Boolean validateBook(Book book) {
        if (book.getAuthor()== null || book.getAuthor().isEmpty()) {
            throw new CategoryException(ErrorCode.BAD_DATA, "Author Name should not be empty");
        }
        if (book.getDescription()== null || book.getDescription().isEmpty()) {
            throw new CategoryException(ErrorCode.BAD_DATA, "Description should not be empty");
        }
        if (book.getTitle() == null ||  book.getTitle().isEmpty()) {
            throw new CategoryException(ErrorCode.BAD_DATA, "Title should not be empty");
        }
        return true;
    }
	
	
	



}
