package com.dev.observability.payment.controller;

import com.dev.observability.payment.dto.PaymentRequest;
import com.dev.observability.payment.dto.PaymentResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

public interface IPaymentController {

    public ResponseEntity<PaymentResponse> createPayment(
            @RequestBody PaymentRequest paymentRequest
    );

}
