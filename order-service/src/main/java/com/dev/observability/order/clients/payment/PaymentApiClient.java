package com.dev.observability.order.clients.payment;

import com.dev.observability.order.clients.payment.vo.PaymentRequest;
import com.dev.observability.order.clients.payment.vo.PaymentResponse;
import feign.HeaderMap;
import feign.RequestLine;

import java.util.Map;

public interface PaymentApiClient {

    @RequestLine("POST")
    PaymentResponse postPayment (
            @HeaderMap Map<String, Object> headerMap,
            PaymentRequest paymentRequest
    );

}
