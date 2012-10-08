package com.bp.bs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookDB {
	//private Book book1234;
	private Map<String, Book> books;
	
	public BookDB() {
		books = new HashMap<String, Book>();
		Book book1234 = new Book("1234", "Java Programming");
		
		List<Review> reviews = book1234.getReviews();
		reviews.add(new Review("John", "Great book!"));
		reviews.add(new Review("Judy", "Excellent!"));	
		addBook(book1234);
	}

	public Book getBook1234() {
		return books.get("1234");
	}
	
	public void addBook(Book book) {
		books.put(book.getIsbn(), book);
	}

	public boolean contains(String isbn) {
		return books.containsKey(isbn);
	}
	public Book getBook(String isbn) {
		return books.get(isbn);
	}	
	public void delete(String isbn) {
		books.remove(isbn);
	}	
	
	public List<Book> searchBooks(String keyword, String pubDate) {
		return new ArrayList<Book>(books.values());
	}
	
	public static BookDB instance = new BookDB();
}