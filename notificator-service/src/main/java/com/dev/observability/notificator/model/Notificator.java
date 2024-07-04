package com.dev.observability.notificator.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Notificator {

    private String type;
    private String msg;
    private String sender;
    private String receiver;

}
