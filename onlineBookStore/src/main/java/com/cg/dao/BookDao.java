package com.cg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.entity.Book;

@Repository
public interface BookDao extends JpaRepository<Book, Integer>{

	@Query("select b from Category c,Book b where c.categoryId=b.category and c.categoryId=:categoryId")
	public List<Book> getBookByCatID(@Param("categoryId")int categoryId);
}
