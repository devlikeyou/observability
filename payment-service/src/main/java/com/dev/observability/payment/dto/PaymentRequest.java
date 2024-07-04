package com.dev.observability.payment.dto;

import lombok.Data;

@Data
public class PaymentRequest {

    private String type;
    private String cardNumber;
    private Double value;

}
