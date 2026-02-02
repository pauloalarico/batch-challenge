package org.example.batchchallenge.infra.config;

import org.example.batchchallenge.domain.model.BankClient;
import org.example.batchchallenge.utils.QueryParameters;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.parameters.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.infrastructure.item.ItemReader;
import org.springframework.batch.infrastructure.item.ItemWriter;
import org.springframework.batch.infrastructure.item.database.JdbcBatchItemWriter;
import org.springframework.batch.infrastructure.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.infrastructure.item.file.FlatFileItemReader;
import org.springframework.batch.infrastructure.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
public class JobConfig {
    private final DataSource dataSource;
    @Value("${app.paths.resource}")
    private String pathFileDestiny;

    public JobConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public Job job (JobRepository jobRepository) {
        return new JobBuilder("data-process", jobRepository)
                .start(initialStep(jobRepository))
                .incrementer(new RunIdIncrementer())
                .build();
    }

    @Bean
    public Step initialStep(JobRepository jobRepository, DataSourceTransactionManager transactionManager) {
        return new StepBuilder("first-step", jobRepository)
                .<BankClient, BankClient>chunk(5)
                .transactionManager(transactionManager)
                .reader(reader())
                .processor()
                .writer(writer())
                .build();
    }

    @Bean
    public FlatFileItemReader<BankClient> reader() {
        return new FlatFileItemReaderBuilder<BankClient>()
                .name("file-reader")
                .resource(new FileSystemResource(pathFileDestiny + "/dados_ficticios.csv"))
                .delimited()
                .delimiter("|")
                .names("name", "documentNumber", "branch", "account", "value", "referenceDate")
                .fieldSetMapper(new BankClientMapper())
                .build();
    }

    public ItemWriter<BankClient> writer() {
        return new JdbcBatchItemWriterBuilder<BankClient>()
                .dataSource(dataSource)
                .sql(
                        "INSERT INTO bank_client(name, document_number, branch, account, value, reference_date)" +
                                "VALUES(:name, documentNumber, branch, account, value, referenceDate"
                )
                .itemSqlParameterSourceProvider(new QueryParameters())
                .build();
    }
}
