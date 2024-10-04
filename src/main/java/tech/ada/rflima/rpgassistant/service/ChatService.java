package tech.ada.rflima.rpgassistant.service;

import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.stereotype.Service;
import tech.ada.rflima.rpgassistant.exception.ChatException;

import java.util.Objects;

@Service
public class ChatService {

    private final OllamaChatModel chatModel;

    public ChatService(OllamaChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String conversar(String mensagem) {
        try {
            if (Objects.isNull(mensagem)) {
                throw new IllegalArgumentException("Mensagem não pode ser nula");
            }
            return chatModel.call(mensagem);
        } catch (RuntimeException e) {
            throw new ChatException(e.getMessage());
        }
    }
}
