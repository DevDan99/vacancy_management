package br.com.danielcosta.gestao_vagas.providers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

@Service
public class JWTProvider {

	@Value("${security.token.secret}")
	private String secretKey;

	public DecodedJWT validateToken(String token) {
		// Lógica para validar o token JWT
		token = token.replace("Bearer ", ""); // Remove o prefixo "Bearer " do token

		Algorithm algorithm = Algorithm.HMAC256(secretKey); // Mesma chave secreta usada para assinar o token

		try {
			// Verifica o token e extrai as informações
			var tokenDecoded = JWT.require(algorithm)
					.build()
					.verify(token); // Verifica o token e extrai o assunto (subject)

			// Retorna o token decodificado se for válido
			return tokenDecoded;

		} catch (JWTVerificationException e) {
			e.printStackTrace();
			return null;
		}
	}
}
