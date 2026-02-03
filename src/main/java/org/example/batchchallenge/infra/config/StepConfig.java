package org.example.batchchallenge.infra.config;

import org.example.batchchallenge.domain.model.BankClient;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.infrastructure.item.ItemReader;
import org.springframework.batch.infrastructure.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StepConfig {
    @Bean
    public Step initialStep(JobRepository jobRepository, ItemReader<BankClient> reader,
                            ItemWriter<BankClient> writer) {
        return new StepBuilder("first-step", jobRepository)
                .<BankClient, BankClient>chunk(5)
                .reader(reader)
                .writer(writer)
                .build();
    }

}
