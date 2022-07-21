package ru.inside.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.inside.dto.SaveClientDTO;
import ru.inside.model.Message;
import ru.inside.model.User;
import ru.inside.repository.UserRepository;
import ru.inside.service.MessageService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/message")
@Slf4j
public class MessageController {
    private final MessageService messageService;
    private final UserRepository userRepository;

    public MessageController(MessageService messageService, UserRepository userRepository) {
        this.messageService = messageService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity history(@RequestBody SaveClientDTO user) {
        Map<Object, Object> response = new HashMap<>();

        String username = user.getUsername();
        String message = user.getMessage();

        User currentUser = userRepository.findByUsername(user.getUsername());

        log.info("In get mesg currenUser: {}", currentUser);
        List<Message> messages = messageService.findAllByUserId(currentUser.getId());

        int historyCount = checkMessage(message);

        if (messages.isEmpty()) {
            response.put("1", "У пользователя " + username + " нет сообщений");
        }
        if (historyCount > 0 && historyCount <= messages.size()) {
            List<Message> localMsg = messages.subList(messages.size() - historyCount, messages.size());
            response = localMsg.stream().collect(Collectors.toMap(Message::getId, Message::getMessage));
        } else {
            response = messages.stream().collect(Collectors.toMap(Message::getId, Message::getMessage));
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity addNewMsg(@RequestBody SaveClientDTO user) {
        Map<Object, Object> response = new HashMap<>();

        String username = user.getUsername();
        String message = user.getMessage();

        User currentUser = userRepository.findByUsername(user.getUsername());

        log.info("In save new msg where msg: {} and username: {}", message, username);

        if (message == null) {
            response.put("Error", "Message can't be empty");
        } else {
            messageService.save(currentUser, message);
            response.put(username, message);
        }

        return ResponseEntity.ok(response);
    }

   private static int checkMessage(String str) {
        int a = 0;

        if (str == null) {
            return a;
        }

        String[] strArray = str.split(" ");

        for (String localStr : strArray) {
            if(localStr.equals("history")) {
                log.info("User wanna get history");
            } else {
                try {
                    a = Integer.parseInt(localStr);
                } catch (NumberFormatException e ) {
                    log.info("catch NumberFormatException");
                }

                return a;
            }
        }
        return a;
    }
}
