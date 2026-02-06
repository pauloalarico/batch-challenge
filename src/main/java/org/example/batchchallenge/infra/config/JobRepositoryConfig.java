package org.example.batchchallenge.infra.config;

import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JdbcJobRepositoryFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class JobRepositoryConfig {
    @Bean
    @Primary
    public JobRepository jobRepositoryBatch (DataSource dataSource,
                                       PlatformTransactionManager transactionManager) throws Exception {
        JdbcJobRepositoryFactoryBean factory = new JdbcJobRepositoryFactoryBean();
        factory.setTransactionManager(transactionManager);
        factory.setDataSource(dataSource);
        factory.setDatabaseType("POSTGRES");
        factory.afterPropertiesSet();
        return factory.getObject();
    }

}
