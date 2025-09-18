package br.com.danielcosta.gestao_vagas.modules.candidate.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.danielcosta.gestao_vagas.modules.candidate.CandidateEntity;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @PostMapping("/")
    // Recebe do body, tipo CandidateEntity
    public void create(@Valid @RequestBody CandidateEntity candidate) {
        System.out.println(String.format("Candidato: %s criado com sucesso!", candidate.getEmail()));
    }
}
