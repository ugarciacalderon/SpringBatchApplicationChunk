package com.batch.steps;

import com.batch.entities.Person;
import com.batch.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class PersonItemWriter implements ItemWriter<Person>{

    @Autowired
    private PersonService personService;

    @Override
    public void write(Chunk<? extends Person> chunk) throws Exception {
        for (Person person : chunk) {
            log.debug("Person: {}", person);
        }
        personService.saveAll((Chunk<? extends List<Person>>) chunk);
    }
}
