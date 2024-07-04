package com.dev.observability.notificator.dto;

import lombok.Data;

@Data
public class NotificatorRequest {

    private String type;
    private String msg;
    private String sender;
    private String receiver;

}
