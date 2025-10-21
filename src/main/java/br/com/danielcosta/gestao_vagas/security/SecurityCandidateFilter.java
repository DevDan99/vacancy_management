package br.com.danielcosta.gestao_vagas.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.danielcosta.gestao_vagas.providers.JWTCandidateProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityCandidateFilter extends OncePerRequestFilter {

	@Autowired
	private JWTCandidateProvider jwtCandidateProvider;

	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {

		SecurityContextHolder.getContext().setAuthentication(null);
		String header = request.getHeader("Authorization");

		if (request.getRequestURI().startsWith("/candidate")) {
			if (header != null) {
				var token = this.jwtCandidateProvider.validateToken(header);

				if (token == null) {
					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					return;
				}

				// Armazena o ID do candidato na requisição para uso posterior
				request.setAttribute("candidate_id", token.getSubject());
			}
		}

		filterChain.doFilter(request, response);

	}
}
