package com.dev.observability.notificator.service;

import com.dev.observability.notificator.model.Notificator;

public interface INotificatorService {

    void sendNotification(Notificator notificator);

}
