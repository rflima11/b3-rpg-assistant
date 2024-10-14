package tech.ada.rflima.rpgassistant.unit.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import tech.ada.rflima.rpgassistant.dto.CampanhaDTO;
import tech.ada.rflima.rpgassistant.mapper.CampanhaMapper;
import tech.ada.rflima.rpgassistant.model.CampanhaEntity;
import tech.ada.rflima.rpgassistant.repository.CampanhaRepository;
import tech.ada.rflima.rpgassistant.service.SalvarCampanhaService;


import static org.junit.jupiter.api.Assertions.*;

class SalvarCampanhaServiceTest {

    SalvarCampanhaService salvarCampanhaService;

    CampanhaRepository campanhaRepositoryMock;
    CampanhaMapper campanhaMapperMock;

    @BeforeEach
    public void setUp() {
        campanhaRepositoryMock = Mockito.mock(CampanhaRepository.class);
        campanhaMapperMock = Mockito.mock(CampanhaMapper.class);

        salvarCampanhaService = new SalvarCampanhaService(
                campanhaRepositoryMock, campanhaMapperMock);
    }

    @Test
    void deveSalvarCampanhaComSucesso() {
        //Cenário
        CampanhaDTO campanhaQueSeraSalva = new CampanhaDTO();
        campanhaQueSeraSalva.setNomeCampanha("Vales Sombrios");
        campanhaQueSeraSalva.setDescricaoCampanha("Luta nos valores sombrios");
        CampanhaEntity campanhaSalva = new CampanhaEntity();
        campanhaSalva.setNomeCampanha(campanhaQueSeraSalva.getNomeCampanha());

        Mockito.when(campanhaMapperMock.toEntity(campanhaQueSeraSalva))
                .thenReturn(campanhaSalva);

        Mockito.when(campanhaRepositoryMock.save(campanhaSalva)).thenReturn(campanhaSalva);


        //Execução
        CampanhaEntity retorno = salvarCampanhaService.executar(campanhaQueSeraSalva);


        //Verificação
        Assertions.assertNotNull(retorno);
        Assertions.assertNotNull(retorno.getDataHoraCriacao());
        Assertions.assertEquals("Vales Sombrios", retorno.getNomeCampanha());
    }

}