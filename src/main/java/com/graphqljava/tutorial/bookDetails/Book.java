package com.graphqljava.tutorial.bookDetails;

public class Book implements Media {

	private String id;
	private String name;
	private int pageCount;
	private String authorId;

	public Book(String id, String name, int pageCount, String authorId) {
		this.id = id;
		this.name = name;
		this.pageCount = pageCount;
		this.authorId = authorId;
	}


	public String getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	public String getAuthorId() {
		return authorId;
	}

	@Override
	public MediaType getMediaType() {
		return MediaType.BOOK;
	}
}