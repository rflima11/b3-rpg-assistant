package tech.ada.rflima.rpgassistant.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.ada.rflima.rpgassistant.dto.CampanhaDTO;
import tech.ada.rflima.rpgassistant.dto.CampanhaRequestDTO;
import tech.ada.rflima.rpgassistant.dto.ConsultaCampanhaDTOResponse;
import tech.ada.rflima.rpgassistant.service.BuscarCampanhaService;
import tech.ada.rflima.rpgassistant.service.CriarCampanhaService;

import java.util.List;

@RestController
@RequestMapping("/v1/campanhas")
public class CampanhaController {

    private final CriarCampanhaService criarCampanhaService;
    private final BuscarCampanhaService buscarCampanhaService;

    public CampanhaController(CriarCampanhaService criarCampanhaService, BuscarCampanhaService buscarCampanhaService) {
        this.criarCampanhaService = criarCampanhaService;
        this.buscarCampanhaService = buscarCampanhaService;
    }

    @PostMapping
    public ResponseEntity<CampanhaDTO> criarCampanha(@RequestBody CampanhaRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(criarCampanhaService.executar(request));
    }

    @GetMapping
    public ResponseEntity<List<ConsultaCampanhaDTOResponse>> buscarCampanhasPorTema(@RequestParam String tema) {
        return ResponseEntity.status(HttpStatus.OK).body(buscarCampanhaService.buscarCampanhasPorTema(tema));
    }
}
