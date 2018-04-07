package com.ip.data;

import com.ip.domain.Developer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DBInitializer implements ApplicationListener<ContextRefreshedEvent> {


    private DeveloperRepository developerRepository;

    @Autowired
    public DBInitializer(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;

    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        developerRepository.save(new Developer("Damian", "Jankowski", "test@test.com",
                "Senior", "stubbed.linkedin.com", "Java", "Wrocław",
                "This is a sample bio", true));

        developerRepository.save(new Developer("Jakub", "Dudycz", "test@test.com",
                "Regular", "stubbed.linkedin.com", "Javascript", "Poznań",
                "This is a sample bio", false));

        developerRepository.save(new Developer("Maciej", "Hajduk", "test@test.com",
                "Junior", "stubbed.linkedin.com", "Ruby", "Warszawa",
                "This is a sample bio", true));

        developerRepository.save(new Developer("Grzegorz", "Dzikowski", "test@test.com",
                "Regular", "stubbed.linkedin.com", "Java", "Wrocław",
                "This is a sample bio", false));

        developerRepository.save(new Developer("Marek", "Grygo", "test@test.com",
                "Regular", "stubbed.linkedin.com", "Python", "Lublin",
                "This is a sample bio", true));

        developerRepository.save(new Developer("Jan", "Surma", "test@test.com",
                "Junior", "stubbed.linkedin.com", "Javascript", "Poznań",
                "This is a sample bio", false));

        developerRepository.save(new Developer("Maciej", "Kabanos", "test@test.com",
                "Senior", "stubbed.linkedin.com", "Ruby", "Warszawa",
                "This is a sample bio", false));

        developerRepository.save(new Developer("Marcin", "Sys", "test@test.com",
                "Junior", "stubbed.linkedin.com", "C", "Javascript",
                "This is a sample bio", false));

        developerRepository.save(new Developer("Jan", "Kowalski", "test@test.com",
                "Senior", "stubbed.linkedin.com", "Java", "Wrocław",
                "This is a sample bio", true));

        developerRepository.save(new Developer("Grzegorz", "Kapała", "test@test.com",
                "Junior", "stubbed.linkedin.com", "Javascript", "Poznań",
                "This is a sample bio", false));

        developerRepository.save(new Developer("Krzysztof", "Tomasik", "test@test.com",
                "Senior", "stubbed.linkedin.com", "Python", "Warszawa",
                "This is a sample bio", false));

        developerRepository.save(new Developer("Michał", "Zdębowski", "test@test.com",
                "Regular", "stubbed.linkedin.com", "C", "Poznań",
                "This is a sample bio", false));


    }
}
