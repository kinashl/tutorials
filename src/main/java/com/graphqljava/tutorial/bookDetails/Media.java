package com.graphqljava.tutorial.bookDetails;

public interface Media {

    String getId();
    String getName();
    String getAuthorId();

    MediaType getMediaType();
}