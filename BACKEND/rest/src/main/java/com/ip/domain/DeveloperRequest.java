package com.ip.domain;

import java.util.List;

public class DeveloperRequest {

    private String position;
    private String mainLanguage;
    private boolean student;
    private String city;

    public DeveloperRequest(String position, String mainLanguage, String city, boolean student) {
        this.position = position;
        this.mainLanguage = mainLanguage;
        this.student = student;
        this.city = city;
    }

    public String getPosition() {
        return position;
    }

    public String getMainLanguage() {
        return mainLanguage;
    }

    public boolean isStudent() {
        return student;
    }

    public String getCity() {
        return city;
    }
}
