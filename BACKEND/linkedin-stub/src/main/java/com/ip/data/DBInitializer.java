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


    //FIXME: temporary
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        developerRepository.save(new Developer("damian", "jankowski", "test@test.com",
                "Senior", "sdadoas.com", "Java", "Wrocław",
                "disaoddaskmuasidnsapidnaosidas", true));

        developerRepository.save(new Developer("jakub", "dudycz", "test@test.com",
                "Regular", "sdadoas.com", "Javascript", "Poznań",
                "disaoduasisaoddnsapidnaosidas", false));

        developerRepository.save(new Developer("maciej", "hajdug", "test@test.com",
                "Junior", "sdadoas.com", "Ruby", "Warszawa",
                "disaoduasidnsapiddsadnaosidas", true));

        developerRepository.save(new Developer("grzegorz", "dzikowski", "test@test.com",
                "Regular", "sdadoas.com", "Java", "Wrocław",
                "disaoduasidnsapidnaosidas", true));


    }
}
