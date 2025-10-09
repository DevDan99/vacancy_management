package br.com.danielcosta.gestao_vagas.providers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Service
public class JWTProvider {

	@Value("${security.token.secret}")
	private String secretKey;

	public String validateToken(String token) {
		// Lógica para validar o token JWT
		token = token.replace("Bearer ", ""); // Remove o prefixo "Bearer " do token

		Algorithm algorithm = Algorithm.HMAC256(secretKey); // Mesma chave secreta usada para assinar o token

		try {
			var subject = JWT.require(algorithm).build().verify(token).getSubject(); // Verifica o token e extrai o assunto (subject)
			// Se o token for inválido ou expirado, uma exceção será lançada
			// Se o token for válido, retorna o assunto (subject), que geralmente é o ID do usuário.
			return subject;

		} catch (JWTVerificationException e) {
			e.printStackTrace();
			return "";
		}
	}
}
