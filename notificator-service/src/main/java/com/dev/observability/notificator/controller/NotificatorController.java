package com.dev.observability.notificator.controller;

import com.dev.observability.notificator.dto.NotificatorRequest;
import com.dev.observability.notificator.exceptions.InternalServerErrorException;
import com.dev.observability.notificator.model.Notificator;
import com.dev.observability.notificator.service.NotificatorService;
import com.newrelic.api.agent.Trace;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Arrays;

@Log4j2
@RestController
@RequestMapping("/notificator")
public class NotificatorController implements INotificatorController {

    @Autowired
    private NotificatorService notificatorService;

    @Trace
    @Override
    @PostMapping
    public ResponseEntity<Void> createNotification(String requestTraceId, NotificatorRequest notificatorRequest) {

        log.info("Received a request to create a Notification");

        if(hasError(requestTraceId)) {
            log.error("Notification has an Internal Error");
            throw new InternalServerErrorException("Notification has an Internal Error");
        }

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

    private boolean hasError(String value){
        return Arrays.asList("8","9").stream()
                .anyMatch(c-> c.equals(value.substring(0,1)));
    }

}
