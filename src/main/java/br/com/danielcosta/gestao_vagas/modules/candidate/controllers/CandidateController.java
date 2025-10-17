package br.com.danielcosta.gestao_vagas.modules.candidate.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.danielcosta.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.danielcosta.gestao_vagas.modules.candidate.useCases.CreateCandidateUseCase;
import br.com.danielcosta.gestao_vagas.modules.candidate.useCases.ProfileCandidateUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired // Injeção de dependência do Spring.
    private CreateCandidateUseCase createCandidateUseCase;

    @Autowired
    private ProfileCandidateUseCase profileCandidateUseCase;

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

    // Anotação que indica que esse método responde a requisições HTTP GET no endpoint "/candidate/".
    @GetMapping("/")
    public ResponseEntity<Object> get(HttpServletRequest request) {

        var idCandidate = request.getAttribute("candidate_id");

        try {
            var profile = this.profileCandidateUseCase.execute(UUID.fromString(idCandidate.toString()));
            return ResponseEntity.ok().body(profile);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
