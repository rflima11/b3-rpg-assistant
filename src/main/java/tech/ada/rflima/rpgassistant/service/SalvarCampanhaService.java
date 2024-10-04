package tech.ada.rflima.rpgassistant.service;

import org.springframework.stereotype.Service;
import tech.ada.rflima.rpgassistant.dto.CampanhaDTO;
import tech.ada.rflima.rpgassistant.mapper.CampanhaMapper;
import tech.ada.rflima.rpgassistant.model.CampanhaEntity;
import tech.ada.rflima.rpgassistant.repository.CampanhaRepository;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class SalvarCampanhaService {

    private final CampanhaRepository campanhaRepository;
    private final CampanhaMapper campanhaMapper;

    public SalvarCampanhaService(CampanhaRepository campanhaRepository, CampanhaMapper campanhaMapper) {
        this.campanhaRepository = campanhaRepository;
        this.campanhaMapper = campanhaMapper;
    }

    public CampanhaEntity executar(CampanhaDTO campanhaDTO) {
        CampanhaEntity entity = campanhaMapper.toEntity(campanhaDTO);
        entity.setDataHoraCriacao(LocalDateTime.now(Clock.system(ZoneId.systemDefault())));
        return campanhaRepository.save(entity);
    }
}
