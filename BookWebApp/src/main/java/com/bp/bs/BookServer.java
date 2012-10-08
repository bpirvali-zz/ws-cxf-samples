package com.bp.bs;

import java.io.IOException;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;

public class BookServer {
	public static void main(String[] args) throws InterruptedException,
			IOException {
		try {
			JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
			sf.setResourceClasses(BookResource.class, 
					BooksResource.class, BookSelectionsResource.class);
			sf.setAddress("http://localhost:8080/bs/rest");
			sf.create();
			System.out.println("Started");
			Thread.sleep(5 * 60 * 1000);
			System.out.println("ended");
			System.exit(0);

		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
}