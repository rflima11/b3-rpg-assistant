package tech.ada.rflima.rpgassistant.unit.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tech.ada.rflima.rpgassistant.controller.ChatController;
import tech.ada.rflima.rpgassistant.service.ChatService;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class ChatControllerTest {

    static final String PATH = "/v1/chat";

    @InjectMocks
    ChatController controller;

    @Mock
    ChatService service;

    MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .build();
    }

    @Test
    void deveConversarDadoQueFoiInformadoUmaMensagem() throws Exception {
        String mensagem = "Oi, tudo bem?";
        String retorno = "Tudo sim e com você?";

        when(service.conversar(mensagem))
                .thenReturn(retorno);

        mockMvc.perform(get(PATH)
                .contentType(TEXT_PLAIN)
                .queryParam("mensagem", mensagem))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(retorno))
                .andDo(print());
    }

}
