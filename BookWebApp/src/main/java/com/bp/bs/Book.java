package com.bp.bs;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Book {
	private String isbn;
	private String title;
	private Date lastModified;
	private long version;
	private List<Review> reviews;
	
	public Book(String isbn, String title) {
		this.isbn = isbn;
		this.title = title;
		this.lastModified = new Date();
		this.version = 0;
		this.reviews = new ArrayList<Review>();
	}

	public String getIsbn() {
		return isbn;
	}

	public String getTitle() {
		return title;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public long getVersion() {
		return version;
	}
	
	public void setIsbn(String isbn) {
		this.isbn = isbn;
		version++;
	}

	public void setTitle(String title) {
		this.title = title;
		version++;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
		version++;
	}

	public List<Review> getReviews() {
		return reviews;
	}	
}