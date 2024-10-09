package tech.ada.rflima.rpgassistant.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.MapOutputConverter;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.stereotype.Service;
import tech.ada.rflima.rpgassistant.dto.CampanhaDTO;
import tech.ada.rflima.rpgassistant.dto.CampanhaRequestDTO;
import tech.ada.rflima.rpgassistant.dto.LocacaoDTO;
import tech.ada.rflima.rpgassistant.dto.PersonagemDTO;

import java.util.Arrays;
import java.util.Map;

@Service
public class CriarCampanhaService {

    private static final String MESTRE_RPG_GERANDO_CAMPANHA = """
            Você é uma API que também é um mestre de RPG, e como mestre de RPG deve criar uma campanha
            no tema {tema}, e devolver isso em uma resposta apenas no formato JSON. Idioma: PT-BR. 
            Crie a campanha baseado nos atributos {atributosCampanha}, os personagens baseado nos atributos {atributosPersonagens}  
            e as locacoes baseadas nos atributos {atributosLocacoes}
            """;

    private final OllamaChatModel chatModel;

    private final SalvarCampanhaService salvarCampanhaService;
    private final ObjectMapper objectMapper;

    public CriarCampanhaService(OllamaChatModel chatModel, SalvarCampanhaService salvarCampanhaService, ObjectMapper objectMapper) {
        this.chatModel = chatModel;
        this.salvarCampanhaService = salvarCampanhaService;
        this.objectMapper = objectMapper;
    }

    public CampanhaDTO executar(CampanhaRequestDTO request) {
        String tema = request.getTema();

        MapOutputConverter mapOutputConverter = new MapOutputConverter();

        ChatResponse chatResponse = chatModel.call(this.generatePromptTemplate(tema).create());

        Map<String, Object> respostaMap = mapOutputConverter.convert(chatResponse.getResult().getOutput().getContent());

        CampanhaDTO campanhaDTO = objectMapper.convertValue(respostaMap, CampanhaDTO.class);
        campanhaDTO.setTema(campanhaDTO.getTema());
        salvarCampanhaService.executar(campanhaDTO);
        return campanhaDTO;
    }

    private PromptTemplate generatePromptTemplate(String tema) {
        MapOutputConverter mapOutputConverter = new MapOutputConverter();

        PromptTemplate promptTemplate = new PromptTemplate(MESTRE_RPG_GERANDO_CAMPANHA); //, Map.of("format", mapOutputConverter.getFormat()));

        promptTemplate.add("tema", tema);
        promptTemplate.add("atributosCampanha", CampanhaDTO.class.getDeclaredFields());
        promptTemplate.add("atributosPersonagens", PersonagemDTO.class.getDeclaredFields());
        promptTemplate.add("atributosLocacoes", LocacaoDTO.class.getDeclaredFields());

        return promptTemplate;
    }
}
