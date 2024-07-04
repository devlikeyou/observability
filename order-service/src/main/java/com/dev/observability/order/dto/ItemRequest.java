package com.dev.observability.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ItemRequest {

    private String productId;
    private int quantity;
    private Double unitPrice;
    private int sequence;

}
