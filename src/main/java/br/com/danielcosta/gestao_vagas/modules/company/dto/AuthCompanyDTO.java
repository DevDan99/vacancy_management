package br.com.danielcosta.gestao_vagas.modules.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

// DTO = Data Transfer Object
// Classe simples usada para transferir dados entre processos, como entre o cliente e o servidor em uma aplicação web.
// Aqui, essa classe é usada para encapsular os dados necessários para autenticar uma empresa (username e password).

@Data
@AllArgsConstructor // Gera construtor com todos os argumentos da classe.
public class AuthCompanyDTO {

	private String username;
	private String password;
}
