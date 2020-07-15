package com.cg.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="category1")
public class Category {
	@Id   
	@Column(length=12)   
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int CategoryId;
	@Column(length=50)
	public String CategoryName;
	
	//@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//	@JoinColumn(name="CategoryId")
	//@ManyToOne(optional = false)
    //@JoinColumn(name="book_id")
	@JsonBackReference
	@OneToMany(mappedBy="category")
	private List<Book> books = new ArrayList<Book>();

	public int getCategoryId() {
		return CategoryId;
	}

	public void setCategoryId(int categoryId) {
		CategoryId = categoryId;
	}

	public String getCategoryName() {
		return CategoryName;
	}

	public void setCategoryName(String categoryName) {
		CategoryName = categoryName;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
	

}
