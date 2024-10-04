package tech.ada.rflima.rpgassistant.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import tech.ada.rflima.rpgassistant.dto.CampanhaDTO;
import tech.ada.rflima.rpgassistant.model.CampanhaEntity;

@Mapper(componentModel = "spring")
public interface CampanhaMapper {
    CampanhaEntity toEntity(CampanhaDTO dto);

    CampanhaDTO toDTO(CampanhaEntity entity);

    @AfterMapping
    default void afterMapping(@MappingTarget CampanhaEntity campanhaEntity) {
        campanhaEntity.getLocacoes().forEach(l -> l.setCampanha(campanhaEntity));
        campanhaEntity.getPersonagens().forEach(p -> p.setCampanha(campanhaEntity));
    }
}
