package com.dev.observability.payment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {

    @Id
    private String id;

    private OffsetDateTime transactionDate;

    private String type;
    private String cardNumber;
    private Double value;

}
