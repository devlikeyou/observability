package com.dev.observability.notificator.controller;

import com.dev.observability.notificator.dto.NotificatorRequest;
import com.dev.observability.notificator.model.Notificator;
import com.dev.observability.notificator.service.NotificatorService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.net.URI;

@Log4j2
@RestController
@RequestMapping("/notificator")
public class NotificatorController implements INotificatorController {

    @Autowired
    private NotificatorService notificatorService;

    @Override
    @PostMapping
    public ResponseEntity<Void> createNotification(NotificatorRequest notificatorRequest) {

        notificatorService.sendNotification(
                Notificator.builder()
                        .sender(notificatorRequest.getSender())
                        .receiver(notificatorRequest.getReceiver())
                        .msg(notificatorRequest.getMsg())
                        .type(notificatorRequest.getType())
                        .build()
        );

        return ResponseEntity.created(URI.create("")).build();
    }

}
