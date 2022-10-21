package com.decagon.reward_your_teacher.usecase.payload.request;

import com.decagon.reward_your_teacher.domain.entities.enums.NotificationType;

public record NotificationRequest(String notificationBody, NotificationType notificationType) {

}
