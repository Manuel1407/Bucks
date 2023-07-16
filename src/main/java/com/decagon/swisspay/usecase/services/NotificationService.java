package com.decagon.swisspay.usecase.services;

import com.decagon.swisspay.domain.entities.message.NotificationEntity;
import com.decagon.swisspay.usecase.payload.request.TransactionRequest;
import com.decagon.swisspay.usecase.payload.response.NotificationResponse;

import java.util.List;

public interface NotificationService {
    NotificationEntity studentSendMoneyNotification(TransactionRequest transactionRequest);

    NotificationEntity walletFundingNotification(TransactionRequest transactionRequest);

    NotificationEntity teacherReceivedNotification(TransactionRequest transactionRequest);

    List<NotificationResponse> allNotificationsOfA_StudentById();

    List<NotificationResponse> allNotificationsOfA_TeacherById();

    NotificationResponse studentAppreciatedNotification(Long transactionId);

}


