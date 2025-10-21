package br.com.danielcosta.gestao_vagas.modules.company.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.danielcosta.gestao_vagas.modules.company.entities.CompanyEntity;

// Interface que estende JpaRepository para fornecer operações CRUD para a entidade CompanyEntity.
public interface CompanyRepository extends JpaRepository<CompanyEntity, UUID> {
	// Aqui você pode adicionar métodos personalizados de consulta, se necessário.

	// Exemplo de método personalizado para encontrar uma empresa por nome de usuário ou email.
	Optional<CompanyEntity> findByUsernameOrEmail(String username, String email);

	Optional<CompanyEntity> findByUsername(String username);
}
