package com.laakej.NewtonFlix.services;

import com.laakej.NewtonFlix.models.SearchResultsModel;
import org.springframework.stereotype.Service;

import org.springframework.web.reactive.function.client.WebClient;

@Service
public class SearchService {
	//Hardcoding the 3rd party api parameters, we can upgrade this later to take different search terms
	static final String uri = "http://www.omdbapi.com/?apikey=e0522e17&s=newton&type=movie";
	private	WebClient webClient;

	public SearchService(){
		this.webClient = WebClient.create();
	}

	public SearchService(WebClient webClient){
		this.webClient = webClient;
	}

	public SearchResultsModel fetchMovies() {
		//TODO: Need to set the timeout here
		return this.webClient.get()
				.uri(uri)
				.retrieve()
				.bodyToMono(SearchResultsModel.class)
				.block();
	}
}
