package com.ip.domain;

import java.util.List;

public class DeveloperRequest {

    private String position;
    private List<String> languages;
    private boolean student;
    private String city;

    public DeveloperRequest(String position, List<String> languages, String city, boolean student) {
        this.position = position;
        this.languages = languages;
        this.student = student;
        this.city = city;
    }

    public String getPosition() {
        return position;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public boolean isStudent() {
        return student;
    }

    public String getCity() {
        return city;
    }
}
