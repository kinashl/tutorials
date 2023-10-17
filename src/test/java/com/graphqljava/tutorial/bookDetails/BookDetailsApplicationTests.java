package com.graphqljava.tutorial.bookDetails;

import com.example.packagename.client.MediaByIdGraphQLQuery;
import com.example.packagename.client.MediaByIdProjectionRoot;
import com.example.packagename.types.Book;
import com.jayway.jsonpath.TypeRef;
import com.netflix.graphql.dgs.client.CustomGraphQLClient;
import com.netflix.graphql.dgs.client.GraphQLClient;
import com.netflix.graphql.dgs.client.GraphQLResponse;
import com.netflix.graphql.dgs.client.HttpResponse;
import com.netflix.graphql.dgs.client.MonoGraphQLClient;
import com.netflix.graphql.dgs.client.WebClientGraphQLClient;
import com.netflix.graphql.dgs.client.codegen.GraphQLQueryRequest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@SpringBootTest
class BookDetailsApplicationTests {

	@Test
	void contextLoads() {
		GraphQLQueryRequest query =
				new GraphQLQueryRequest(
						new MediaByIdGraphQLQuery.Builder()
								.id("book-1")
								.build(),
						new MediaByIdProjectionRoot()
								.id()
								.onDvd().runTime().root()
								.onBook().pageCount().root()
								.mediaType());


		//Configure a WebClient for your needs, e.g. including authentication headers and TLS.
		WebClient webClient = WebClient.create("http://localhost:8888/graphql");
		WebClientGraphQLClient client = MonoGraphQLClient.createWithWebClient(webClient, headers -> headers.add("myheader", "test"));
//		MonoGraphQLClient graphQLClient = new DefaultGraphQLClient("http://localhost:" + 8888 + "/graphql");

//		WebClientGraphQLClient client = MonoGraphQLClient.createWithWebClient(webClient);

		//The GraphQLResponse contains data and errors.
		Mono<GraphQLResponse> graphQLResponseMono = client.reactiveExecuteQuery(query.serialize());

		graphQLResponseMono.subscribe();
		//GraphQLResponse has convenience methods to extract fields using JsonPath.
		Mono<String> somefield = graphQLResponseMono.map(r -> r.extractValue("somefield"));

		//Don't forget to subscribe! The request won't be executed otherwise.
		somefield.subscribe();
	}

	@Test
	void contextLoads2() {

		String url = "http://localhost:8888/graphql";

		GraphQLQueryRequest query =
				new GraphQLQueryRequest(
						new MediaByIdGraphQLQuery.Builder()
								.id("book-1")
								.build(),
						new MediaByIdProjectionRoot()
								.id()
								.name()
								.author()
								.id()
								.firstName()
								.lastName()
								.root()
//								.onDvd().runTime().root()
								.onBook().pageCount().root());

		CustomGraphQLClient client = GraphQLClient.createCustom(url,  (urle, headers, body) -> {
			HttpHeaders httpHeaders = new HttpHeaders();
			headers.forEach(httpHeaders::addAll);
			ResponseEntity<String> exchange = new RestTemplate().exchange(url, HttpMethod.POST, new HttpEntity<>(body, httpHeaders),String.class);
			return new HttpResponse(exchange.getStatusCodeValue(), exchange.getBody());
		});

		GraphQLResponse graphQLResponse = client.executeQuery(query.serialize());

//		List<Show> shows = dgsQueryExecutor.executeAndExtractJsonPathAsObject(
//				graphQLQueryRequest.serialize(),
//				"data.shows[*]",
//				new TypeRef<List<Show>>() {
//				});


//		com.example.packagename.types.IBook book = graphQLResponse.extractValueAsObject("mediaById", new TypeRef<com.example.packagename.types.IBook>(){});
//		com.example.packagename.types.Book book2 = graphQLResponse.extractValueAsObject("data.mediaById", com.example.packagename.types.Book.class);
//		com.example.packagename.types.Media media = graphQLResponse.extractValueAsObject("data.mediaById", new TypeRef<com.example.packagename.types.Media>(){});com.example.packagename.types.Media media = graphQLResponse.extractValueAsObject("mediaById", com.example.packagename.types.Media.class);
		Book media2 = graphQLResponse.extractValueAsObject("mediaById", Book.class);
//		Media media = graphQLResponse.extractValueAsObject("mediaById", Media.class);
		com.example.packagename.types.Dvd dvd = graphQLResponse.extractValueAsObject("mediaById", com.example.packagename.types.Dvd.class);

	}

}
