package com.laakej.NewtonFlix.controllers;

import com.laakej.NewtonFlix.models.MovieModel;
import com.laakej.NewtonFlix.models.SearchResultsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.laakej.NewtonFlix.services.SearchService;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchController {

	private final SearchService searchService;

	@Autowired
	SearchController(SearchService searchService) {
		this.searchService = searchService;
	}
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping("")	
	public ResponseEntity<List<MovieModel>> search() {
		SearchResultsModel resultList = searchService.fetchMovies();

		return new ResponseEntity<>(resultList.getResults(), HttpStatus.OK);
	}
}
