package com.batch.service.impl;

import com.batch.entities.Person;
import com.batch.repository.PersonDao;
import com.batch.service.PersonService;
import jakarta.transaction.Transactional;
import org.springframework.batch.item.Chunk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDao personDao;

    @Override
    @Transactional
    public Iterable<Person> saveAll(Chunk persons) {
        return personDao.saveAll(persons);
    }
}
