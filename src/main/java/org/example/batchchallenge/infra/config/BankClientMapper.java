package org.example.batchchallenge.infra.config;

import org.example.batchchallenge.domain.model.BankClient;
import org.springframework.batch.infrastructure.item.file.mapping.FieldSetMapper;
import org.springframework.batch.infrastructure.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class BankClientMapper implements FieldSetMapper<BankClient> {
    @Override
    public BankClient mapFieldSet(FieldSet fieldSet) throws BindException {
        return BankClient.builder()
                .id(UUID.randomUUID())
                .name(fieldSet.readString("name"))
                .documentNumber(fieldSet.readString("documentNumber"))
                .branch(fieldSet.readString("branch"))
                .account(fieldSet.readString("account"))
                .value(fieldSet.readBigDecimal("value"))
                .referenceDate(parse(fieldSet.readString("referenceDate")))
                .build();
    }

    private LocalDate parse (String date) {
        return YearMonth.parse(date, DateTimeFormatter.ofPattern("MM/yy"))
                .atDay(1);
    }
}
