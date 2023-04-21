package com.keith.notificationservice.helper;


import com.keith.notificationservice.model.Notification;
import com.keith.notificationservice.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationHelper {

    private final NotificationRepository notificationRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
    public void saveNotification(Notification notification){
        notificationRepository.save(notification);
        log.info("Notification saved : {}", notification);
    }
}
