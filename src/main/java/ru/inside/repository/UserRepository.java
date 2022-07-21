package ru.inside.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.inside.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
