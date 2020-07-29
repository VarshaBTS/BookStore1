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

import com.cg.exceptions.ErrorCode;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
	@Autowired
	private CategoryDao dao;
	
	@Autowired
	private BookDao dao1;
	
	private static final String ACTION_1 = "Exception while writing data to persistant layer";
	
	/*******************************************************************************************************
	 * Function Name : addCategory 
	 * Input Parameters :  category
	 * Return Type : category 
	 * Throws : CategoryException
	 * Description : to add a Category
	 ********************************************************************************************************/
	
	@Override
	public Category addCategory(Category category) {
		 if (validateCategory(category)) {
	            try {
		       dao.save(category);
	            } catch (Exception e) {
	                throw new CategoryException(ErrorCode.SYSTEM_EXCEPTION, ACTION_1, e);
	            }
	         
	        }
		   return category;
	}

	/*******************************************************************************************************
	 * Function Name : addBook 
	 * Input Parameters :  book
	 * Return Type : book 
	 * Throws : CategoryException
	 * Description : to add a Book
	 ********************************************************************************************************/
	
	@Override
	public Book addBook(Book book) {
		if (validateBook(book)) {
            try {
		     dao1.save(book);
            } catch (Exception e) {
                throw new CategoryException(ErrorCode.SYSTEM_EXCEPTION, ACTION_1, e);
            }
		
		 }
       
        return book;
	}


	/*******************************************************************************************************
	 * Function Name : btoC 
	 * Input Parameters :  cid, bid
	 * Return Type : book 
	 * Description : to assign a Book
	 ********************************************************************************************************/
	
	@Override
	public Book btoC(int cid, int bid) {
		
		Book b=dao1.getOne(bid);
		if(b!=null) {
			Category c=dao.getOne(cid);
			b.setCategory(c);
		}
		return dao1.save(b);
	}

	

	/*******************************************************************************************************
	 * Function Name : UpdateCategory 
	 * Input Parameters :  c, n
	 * Return Type : Category 
	 * Throws : CategoryException
	 * Description : to update a category
	 ********************************************************************************************************/
	
	@Override
	public Category UpdateCategory(Integer c,String n) {
		Category ct;
		if (c == null) {
            throw new CategoryException(ErrorCode.BAD_DATA, "Valid CategoryId is required");
        }
		else {
			ct=dao.getOne(c);
			if(ct!=null) {
				ct.setCategoryName(n);
				dao.save(ct);
				if(!validateCategory(ct)) {
					throw new CategoryException(ErrorCode.BAD_DATA,"Category Name should not be empty");
				}
		}
		
		
		}
		return dao.save(ct);
		
	}

	

	/*******************************************************************************************************
	 * Function Name : getCategoryBId 
	 * Input Parameters :  cid
	 * Return Type : Category 
	 * Description : to get Books
	 ********************************************************************************************************/
	
	@Override
	public List<Book> getCategoryBId(int cid) {
		List<Book> c=dao1.getBookByCatID(cid);
		return c;
	}
	
	/*******************************************************************************************************
	 * Function Name : getAllCategory 
	 * Return Type : Category 
	 * Description : to get Category
	 ********************************************************************************************************/
	
	@Override
	public List<Category> getAllCategory(){
		return dao.findAll();
	}
	
	/*******************************************************************************************************
	 * Function Name : removeCategory 
	 * Input Parameters :  categoryId
	 * Return Type : String 
	 * Throws : CategoryException
	 * Description : to remove a category
	 ********************************************************************************************************/
	
	@Override
	public String removeCategory(Integer categoryId) {
		 if (categoryId == null) {
	            throw new CategoryException(ErrorCode.BAD_DATA, "Valid CategoryId is required");
	        }
		 
	        try {
			 dao.removeCategory(categoryId);	
			 return "deleted";
	        } 
	        
	        catch (Exception e) {
	            throw new CategoryException(ErrorCode.SYSTEM_EXCEPTION,"Can not delete the category as it contains books", e);
	        }	
	
	}
	
	/*******************************************************************************************************
	 * Function Name : removeBook 
	 * Input Parameters :  bid
	 * Return Type : String 
	 * Throws : CategoryException
	 * Description : to remove a Book
	 ********************************************************************************************************/
	
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

	/*******************************************************************************************************
	 * Function Name : updateBook 
	 * Input Parameters :  book
	 * Return Type : Book 
	 * Description : to update a Book
	 ********************************************************************************************************/
	
	@Override
	public Book updateBook(Book book) {
		
		Book b=dao1.getOne(book.getBook_id());
		if(b!=null)
		{
			b.setTitle(book.getTitle());
			b.setAuthor(book.getAuthor());
			b.setDescription(book.getDescription());
			b.setPrice(book.getPrice());
			b.setISBN(book.getISBN());
			
		}
		dao1.save(b);
        validateBook(b);
		return b;
	}

	
	@Override
	public List<Book> listOfBook() {
		return dao1.findAll();
	}
	
	
	/*******************************************************************************************************
	 * Function Name : validateCategory 
	 * Input Parameters :  category
	 * Return Type : Boolean 
	 * Throws : CategoryException
	 * Description : to validate a Category
	 ********************************************************************************************************/
	
	private Boolean validateCategory(Category category) {
        if (category.getCategoryName() == null || category.getCategoryName().isEmpty()) {
            throw new CategoryException(ErrorCode.BAD_DATA, "Category Name should not be empty");
        }
        return true;
    }

	/*******************************************************************************************************
	 * Function Name : validateBook 
	 * Input Parameters :  book
	 * Return Type : Boolean 
	 * Throws : CategoryException
	 * Description : to validate a Book
	 ********************************************************************************************************/
	
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
        if(book.getPrice() == 0)
        {
        	throw new CategoryException(ErrorCode.BAD_DATA, "Price has to be more than zero");
        }
        return true;
    }
	


}
