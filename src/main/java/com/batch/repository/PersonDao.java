package com.batch.repository;

import com.batch.entities.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonDao extends CrudRepository<Person, Long> {
}
