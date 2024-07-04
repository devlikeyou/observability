package com.dev.observability.payment.controller;

import com.dev.observability.payment.dto.PaymentRequest;
import com.dev.observability.payment.dto.PaymentResponse;
import com.dev.observability.payment.exceptions.InternalServerErrorException;
import com.dev.observability.payment.model.Payment;
import com.dev.observability.payment.sevice.IPaymentService;
import com.newrelic.api.agent.Trace;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@Log4j2
@RestController
@RequestMapping("/payments")
public class PaymentController implements IPaymentController {

    @Autowired
    private IPaymentService paymentService;

    @Override
    @PostMapping
    @Trace
    public ResponseEntity<PaymentResponse> createPayment(String requestTraceId, PaymentRequest paymentRequest) {

        log.info("Received a request to create a payment");

        if(hasError(requestTraceId)) {
            log.error("Payment has an Internal Error");
            throw new InternalServerErrorException("Payment has an Internal Error");
        }

        String paymentId = paymentService.createPayment(Payment.builder()
                        .cardNumber(paymentRequest.getCardNumber())
                        .type(paymentRequest.getType())
                        .value(paymentRequest.getValue())
                .build());

        log.info("Payment created with successfully {} ", paymentId);
        return ResponseEntity.ok(
                    PaymentResponse.builder()
                            .transactionPaymentId(paymentId)
                            .build());
    }

    private boolean hasError(String value){
        return Arrays.asList("2","3","4").stream()
                .anyMatch(c-> c.equals(value.substring(0,1)));
    }
}
