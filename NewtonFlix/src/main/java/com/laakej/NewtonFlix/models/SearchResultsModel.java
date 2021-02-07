package com.laakej.NewtonFlix.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SearchResultsModel {
    @JsonProperty("Search")
    private List<MovieModel> results;

    public List<MovieModel> getResults() {
        return results;
    }

    public void setResults(List<MovieModel> results) {
        this.results = results;
    }
}
