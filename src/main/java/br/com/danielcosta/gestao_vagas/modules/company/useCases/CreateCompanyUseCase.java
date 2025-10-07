package br.com.danielcosta.gestao_vagas.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.danielcosta.gestao_vagas.exceptions.UserFoundException;
import br.com.danielcosta.gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.danielcosta.gestao_vagas.modules.company.repositories.CompanyRepository;

@Service
public class CreateCompanyUseCase {

	// Injeção de dependência do repositório de empresas. Na prática, o Spring vai
	// fornecer uma instância de CompanyRepository para essa classe.
	@Autowired
	private CompanyRepository companyRepository; // Repositório para operações de banco de dados relacionadas a empresas.

	@Autowired
	private PasswordEncoder passwordEncoder; // Usado para codificar senhas.

	// Método para criar uma nova empresa.
	public CompanyEntity execute(CompanyEntity company) {

		// Verifica se já existe uma empresa com o mesmo nome de usuário ou email.
		this.companyRepository.findByUsernameOrEmail(company.getUsername(), company.getEmail()).ifPresent((user) -> {
			throw new UserFoundException("Empresa já cadastrada");
		});

		var password = passwordEncoder.encode(company.getPassword());
		company.setPassword(password);

		return this.companyRepository.save(company);
	}
}
