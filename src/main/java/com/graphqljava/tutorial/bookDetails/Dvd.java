package com.graphqljava.tutorial.bookDetails;

public class Dvd implements Media {

    private String id;
    private String name;
    private int runTime;
    private String authorId;

    public Dvd(String id, String name, int pageCount, String authorId) {
        this.id = id;
        this.name = name;
        this.runTime = pageCount;
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
        return MediaType.DVD;
    }
}