package tech.ada.rflima.rpgassistant.service;

import org.springframework.stereotype.Service;
import tech.ada.rflima.rpgassistant.dto.CampanhaDTO;
import tech.ada.rflima.rpgassistant.exception.CampanhaNaoEncontradaException;
import tech.ada.rflima.rpgassistant.mapper.CampanhaMapper;
import tech.ada.rflima.rpgassistant.model.CampanhaEntity;
import tech.ada.rflima.rpgassistant.repository.CampanhaRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BuscarCampanhaService {

    private final CampanhaRepository campanhaRepository;
    private final CampanhaMapper mapper;

    public BuscarCampanhaService(CampanhaRepository campanhaRepository, CampanhaMapper mapper) {
        this.campanhaRepository = campanhaRepository;
        this.mapper = mapper;
    }

    public List<CampanhaDTO> buscarCampanhasPorTema(String tema) {
        if (Objects.isNull(tema) || tema.isBlank()) {
            throw new IllegalArgumentException("Tema não pode ser nulo ou vazio");
        }

        List<CampanhaEntity> campanhasEntity = campanhaRepository.findByTema(tema);

        return campanhasEntity
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    public CampanhaDTO buscarCampanhasPorId(Long id) {
        if (Objects.isNull(id)) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }

        Optional<CampanhaEntity> campanhaOptional = campanhaRepository.findById(id);

        CampanhaEntity campanhaEntity = campanhaOptional
                .orElseThrow(() -> new CampanhaNaoEncontradaException(String.format(
                        "Não foi possível encontrar a campanha com o ID %s informado"
                , id)));

        return mapper.toDTO(campanhaEntity);
    }
}
