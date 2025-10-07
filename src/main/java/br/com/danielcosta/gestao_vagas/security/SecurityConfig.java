package br.com.danielcosta.gestao_vagas.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // Indica que essa classe é uma classe de configuração do Spring. Assim que inicializar a aplicação, o Spring vai
					// carregar essa classe e aplicar as configurações de segurança que forem definidas aqui.
public class SecurityConfig {

	// A anotação @Bean indica que o método retorna um bean gerenciado pelo Spring, que neste caso é a cadeia de filtros de
	// segurança.
	// Na pratica ele sobreescreve a configuração padrão de segurança do Spring Boot.
	@Bean
	// Configurações de segurança HTTP. Aqui, estamos desabilitando a proteção CSRF (Cross-Site Request Forgery) para
	// simplificar o desenvolvimento. Em um ambiente de produção, é importante avaliar cuidadosamente as necessidades de
	// segurança e configurar o CSRF de acordo.
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()) // Desabilita a proteção CSRF
				.authorizeHttpRequests(auth -> {
					auth.requestMatchers("/candidate/").permitAll() // Permite acesso sem autenticação ao endpoint /candidate/
							.requestMatchers("/company/").permitAll() // Permite acesso sem autenticação ao endpoint /company
							.requestMatchers("/auth/company").permitAll();
					auth.anyRequest().authenticated(); // Exige autenticação para qualquer outra requisição
				});
		return http.build(); // Constrói e retorna a cadeia de filtros de segurança configurada.
	}

	// Define um bean para o PasswordEncoder, que é usado para codificar senhas. Aqui, estamos usando o
	// BCryptPasswordEncoder,
	// que é uma implementação segura e amplamente utilizada para hashing de senhas.
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
