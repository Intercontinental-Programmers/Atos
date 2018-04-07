package com.ip.service;

import com.ip.data.DeveloperRepository;
import com.ip.domain.Developer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.hibernate.validator.internal.util.CollectionHelper.newArrayList;

@Service
public class DeveloperService {

    private DeveloperRepository developerRepository;

    @Autowired
    public DeveloperService(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    public List<Developer> query(List<String> cities, List<String> languages, List<String> levels, Boolean student) {

        return newArrayList(developerRepository.findAll())
                .stream()
                .filter(developer -> cities.contains(developer.getCity()))
                .filter(developer -> languages.contains(developer.getMainLanguage()))
                .filter(developer -> levels.contains(developer.getLevel()))
                .filter(developer -> {

                    if (student == null)
                        return true;

                    return developer.isStudent() == student;
                })
                .collect(Collectors.toList());
    }
}
