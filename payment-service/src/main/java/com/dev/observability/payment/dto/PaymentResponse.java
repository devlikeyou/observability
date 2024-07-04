package com.dev.observability.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class PaymentResponse {

    private String transactionPaymentId;

}
