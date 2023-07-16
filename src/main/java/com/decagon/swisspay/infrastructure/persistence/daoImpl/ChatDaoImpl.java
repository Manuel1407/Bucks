package com.decagon.swisspay.infrastructure.persistence.daoImpl;

import com.decagon.swisspay.domain.dao.ChatDao;
import com.decagon.swisspay.domain.entities.message.ChatEntity;
import com.decagon.swisspay.infrastructure.persistence.repository.ChatRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ChatDaoImpl extends CrudDaoImpl<ChatEntity, Long> implements ChatDao {

    private final ChatRepository chatRepository;

    public ChatDaoImpl(ChatRepository chatRepository) {
        super(chatRepository);
        this.chatRepository = chatRepository;
    }

    @Override
    public ChatEntity findChatByCreatedAt(LocalDateTime localDateTime) {
        Optional<ChatEntity> optionalChat = chatRepository.findChatEntitiesByCreatedAt(localDateTime);

        if (!optionalChat.isPresent()) {
            throw new RuntimeException();
        }

        return optionalChat.get();
    }


}
