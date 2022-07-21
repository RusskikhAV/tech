package ru.inside.service;

import ru.inside.model.Message;
import ru.inside.model.User;

import java.util.List;

public interface MessageService {

    List<Message> findNumberOfRecentMessagesByUser(int count, User user);

    void save(User user, String message);

    List<Message> findAllByUserId(Long id);

}
