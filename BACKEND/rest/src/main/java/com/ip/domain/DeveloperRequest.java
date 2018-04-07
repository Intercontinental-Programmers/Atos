package com.ip.domain;

import java.util.List;

public class DeveloperRequest {

    private List<String> levels;
    private List<String> languages;
    private Boolean student;
    private List<String> cities;

    public DeveloperRequest(List<String> levels, List<String> languages, List<String> city, Boolean student) {
        this.levels = levels;
        this.languages = languages;
        this.student = student;
        this.cities = city;
    }

    public List<String> getLevels() {
        return levels;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public Boolean isStudent() {
        return student;
    }

    public List<String> getCities() {
        return cities;
    }
}
