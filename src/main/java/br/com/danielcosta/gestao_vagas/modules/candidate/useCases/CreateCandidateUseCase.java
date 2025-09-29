package br.com.danielcosta.gestao_vagas.modules.candidate.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.danielcosta.gestao_vagas.exceptions.UserFoundException;
import br.com.danielcosta.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.danielcosta.gestao_vagas.modules.candidate.CandidateRepository;

@Service // Anotação que indica que essa classe é um serviço do Spring.
public class CreateCandidateUseCase {

	@Autowired // Injeção de dependência do Spring.
	private CandidateRepository candidateRepository;

	public CandidateEntity execute(CandidateEntity candidate) {
		// This é o meu objeto atual do tipo candidateController, então quando uso
		// this.candidateRepository.findByUsernameOrEmail estou acessando o objeto do tipo candidateController usando a
		// propriedade candidateRepository e o metodo da propriedade findByUsernameOrEmail

		// Verifica se já existe um candidato com o mesmo username ou email.
		this.candidateRepository.findByUsernameOrEmail(candidate.getUsername(), candidate.getEmail()).ifPresent(
				(user) -> {
					throw new UserFoundException("Usuário já cadastrado");
				});

		// Salva o candidato no banco de dados e retorna o candidato salvo.
		// O método save() retorna a entidade salva, que pode conter informações adicionais, como o ID gerado.
		return this.candidateRepository.save(candidate);
	}
}
