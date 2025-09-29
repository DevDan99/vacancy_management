package br.com.danielcosta.gestao_vagas.modules.candidate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.danielcosta.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.danielcosta.gestao_vagas.modules.candidate.useCases.CreateCandidateUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired // Injeção de dependência do Spring.
    private CreateCandidateUseCase createCandidateUseCase;

    // Anotação que indica que esse método responde a requisições HTTP POST no endpoint "/candidate/".
    @PostMapping("/")

    // Este método cria um novo candidato. e retorna uma resposta HTTP.
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidate) {
        // System.out.println(String.format("Candidato: %s criado com sucesso!", candidate.getEmail()));
        try {
            var result = this.createCandidateUseCase.execute(candidate);
            return ResponseEntity.ok().body(result);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
