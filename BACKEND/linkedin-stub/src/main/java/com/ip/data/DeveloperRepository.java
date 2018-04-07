package com.ip.data;

import com.ip.domain.Developer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeveloperRepository extends CrudRepository<Developer, Long> {


    boolean existsByLanguagesIn(List<String> languages); //Not sure if it should be "In" or "Is"

    boolean existsByPosition(String position);

    boolean existsByStudentIsTrue(boolean student);

    Optional<Developer> findByPosition(String position);

    Optional<Developer> findByLanguagesIn(List<String> languages);

    Optional<Developer> findByCity(String city);

    Optional<Developer> findByStudentIsTrue(boolean student);

}
