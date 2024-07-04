package com.dev.observability.payment.repository;

import com.dev.observability.payment.model.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentRepository extends MongoRepository<Payment, String> {
}
