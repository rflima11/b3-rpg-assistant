package tech.ada.rflima.rpgassistant.controller;


import org.json.HTTP;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.ada.rflima.rpgassistant.dto.CampanhaDTO;
import tech.ada.rflima.rpgassistant.service.CriarCampanhaService;

@RestController
@RequestMapping("/v1/campanhas")
public class CampanhaController {

    private final CriarCampanhaService criarCampanhaService;

    public CampanhaController(CriarCampanhaService criarCampanhaService) {
        this.criarCampanhaService = criarCampanhaService;
    }

    @PostMapping
    public ResponseEntity<CampanhaDTO> criarCampanha(@RequestBody String tema) {
        return ResponseEntity.status(HttpStatus.CREATED).body(criarCampanhaService.executar(tema));
    }
}
