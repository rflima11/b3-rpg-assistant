package tech.ada.rflima.rpgassistant.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CampanhaControllerTest {

    @InjectMocks
    CampanhaController campanhaController;
    @Mock
    CriarCampanhaService criarCampanhaService;
    @Mock
    BuscarCampanhaService buscarCampanhaService;
    MockMvc mockMvc;
    final String PATH_CAMPANHAS = "/v1/campanhas";
    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(campanhaController).build();
    }

    @Test
    void criarCampanha() {
    }

    @Test
    void criarCampanhaSucesso() throws Exception {
        String tema = "medieval";

        CampanhaRequestDTO campanhaRequestDTO = new CampanhaRequestDTO(tema);
        CampanhaDTO retornoMock = new CampanhaDTO();
        retornoMock.setDescricaoCampanha("Campanha Mock");
        retornoMock.setDescricaoCampanha("Descrição da campanha");

        String request = asJsonString(campanhaRequestDTO);

        List<ConsultaCampanhaDTOResponse> response = Collections.singletonList(new ConsultaCampanhaDTOResponse());

        Mockito.when(criarCampanhaService.executar(Mockito.any()))
                .thenReturn(retornoMock);

        mockMvc.perform(
                        MockMvcRequestBuilders.post(PATH_CAMPANHAS)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(request))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(asJsonString(retornoMock)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void buscarCampanhasPorTema() throws Exception {
        String tema = "medieval";

        List<ConsultaCampanhaDTOResponse> response = Collections.singletonList(new ConsultaCampanhaDTOResponse());

        Mockito.when(buscarCampanhaService.buscarCampanhasPorTema(tema))
                .thenReturn(response);

        mockMvc.perform(
                MockMvcRequestBuilders.get(PATH_CAMPANHAS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .queryParam("tema", tema))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().json(asJsonString(response)))
                .andDo(MockMvcResultHandlers.print());
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}