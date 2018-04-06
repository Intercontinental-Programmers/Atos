package com.ip.domain;

import java.util.List;

public class DeveloperRequest {

    private String position;
    private List<String> languages;
    private String level;

    public DeveloperRequest( String position, List<String> languages, String level) {
        this.position = position;
        this.languages = languages;
        this.level = level;
    }

    public String getPosition() {
        return position;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public String getLevel() {
        return level;
    }
}
