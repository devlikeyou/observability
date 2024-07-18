package com.dev.observability.order.controller;

import com.dev.observability.order.dto.OrderRequest;
import com.dev.observability.order.dto.OrderResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface IOrderController {

    public ResponseEntity<OrderResponse> createOrder(
            @RequestBody OrderRequest orderRequest);
}
