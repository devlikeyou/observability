package com.dev.observability.notificator.service;

import com.dev.observability.notificator.model.Notificator;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class NotificatorService implements INotificatorService {

    @Override
    public void sendNotification(Notificator notificator) {
        //Process to Notification

    }
}
