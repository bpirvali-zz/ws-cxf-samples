package com.bp.bs;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.springframework.stereotype.Service;

@Service("bookResource")
@Path("books/{isbn}")
public class BookResource {
	//private BooksService bs;
	
	@Path("reviews")
	public ReviewsResource getReviewsResource(@PathParam("isbn") String isbn) {
		BookDB bookDB = BookDB.instance;
		if (bookDB.contains(isbn)) {
			Book book = bookDB.getBook(isbn);
			return new ReviewsResource(book);
		} else {
			Response response = Response.status(Status.NOT_FOUND).build();
			throw new WebApplicationException(response);
		}
	}
//	public void setBs(BooksService bs) {
//		this.bs = bs;
//	} 
	/*
	@Path("reviews")
	@GET
	public Response getReviews(@PathParam("isbn") String isbn) {
		BookDB bookDB = BookDB.instance;
		if (bookDB.contains(isbn)) {
			Book book = bookDB.getBook(isbn);
			ReviewsState result = new ReviewsState();
			for (Review r : book.getReviews()) {
				ReviewState st = new ReviewState();
				st.setBy(r.getBy());
				st.setText(r.getText());
				result.getReview().add(st);
			}
			ResponseBuilder builder = Response.ok(result);
			CacheController.setExpiry(builder);
			return builder.build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	*/
	
	@GET
	public Response getDetails(@Context Request request,
			@PathParam("isbn") String isbn) {
		BookDB bookDB = BookDB.instance;
		if (bookDB.contains(isbn)) {
			Book book = bookDB.getBook(isbn);
			EntityTag entityTag = new EntityTag(
					Long.toString(book.getVersion()));
			ResponseBuilder builder = request.evaluatePreconditions(
					getVersion(book), entityTag);
			if (builder != null) {
				CacheController.setExpiry(builder);
			} else {
				BookState st = getBookState(book);
				builder = Response.ok(st);
				builder.lastModified(getVersion(book));
				builder.tag(Long.toString(book.getVersion()));
				CacheController.setExpiry(builder);
			}
			return builder.build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@DELETE
	public Response delete(@PathParam("isbn") String isbn) {
		ResponseBuilder builder;
		BookDB bookDB = BookDB.instance;
		if (bookDB.contains(isbn)) {
			bookDB.delete(isbn);
			builder = Response.noContent();
		} else {
			builder = Response.status(Status.NOT_FOUND);
		}
		return builder.build();
	}

	@PUT
	public Response update(@PathParam("isbn") String isbn, BookState st) {
		ResponseBuilder builder;
		BookDB bookDB = BookDB.instance;
		if (isbn.equals("1234")) {
			Book book = bookDB.getBook1234();
			book.setIsbn(st.getIsbn());
			book.setTitle(st.getTitle());
			book.setLastModified(new Date());
			builder = Response.noContent();
		} else {
			builder = Response.status(Status.NOT_FOUND);
		}
		return builder.build();
	}

	private Date getVersion(Book book) {
		Date lastModified = book.getLastModified();
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(lastModified);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/*
	 * private void setExpiry(ResponseBuilder builder) { GregorianCalendar now =
	 * new GregorianCalendar(); GregorianCalendar nextUpdate =
	 * getNextUpdateTime(now); int maxAge = (int) ((nextUpdate.getTimeInMillis()
	 * - now .getTimeInMillis()) / 1000L); CacheControl cacheControl = new
	 * CacheControl(); cacheControl.setMaxAge(maxAge);
	 * builder.cacheControl(cacheControl);
	 * builder.expires(nextUpdate.getTime()); }
	 */
	public static BookState getBookState(Book book) {
		BookState st = new BookState();
		st.setIsbn(book.getIsbn());
		st.setTitle(book.getTitle());
		return st;
	}

//	private GregorianCalendar getNextUpdateTime(GregorianCalendar now) {
//		GregorianCalendar nextUpdate = new GregorianCalendar();
//		nextUpdate.setTime(now.getTime());
//		nextUpdate.set(Calendar.HOUR_OF_DAY, 10);
//		nextUpdate.set(Calendar.MINUTE, 0);
//		nextUpdate.set(Calendar.SECOND, 0);
//		nextUpdate.set(Calendar.MILLISECOND, 0);
//		if (now.get(Calendar.HOUR_OF_DAY) >= 10) {
//			nextUpdate.add(Calendar.DAY_OF_YEAR, 1);
//		}
//		return nextUpdate;
//	}
}