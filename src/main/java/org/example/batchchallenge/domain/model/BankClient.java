package org.example.batchchallenge.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankClient {
    @Id
    private UUID id;
    private String name;
    private String documentNumber;
    private String branch;
    private String account;
    private BigDecimal value;
    private LocalDate referenceDate;
}
