package com.dev.observability.payment.sevice;

import com.dev.observability.payment.model.Payment;

public interface IPaymentService {

    String createPayment(Payment payment);

}
