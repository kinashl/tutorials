package com.graphqljava.tutorial.bookDetails;

import com.netflix.graphql.dgs.DgsTypeResolver;

public class TypeResolvers {

	@DgsTypeResolver(name = "Media")
	public String resolveMovie(Media media) {
		if (media instanceof Dvd) {
			return "Dvd";
		} else if (media instanceof Media) {
			return "Book";
		} else {
			throw new RuntimeException("Invalid type: " + media.getClass().getName() + " found in MovieTypeResolver");
		}
	}
}
