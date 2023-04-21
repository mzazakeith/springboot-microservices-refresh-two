package com.keith.notificationservice.service;


import com.keith.notificationservice.model.dto.NotificationRequest;

public interface NotificationService {
    void send(NotificationRequest notificationRequest);
}
