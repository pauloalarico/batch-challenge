package org.example.batchchallenge.utils;

import org.example.batchchallenge.domain.model.BankClient;
import org.springframework.batch.infrastructure.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public class QueryParameters extends BeanPropertyItemSqlParameterSourceProvider<BankClient> {
    @Override
    public SqlParameterSource createSqlParameterSource(BankClient item) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", item.getId());
        params.addValue("name", item.getName());
        params.addValue("documentNumber", item.getDocumentNumber());
        params.addValue("branch", item.getBranch());
        params.addValue("account", item.getAccount());
        params.addValue("value", item.getValue());
        params.addValue("referenceDate", item.getReferenceDate());
        return params;
    }
}
