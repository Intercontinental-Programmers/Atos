package com.ip.services;

import com.ip.data.DeveloperRepository;
import com.ip.domain.Developer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;

@Service
@Transactional
public class DeveloperService {

    private DeveloperRepository developerRepository;

    @Autowired
    public DeveloperService(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }


    /*
    public Developer getDeveloperByPosition(String position) throws Exception {
        Optional<Developer> result = developerRepository.findByPosition(position);
        if (!developerRepository.existsByPosition(position))
            throw new Exception("No matches found");
        return result.get();
    }

    public Developer getDeveloperByCity(String city) throws Exception {
        Optional<Developer> result = developerRepository.findByCity(city);
        if (!developerRepository.existsByCity(city))
            throw new Exception("No matches found");
        return result.get();
    }

    public Developer getDeveloperByStudentStatus(boolean student) throws Exception {
        Optional<Developer> result = developerRepository.findByStudentIsTrue(student);
        if (!developerRepository.existsByStudentIsTrue(student))
            throw new Exception("No matches found");
        return result.get();
    }

    public Developer getDeveloperByLanguage(ArrayList<String> languages) throws Exception {
        Optional<Developer> result = developerRepository.findByLanguagesIn(languages);
        if (!developerRepository.existsByLanguagesIn(languages))
            throw new Exception("No matches found");
        return result.get();
    }

*/


}
