package ru.inside.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.inside.model.Message;
import ru.inside.model.User;
import ru.inside.repository.MessageRepository;
import ru.inside.service.MessageService;

import java.util.List;

@Service
@Slf4j
@Qualifier("messageService")
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public List<Message> findNumberOfRecentMessagesByUser(int count, User user) {
        List<Message> messages = messageRepository.findAllByUser(user);

        if (count != 0 && messages.size() > count) {
            List<Message> numberOfRecentMessages = messages.subList((messages.size() - count - 1), messages.size() - 1);
            return numberOfRecentMessages;
        }
        return messages;
    }

    @Override
    public List<Message> findAllByUserId(Long id) {

        return messageRepository.findAllByUserId(id);
    }

    @Override
    public void save(User user, String message) {
        Message newMsg = new Message();
        newMsg.setMessage(message);
        newMsg.setUser(user);
        messageRepository.save(newMsg);
    }

}
