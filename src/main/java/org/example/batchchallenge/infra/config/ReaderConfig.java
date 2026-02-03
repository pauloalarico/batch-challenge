package org.example.batchchallenge.infra.config;

import org.example.batchchallenge.domain.model.BankClient;
import org.springframework.batch.infrastructure.item.file.FlatFileItemReader;
import org.springframework.batch.infrastructure.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class ReaderConfig {
    @Value("${app.paths.resource}")
    private String pathFileDestiny;

    @Bean
    public FlatFileItemReader<BankClient> reader() {
        return new FlatFileItemReaderBuilder<BankClient>()
                .name("file-reader")
                .resource(new FileSystemResource(pathFileDestiny + "/dados_ficticios.csv"))
                .linesToSkip(1)
                .delimited()
                .delimiter("|")
                .names("name", "documentNumber", "branch", "account", "value", "referenceDate")
                .fieldSetMapper(new BankClientMapper())
                .build();
    }

}
