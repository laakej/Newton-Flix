package com.laakej.NewtonFlix.services;

import com.laakej.NewtonFlix.models.SearchResultsModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class SearchServiceTest {
    private static final String testLocationString = "src/test/resources/fakeMovieList.json";
    @Test
    public void whenFetchMovies_thenReturnSearchModel() throws IOException {
        //Retrieve the Json object from the fake movie list file and parse it into fakeMovieInputString
        String fakeMovieInputString = Files.readString(Paths.get(testLocationString));

        ClientResponse fakeClientResponse = ClientResponse
                .create(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body(fakeMovieInputString)
                .build();

        WebClient webClient =  WebClient.builder().exchangeFunction(new ExchangeFunction() {
            @Override
            public Mono<ClientResponse> exchange(ClientRequest request) {
                return Mono.just(fakeClientResponse);
            }
        }).build();

        SearchService searchService = new SearchService(webClient);
        SearchResultsModel searchResults = searchService.fetchMovies();

        assertNotNull(searchResults.getResults().get(0).getTitle());
    }



}