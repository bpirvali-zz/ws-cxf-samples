package com.bp.bs;

public class BooksService {

	public boolean contains(String isbn) {
		return BookDB.instance.contains(isbn);
	}

	public Book getBook(String isbn) {
		return BookDB.instance.getBook(isbn);
	}

}
