package com.batch.service;

import com.batch.entities.Person;
import org.springframework.batch.item.Chunk;

import java.util.List;

public interface PersonService {
    Iterable<Person> saveAll(Chunk<? extends List<Person>> chunk);
}
