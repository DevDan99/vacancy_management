package br.com.danielcosta.gestao_vagas.modules.candidate.dto;

// DTO (Data Transfer Object) para requisição de autenticação do candidato
// Usando record do Java para criar uma classe imutável e concisa.
public record AuthCandidateRequestDTO(String username, String password) {

}
