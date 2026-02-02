package org.example.batchchallenge.infra.config;

import org.example.batchchallenge.domain.model.BankClient;
import org.springframework.batch.infrastructure.item.file.mapping.FieldSetMapper;
import org.springframework.batch.infrastructure.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class BankClientMapper implements FieldSetMapper<BankClient> {
    @Override
    public BankClient mapFieldSet(FieldSet fieldSet) throws BindException {
        //TODO MAPPER FOR BANKCLIENT CLASS
    }
}
