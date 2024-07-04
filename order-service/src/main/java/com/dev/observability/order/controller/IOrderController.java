package com.dev.observability.order.controller;

import com.dev.observability.order.dto.OrderRequest;
import com.dev.observability.order.dto.OrderResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

public interface IOrderController {

    public ResponseEntity<OrderResponse> createOrder(
            @RequestHeader String requestTraceId,
            @RequestBody OrderRequest orderRequest);
}
