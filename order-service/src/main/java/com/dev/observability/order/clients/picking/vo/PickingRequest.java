package com.dev.observability.order.clients.picking.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class PickingRequest {

    private String storeId;
    private String orderId;

}
