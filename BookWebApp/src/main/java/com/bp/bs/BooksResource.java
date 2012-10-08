package com.bp.bs;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.springframework.stereotype.Service;
@Service("booksResource")
@Path("books")
public class BooksResource {
	@POST
	public Response add(@Context Request request, @Context UriInfo uriInfo, BookState st) {
		BookDB.instance.addBook(new Book(st.getIsbn(), st.getTitle()));
		UriBuilder uriBuilder = uriInfo.getBaseUriBuilder();
		uriBuilder.path(BookResource.class);
		ResponseBuilder builder = Response.created(uriBuilder.build(st.getIsbn()));
		return builder.build();
//		try {
//			ResponseBuilder builder = Response.created(new URI(
//					"http://localhost:8080/bs/books/" + st.getIsbn()));
//			return builder.build();
//		} catch (URISyntaxException e) {
//			throw new RuntimeException(e);
//		}
	}
}
