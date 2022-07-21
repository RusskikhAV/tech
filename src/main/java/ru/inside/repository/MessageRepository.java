package ru.inside.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.inside.model.Message;
import ru.inside.model.User;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Long> {

    Optional<Message> findById(Long id);

    List<Message> findAllByUser(User user);

    List<Message> findAllByUserId(Long id);

}
