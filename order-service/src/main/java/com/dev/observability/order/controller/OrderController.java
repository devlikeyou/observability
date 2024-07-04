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

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController implements IOrderController {

    private final IOrderService orderService;

    @Override
    @PostMapping
    @Trace
    public ResponseEntity<OrderResponse> createOrder(String requestTraceId, OrderRequest orderRequest) {

        log.info("Received a request to create a new Order" );

        if(hasError(requestTraceId)) {
            log.error("Order has an Internal Error");
            throw new InternalServerErrorException("Order has an Internal Error");
        }

        Order order = orderService.createOrder(requestTraceId, orderRequest);

        log.info("Order Created with sucessfully {} " ,order.getId() );
        return ResponseEntity.ok(OrderResponse.builder().id(order.getId()).build());
    }

    private boolean hasError(String value){
        return Arrays.asList("0","1").stream()
                .anyMatch(c-> c.equals(value.substring(0,1)));
    }

}
