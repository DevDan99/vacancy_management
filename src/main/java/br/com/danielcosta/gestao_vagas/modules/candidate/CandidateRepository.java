package br.com.danielcosta.gestao_vagas.modules.candidate;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository já possui métodos prontos para operações básicas de CRUD.
// O primeiro parâmetro é a entidade que o repositório vai gerenciar e o segundo é o tipo do ID da entidade.
// Esse é o repositório para a entidade CandidateEntity.
public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID> {
	// Método personalizado para encontrar um candidato por username ou email.
	// Método é do JpaRepository, que cria a query automaticamente com base no nome do método.
	// Optional é usado para evitar null pointer exceptions e indicar que o resultado pode estar ausente.
	Optional<CandidateEntity> findByUsernameOrEmail(String username, String email);

	Optional<CandidateEntity> findByUsername(String username);
}
