package com.keith.notificationservice.service.impl;

import com.keith.notificationservice.helper.NotificationHelper;
import com.keith.notificationservice.model.Notification;
import com.keith.notificationservice.model.dto.NotificationRequest;
import com.keith.notificationservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationHelper notificationHelper;

    @Override
    public void send(NotificationRequest notificationRequest) {
        Notification notification = Notification.builder()
                .customerId(notificationRequest.getCustomerId())
                .email(notificationRequest.getEmail())
                .sender("Keith")
                .message(notificationRequest.getMessage())
                .build();
        notificationHelper.saveNotification(notification);
    }
}
