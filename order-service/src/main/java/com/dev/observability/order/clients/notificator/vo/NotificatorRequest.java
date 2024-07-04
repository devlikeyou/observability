package com.dev.observability.order.clients.notificator.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class NotificatorRequest {

    private String type;
    private String msg;
    private String sender;
    private String receiver;

}
