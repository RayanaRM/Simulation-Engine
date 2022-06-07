package br.unisinos.edu.engine.controller;

import br.unisinos.edu.engine.service.EngineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EngineSimulatorController {

    private final EngineService engineService;

    @PostMapping("/simulate")
    @ResponseStatus(HttpStatus.OK)
    public void criaLugar() {
        engineService.simulate();
    }

}
