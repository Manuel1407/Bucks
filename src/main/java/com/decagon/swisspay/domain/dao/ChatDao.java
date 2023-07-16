package com.decagon.swisspay.domain.dao;

import com.decagon.swisspay.domain.entities.message.ChatEntity;

import java.time.LocalDateTime;

public interface ChatDao extends CrudDao<ChatEntity, Long> {

    ChatEntity findChatByCreatedAt(LocalDateTime localDateTime);
}
