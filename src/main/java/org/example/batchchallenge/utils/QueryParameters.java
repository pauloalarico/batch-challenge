package org.example.batchchallenge.utils;

import org.example.batchchallenge.domain.model.BankClient;
import org.springframework.batch.infrastructure.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public class QueryParameters extends BeanPropertyItemSqlParameterSourceProvider<BankClient> {
    @Override
    public SqlParameterSource createSqlParameterSource(BankClient item) {
        //TODO CREATE THE QUERY FOR BATCH
    }
}
