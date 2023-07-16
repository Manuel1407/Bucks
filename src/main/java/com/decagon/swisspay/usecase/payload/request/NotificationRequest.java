package com.decagon.swisspay.usecase.payload.request;

import com.decagon.swisspay.domain.entities.enums.NotificationType;

public record NotificationRequest(String notificationBody, NotificationType notificationType) {

}
