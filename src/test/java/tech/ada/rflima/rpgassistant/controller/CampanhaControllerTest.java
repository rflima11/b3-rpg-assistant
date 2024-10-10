package tech.ada.rflima.rpgassistant.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tech.ada.rflima.rpgassistant.dto.CampanhaDTO;
import tech.ada.rflima.rpgassistant.dto.CampanhaRequestDTO;
import tech.ada.rflima.rpgassistant.dto.ConsultaCampanhaDTOResponse;
import tech.ada.rflima.rpgassistant.service.BuscarCampanhaService;
import tech.ada.rflima.rpgassistant.service.CriarCampanhaService;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class CampanhaControllerTest {

    static final String PATH = "/v1/campanhas";

    @InjectMocks
    CampanhaController campanhaController;

    @Mock
    CriarCampanhaService criarCampanhaService;

    @Mock
    BuscarCampanhaService buscarCampanhaService;

    MockMvc mockMvc;


    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(campanhaController).build();
    }

    @Test
    void deveCriarCampanhaComSucesso() throws Exception {
        //Cenário
        String tema = "medieval";
        CampanhaRequestDTO request = new CampanhaRequestDTO(tema);
        CampanhaDTO retorno = new CampanhaDTO();
        retorno.setNomeCampanha("Bosques Sombrios 2");

        when(criarCampanhaService.executar(any()))
                .thenReturn(retorno);

        //Verificação e execução
        mockMvc.perform(post(PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.asJsonString(request)))
                .andExpect(status().isCreated())
                .andExpect(content().json(TestUtils.asJsonString(retorno)))
                .andDo(print());
    }

    @Test
    void deveBuscarCampanhaPorTemaComSucesso() throws Exception {
        //Cenário
        String tema = "medieval";
        ConsultaCampanhaDTOResponse consultaCampanhaDTOResponse = new ConsultaCampanhaDTOResponse();
        consultaCampanhaDTOResponse.setNomeCampanha("Bosques Sombrios");
        consultaCampanhaDTOResponse.setId(1L);

        List<ConsultaCampanhaDTOResponse> retornoBancoDeDados = Collections
                .singletonList(consultaCampanhaDTOResponse);

        when(buscarCampanhaService.buscarCampanhasPorTema(tema))
                .thenReturn(retornoBancoDeDados);

        //Execução e Verificação
        mockMvc.perform(get(PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .queryParam("tema", tema))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(TestUtils.asJsonString(retornoBancoDeDados)))
                .andDo(print());
    }

    @Test
    void deveRetornar400QuandoNaoEhInformadoQueryParamObrigatorio() throws Exception {
        mockMvc.perform(get(PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }

}
