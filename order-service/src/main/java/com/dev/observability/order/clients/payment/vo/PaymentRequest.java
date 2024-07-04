package com.dev.observability.order.clients.payment.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class PaymentRequest {

    private String type;
    private String cardNumber;
    private Double value;

}
