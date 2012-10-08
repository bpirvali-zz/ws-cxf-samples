package com.bp.bs;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.springframework.stereotype.Service;
@Service("bookSelectionsResource")
@Path("bookselections")
public class BookSelectionsResource {
	@GET
	public Response select(@QueryParam("keyword") String keyword,
			@QueryParam("pubdate") String pubDate) {
		List<Book> books = BookDB.instance.searchBooks(keyword, pubDate);
		BooksState result = new BooksState();
		for (Book book : books) {
			BookState st = new BookState();
			st.setIsbn(book.getIsbn());
			st.setTitle(book.getTitle());
			result.getBook().add(st);
		}
		ResponseBuilder builder = Response.ok(result);
		CacheController.setExpiry(builder);
		return builder.build();
	}
}