package com.dev.observability.order.clients.picking;

import com.dev.observability.order.clients.picking.vo.PickingRequest;
import com.dev.observability.order.clients.picking.vo.PickingResponse;
import feign.RequestLine;

public interface PickingApiClient {

    @RequestLine("POST")
    PickingResponse postPicking(
            PickingRequest pickingRequest
    );
}
