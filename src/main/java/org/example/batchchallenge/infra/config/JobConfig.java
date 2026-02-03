package org.example.batchchallenge.infra.config;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.parameters.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class JobConfig {

    @Bean
    public Job job (JobRepository jobRepository, Step initialStep) {
        return new JobBuilder("data-process", jobRepository)
                .start(initialStep)
                .incrementer(new RunIdIncrementer())
                .build();
    }
}
