package com.dev.observability.notificator.controller;


import com.dev.observability.notificator.dto.NotificatorRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

public interface INotificatorController {

    ResponseEntity<Void> createNotification(
            @RequestBody NotificatorRequest notificatorRequest
            );

}
