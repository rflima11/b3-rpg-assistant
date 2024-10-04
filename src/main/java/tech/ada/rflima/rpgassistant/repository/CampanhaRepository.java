package tech.ada.rflima.rpgassistant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.ada.rflima.rpgassistant.model.CampanhaEntity;

import java.util.List;

public interface CampanhaRepository extends JpaRepository<CampanhaEntity, Long> {

    List<CampanhaEntity> findByTema(String tema);

}
