package com.dev.observability.order.clients.notificator;

import com.dev.observability.order.clients.notificator.vo.NotificatorRequest;
import feign.RequestLine;

public interface NotificatorApiClient {

    @RequestLine("POST")
    void postNotificator(
            NotificatorRequest notificatorRequest
    );

}
