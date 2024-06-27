package com.batch.config;

import com.batch.entities.Person;
import com.batch.steps.PersonItemProcessor;
import com.batch.steps.PersonItemReader;
import com.batch.steps.PersonItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private final JobRepository jobRepository;

    public BatchConfig(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Bean
    public PersonItemReader personItemReader() {
        return new PersonItemReader();
    }

    @Bean
    public PersonItemWriter personItemWriter() {
        return new PersonItemWriter();
    }

    @Bean
    public PersonItemProcessor personItemProcessor() {
        return new PersonItemProcessor();
    }

    // configruación de hilos
    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(1); // 1 hilo
        executor.setMaxPoolSize(5); // hilos adicionales
        executor.setQueueCapacity(5); // tareas en cola permitidas
        return executor;
    }

    @Bean
    public Step readFile(PersonItemReader personItemReader, PersonItemWriter personItemWriter, PlatformTransactionManager transactionManager) {
        return new StepBuilder("readFile", jobRepository)
                .<Person, Person>chunk(10, transactionManager)
                .reader(personItemReader())
                .processor(personItemProcessor())
                .writer(personItemWriter())
                .build();
    }
    @Bean
    public Job job(JobRepository jobRepository, Step personItemReader) {
        return new JobBuilder("readFilwithChunk", jobRepository)
                .start(personItemReader)
                .build();
    }
}
