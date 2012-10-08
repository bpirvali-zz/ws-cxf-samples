package com.bp.bs;


import org.apache.cxf.jaxrs.client.WebClient;

public class BookClient {
	public static void main(String[] args) throws InterruptedException {
		//WebClient client = WebClient.create("http://localhost:8080/bs");
		//WebClient client = WebClient.create("http://localhost:1234/bs");
		WebClient client = WebClient.create("http://localhost:8080/bs/rest");
		client.path("books/1234");
		BookState book = client.get(BookState.class);
		System.out.println(book.getTitle());		
		//POST
//		BookState st = new BookState();
//		st.setIsbn("5678");
//		st.setTitle("Design Patterns");
//		client.path("books");
//		client.post(st);
//		client.back(true);
//		client.path("books/5678");		

		//GET
		//client.path("rest/books/1234");
		//Book book = client.accept("application/xml").get(Book.class);
		//Book book = client.get(Book.class);
		//System.out.println(book.getTitle());
		
		//DELETE (404 returns)
//		client.path("books/1234");
//		client.delete();
//		Response response = client.get();
//		System.out.println(response.getStatus());		
		
		//BookSelectionsResource
		//@GET
//		client.path("bookselections");
//		client.query("keyword", "Java");
//		client.query("pubdate", new GregorianCalendar(2009, 11, 26).getTime());
//		BooksState books = client.get(BooksState.class);
//		for (BookState st : books.getBook()) {
//			System.out.println(st.getTitle());
//		}		
	}
}
