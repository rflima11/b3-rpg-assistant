package tech.ada.rflima.rpgassistant.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.ada.rflima.rpgassistant.service.ChatService;

@RestController
@RequestMapping("/v1/chat")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping
    public ResponseEntity<String> conversar(@RequestParam String mensagem) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(chatService.conversar(mensagem));
    }
}
