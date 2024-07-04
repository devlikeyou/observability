package com.dev.observability.order.clients.picking;

import com.dev.observability.order.clients.picking.vo.PickingRequest;
import com.dev.observability.order.clients.picking.vo.PickingResponse;
import feign.HeaderMap;
import feign.RequestLine;

import java.util.Map;

public interface PickingApiClient {

    @RequestLine("POST")
    PickingResponse postPicking(
            @HeaderMap Map<String, Object> headerMap,
            PickingRequest pickingRequest
    );
}
