package com.peliculasonlinehd.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

// Ignora propiedades del JSON que no estén mapeadas en esta clase
@JsonIgnoreProperties(ignoreUnknown = true)
public class People {

    private int id;
    private String name;
    private int gender;
    private double popularity;
    @JsonProperty("profile_path")
    private String profile;
    @JsonProperty("known_for")
    private List<KnownFor> knownFor;

    // CONSTRUCTOR
    public People(int id, String name, int gender, double popularity, String profile, List<KnownFor> knownFor) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.popularity = popularity;
        this.profile = profile;
        this.knownFor = knownFor;
    }

    public People() {

    }

    // GETTERS Y SETTERS

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public List<KnownFor> getKnowFor() {
        return knownFor;
    }

    public void setKnowFor(List<KnownFor> knowFor) {
        this.knownFor = knowFor;
    }
}
