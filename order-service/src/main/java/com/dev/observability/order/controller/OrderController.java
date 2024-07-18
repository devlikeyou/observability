package com.dev.observability.order.controller;


import com.dev.observability.order.dto.OrderRequest;
import com.dev.observability.order.dto.OrderResponse;
import com.dev.observability.order.exceptions.InternalServerErrorException;
import com.dev.observability.order.model.Order;
import com.dev.observability.order.service.IOrderService;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController implements IOrderController {

    private final IOrderService orderService;

    @Override
    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(OrderRequest orderRequest) {

        Order order = orderService.createOrder(orderRequest);
        return ResponseEntity.ok(OrderResponse.builder().id(order.getId()).build());
    }

}
