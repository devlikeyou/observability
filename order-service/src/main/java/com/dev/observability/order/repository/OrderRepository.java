package com.dev.observability.order.repository;

import com.dev.observability.order.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository  extends MongoRepository<Order, String> {
}
