package com.dev.observability.order.clients.payment;

import com.dev.observability.order.clients.payment.vo.PaymentRequest;
import com.dev.observability.order.clients.payment.vo.PaymentResponse;
import feign.RequestLine;

public interface PaymentApiClient {

    @RequestLine("POST")
    PaymentResponse postPayment (
            PaymentRequest paymentRequest
    );

}
