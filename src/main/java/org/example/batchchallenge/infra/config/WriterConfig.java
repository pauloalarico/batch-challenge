package org.example.batchchallenge.infra.config;

import org.example.batchchallenge.domain.model.BankClient;
import org.example.batchchallenge.utils.QueryParameters;
import org.springframework.batch.infrastructure.item.ItemWriter;
import org.springframework.batch.infrastructure.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class WriterConfig {

    @Bean
    public ItemWriter<BankClient> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<BankClient>()
                .dataSource(dataSource)
                .sql(
                        "INSERT INTO bank_client(id, name, document_number, branch, account, value, reference_date)" +
                                "VALUES(:id, :name, :documentNumber, :branch, :account, :value, :referenceDate)"
                )
                .itemSqlParameterSourceProvider(new QueryParameters())
                .build();
    }
}
