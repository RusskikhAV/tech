package ru.inside.dto;

import lombok.Getter;
import lombok.Setter;
import ru.inside.model.Message;
import ru.inside.model.Role;

import java.util.List;


@Getter
@Setter
public class SaveClientDTO {

    private String username;
    private String message;
    private String password;

    private List<Role> roles;

    private List<Message> messages;

}
