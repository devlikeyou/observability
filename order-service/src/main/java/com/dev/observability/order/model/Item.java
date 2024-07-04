package com.dev.observability.order.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Item {

    private String productId;
    private int quantity;
    private Double unitPrice;
    private int sequence;

}
