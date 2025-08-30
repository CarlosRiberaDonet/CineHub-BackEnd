package com.peliculasonlinehd.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.peliculasonlinehd.entity.People;

import java.util.List;

// Ignora propiedades del JSON que no estén mapeadas en esta clase
@JsonIgnoreProperties(ignoreUnknown = true)
public class PeopleResponse {

    private int page;
    @JsonProperty("total_pages")
    private int totalPages;
    @JsonProperty("total_results")
    private int results;
    @JsonProperty("results") // El campo "results" del JSON mapea a popularPeopleList
    private List<People> popularPeopleList;

    // CONSTRUCTOR
    public PeopleResponse(List<People> popularPeopleList){
        this.popularPeopleList = popularPeopleList;
    }

    public PeopleResponse(){

    }

    // GETTERS Y SETTERS
    public int getPage(){
        return page;
    }

    public void setPage(int page){
        this.page = page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getResults() {
        return results;
    }

    public void setResults(int results) {
        this.results = results;
    }

    public List<People> getPopularPeopleList(){
        return popularPeopleList;
    }

    public void setPopularPeopleList(List<People> popularPeopleList){
        this.popularPeopleList = popularPeopleList;
    }
}
