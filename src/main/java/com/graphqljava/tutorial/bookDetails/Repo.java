package com.graphqljava.tutorial.bookDetails;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Repo {

	private static List<Book> books = Arrays.asList(
			new Book("book-1", "Harry Potter and the Philosopher's Stone", 223, "author-1"),
			new Book("book-2", "Moby Dick", 635, "author-2"),
			new Book("book-3", "Interview with the vampire", 371, "author-3")
	);

	private static List<Dvd> dvds = Arrays.asList(
			new Dvd("dvd-1", "Harry Potter and the Philosopher's Stone", 120, "author-1"),
			new Dvd("dvd-2", "Moby Dick", 140, "author-2"),
			new Dvd("dvd-3", "Interview with the vampire", 150, "author-3")
	);

	public static Media getById(String id) {
		return Stream.concat(books.stream(), dvds.stream())
				.filter(book -> book.getId().equals(id))
				.findFirst().orElse(null);
	}

}