package tech.ada.rflima.rpgassistant.unit.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.junit.jupiter.api.Test;
import tech.ada.rflima.rpgassistant.dto.CampanhaDTO;
import tech.ada.rflima.rpgassistant.dto.ConsultaCampanhaDTOResponse;
import tech.ada.rflima.rpgassistant.exception.CampanhaNaoEncontradaException;
import tech.ada.rflima.rpgassistant.mapper.CampanhaMapperImpl;
import tech.ada.rflima.rpgassistant.model.CampanhaEntity;
import tech.ada.rflima.rpgassistant.repository.CampanhaRepository;
import tech.ada.rflima.rpgassistant.service.BuscarCampanhaService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BuscarCampanhaServiceTest {

    BuscarCampanhaService buscarCampanhaService;

    CampanhaRepository campanhaRepository;

    @BeforeEach
    public void setUp() {
        campanhaRepository = Mockito.mock(CampanhaRepository.class);

        buscarCampanhaService = new BuscarCampanhaService(
                campanhaRepository,
                new CampanhaMapperImpl());
    }

    @Test
    void deveBuscarCampanhaPorTemaValidoComSucesso() {
        //Cenário
        String tema = "mediaval";

        List<CampanhaEntity> campanhasDoBancoDeDadosFake = new ArrayList<>();
        CampanhaEntity campanhaEntity1 = new CampanhaEntity();
        CampanhaEntity campanhaEntity2 = new CampanhaEntity();
        campanhasDoBancoDeDadosFake.add(campanhaEntity2);
        campanhasDoBancoDeDadosFake.add(campanhaEntity1);

        Mockito.when(campanhaRepository.findByTema(tema)).thenReturn(campanhasDoBancoDeDadosFake);

        //Ação
        List<ConsultaCampanhaDTOResponse> campanhaDTOS = buscarCampanhaService.buscarCampanhasPorTema(tema);

        //Verificação
        Assertions.assertNotNull(campanhaDTOS);
        Assertions.assertFalse(campanhaDTOS.isEmpty());
        Assertions.assertEquals(2, campanhaDTOS.size());
    }

    @Test
    void deveLancarExcecaoQuandoOTemaForInvalido() {
        //cenário
        String tema = null;
        Exception e = null;

        try {
            List<ConsultaCampanhaDTOResponse> campanhaDTOS = buscarCampanhaService.buscarCampanhasPorTema(tema);
        } catch (Exception ex) {
            e = ex;
        }

        Assertions.assertNotNull(e);
        Assertions.assertInstanceOf(IllegalArgumentException.class, e);
    }

    @Test
    void deveLancarExcecaoQuandoOTemaForVazio() {
        String tema = "";

        IllegalArgumentException exceptionRetornada = Assertions.assertThrows(IllegalArgumentException.class, () -> buscarCampanhaService.buscarCampanhasPorTema(tema));

        Assertions.assertNotNull(exceptionRetornada.getMessage());
        Assertions.assertEquals("Tema não pode ser nulo ou vazio", exceptionRetornada.getMessage());
    }

    @Test
    void deveRetornarCampanhaPorId() {
        //cenário
        Long id = 1L;
        CampanhaEntity campanhaEntity = new CampanhaEntity();
        campanhaEntity.setNomeCampanha("AVENTURA PELOS BOSQUES DO MOCKITO");

        Mockito.when(campanhaRepository.findById(id))
                .thenReturn(Optional.of(campanhaEntity));

        //execução
        CampanhaDTO campanhaDTO = buscarCampanhaService.buscarCampanhasPorId(id);

        //verificação
        Assertions.assertNotNull(campanhaDTO);
        Assertions.assertEquals(campanhaEntity.getNomeCampanha(), campanhaDTO.getNomeCampanha());
    }

    @Test
    void deveLancarExcecaoQuandoNaoForEncontradoNenhumRegistroPorId() {
        //cenário
        Long id = 3L;

        CampanhaNaoEncontradaException campanhaNaoEncontradaException = Assertions.assertThrows(CampanhaNaoEncontradaException.class,
                () -> buscarCampanhaService.buscarCampanhasPorId(id));

        Assertions.assertNotNull(campanhaNaoEncontradaException);
        Assertions.assertEquals(String.format("Não foi possível encontrar a campanha com o ID %s informado", id), campanhaNaoEncontradaException.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoOIdForInvalido() {
        Long id = null;

        IllegalArgumentException illegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class,
                () -> buscarCampanhaService.buscarCampanhasPorId(id));

        Assertions.assertNotNull(illegalArgumentException);
        Assertions.assertEquals("ID não pode ser nulo", illegalArgumentException.getMessage());

    }




}
