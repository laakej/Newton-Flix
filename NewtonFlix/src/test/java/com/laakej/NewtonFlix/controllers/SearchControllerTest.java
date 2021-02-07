package com.laakej.NewtonFlix.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.stream.Collectors;
import com.laakej.NewtonFlix.models.SearchResultsModel;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.laakej.NewtonFlix.models.MovieModel;
import com.laakej.NewtonFlix.services.SearchService;


@WebMvcTest(SearchController.class)
public class SearchControllerTest {
	private static int movieCount = 4;
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private  SearchService mockSearchService;

	@BeforeEach
	public void setUp() {
		when(mockSearchService.fetchMovies()).thenReturn(getSearchResultModel());
	}

	@Test
	public void whenSearchMovies_thenReturnMovieList() throws Exception {
		mockMvc.perform(get("/api/search")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(movieCount)))
				.andExpect(status().isOk());
	}

	private SearchResultsModel getSearchResultModel() {
		EasyRandom generator = new EasyRandom();
		List<MovieModel> movieList = generator
				.objects(MovieModel.class, movieCount)
				.collect(Collectors.toList());

		SearchResultsModel searchResultsModel = new SearchResultsModel();

		searchResultsModel.setResults(movieList);

		return searchResultsModel;
	}
}
