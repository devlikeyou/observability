package com.dev.observability.order.model;

public enum OrderStatus {

    CREATED,
    PAYMENT_PENDING,
    PAYMENT_FAILED,
    PAYMENT_APPROVED,
    PICKING_PENDING,
    PICKING_FAILED,
    IN_DELIVERY,
    DELIVERED,
    IN_CANCEL_PROCESSING,
    CANCELED

}
