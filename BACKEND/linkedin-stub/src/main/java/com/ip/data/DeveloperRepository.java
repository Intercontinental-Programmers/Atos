package com.ip.data;

import com.ip.domain.Developer;
import org.springframework.data.jpa.repository.support.CrudMethodMetadata;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public interface DeveloperRepository extends CrudRepository<Developer, Long> {
}
