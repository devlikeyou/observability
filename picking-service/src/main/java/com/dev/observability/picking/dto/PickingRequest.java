package com.dev.observability.picking.dto;

import lombok.Data;

@Data
public class PickingRequest {

    private String storeId;
    private String orderId;

}
