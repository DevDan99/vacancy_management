package br.com.danielcosta.gestao_vagas.modules.company.useCases;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.danielcosta.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import br.com.danielcosta.gestao_vagas.modules.company.dto.AuthCompanyResponseDTO;
import br.com.danielcosta.gestao_vagas.modules.company.repositories.CompanyRepository;

@Service
public class AuthCompanyUseCase {

	@Value("${security.token.secret}")
	private String secretKey;

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private PasswordEncoder passwordEncoder; // Usado para codificar senhas.

	public AuthCompanyResponseDTO execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
		var company = this.companyRepository.findByUsername(authCompanyDTO.getUsername()).orElseThrow(
				() -> {
					throw new UsernameNotFoundException("Username/Password invalid");
				});

		// Verificar a senha (isso normalmente seria feito com um PasswordEncoder)
		var passwordMatches = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

		// se não for igual -> Erro
		if (!passwordMatches) {
			throw new AuthenticationException();
		}

		// se for igual -> Gerar token (JWT)
		// Chave secreta para assinar o token (deve ser segura e mantida em segredo)
		Algorithm algorithm = Algorithm.HMAC256(secretKey);

		var expiresIn = Instant.now().plus(Duration.ofHours(2)); // Define a expiração do token para 2 horas a partir de agora

		var token = JWT.create().withIssuer("Javagas") // Define o emissor do token
				.withSubject(company.getId().toString()) // withSubject recebe o id do usuário autenticado como String
				.withExpiresAt(expiresIn)
				.withClaim("roles", Arrays.asList("COMPANY")) // Adiciona uma reivindicação personalizada "roles" ao token
				.sign(algorithm); // Assina o token com o algoritmo e a chave secreta

		AuthCompanyResponseDTO responseDTO = AuthCompanyResponseDTO.builder()
				.access_token(token)
				.expires_in(expiresIn.toEpochMilli())
				.build();

		return responseDTO;
	}
}
