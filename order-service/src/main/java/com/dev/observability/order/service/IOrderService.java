package com.dev.observability.order.service;

import com.dev.observability.order.dto.OrderRequest;
import com.dev.observability.order.model.Order;

public interface IOrderService {

    Order createOrder(String requestTraceId, OrderRequest orderRequest);


}
