package com.dev.observability.payment.sevice;

import com.dev.observability.payment.model.Payment;
import com.dev.observability.payment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;


@Service
public class PaymentService implements IPaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public String createPayment(Payment payment) {

        payment.setTransactionDate(OffsetDateTime.now());
        Payment save = paymentRepository.save(payment);
        return save.getId();

    }
}
