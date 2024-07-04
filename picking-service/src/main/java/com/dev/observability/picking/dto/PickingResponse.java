package com.dev.observability.picking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class PickingResponse {
    private String pickingId;
}
