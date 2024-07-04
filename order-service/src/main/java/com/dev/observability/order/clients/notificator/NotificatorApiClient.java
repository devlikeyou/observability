package com.dev.observability.order.clients.notificator;

import com.dev.observability.order.clients.notificator.vo.NotificatorRequest;
import feign.HeaderMap;
import feign.RequestLine;

import java.util.Map;

public interface NotificatorApiClient {

    @RequestLine("POST")
    void postNotificator(
            @HeaderMap Map<String, Object> headerMap,
            NotificatorRequest notificatorRequest
    );

}
