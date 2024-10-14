    package tech.ada.rflima.rpgassistant.integration.repository;


    import org.junit.jupiter.api.*;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
    import tech.ada.rflima.rpgassistant.model.CampanhaEntity;
    import tech.ada.rflima.rpgassistant.repository.CampanhaRepository;

    import java.util.List;
    import java.util.Optional;

    @DataJpaTest
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class CampanhaRepositoryTest {

        @Autowired
        CampanhaRepository campanhaRepository;

        CampanhaEntity campanhaEntity;

        @BeforeEach
        public void setUp() {
            campanhaEntity = new CampanhaEntity();
            campanhaEntity.setNomeCampanha("Bosque 1");
            campanhaEntity.setTema("Medieval");
            campanhaRepository.save(campanhaEntity);
        }

        @AfterEach
        public void after() {
            campanhaRepository.delete(campanhaEntity);
        }

        @Test
        @Order(3)
        void deveSalvarUmaCampanhaComSucesso() {
            //Cenário
            CampanhaEntity campanhaEntity = new CampanhaEntity();
            campanhaEntity.setLocalCampanha("Bosques Sombrios");
            campanhaEntity.setTema("Medieval");

            campanhaEntity.setNomeCampanha("A busuca do bosque sombrio");

            //Execução
            CampanhaEntity objetoSalvo = campanhaRepository.save(campanhaEntity);

            //Validação
            Assertions.assertNotNull(objetoSalvo);
            Assertions.assertNotNull(objetoSalvo.getId());
        }

        @Test
        @Order(2)
        void deveEncontrarUmCampanhaPeloTema() {
            //Cenário
            String tema = "Medieval";

            //Execução
            List<CampanhaEntity> lista = campanhaRepository.findByTema(tema);

            //Validação
            Assertions.assertFalse(lista.isEmpty());
            Assertions.assertEquals(tema, lista.get(0).getTema());
        }

        @Test
        @Order(1)
        void deveAtualizarORegistro() {
            //Cenário
            String nomeCampanhaAtualizado = "Bosques - ATUALIZADO";
            Optional<CampanhaEntity> registroOptional = campanhaRepository.findById(1L);
            CampanhaEntity campanhaEntity = registroOptional.orElseThrow();
            campanhaEntity.setNomeCampanha(nomeCampanhaAtualizado);

            //Execução
            CampanhaEntity registroAtualizado = campanhaRepository.save(campanhaEntity);

            //Verificação
            Assertions.assertNotNull(registroAtualizado);
            Assertions.assertEquals(1L, registroAtualizado.getId());
            Assertions.assertEquals(nomeCampanhaAtualizado, registroAtualizado.getNomeCampanha());
        }

        @Test
        @Order(4)
        void deveDeletarORegistro() {
            campanhaRepository.delete(campanhaEntity);

            Optional<CampanhaEntity> registro = campanhaRepository.findById(campanhaEntity.getId());

            //Verificação
            Assertions.assertTrue(registro.isEmpty());

        }


    }
