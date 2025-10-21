package br.com.danielcosta.gestao_vagas.modules.company.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//DTO de resposta para autenticação de empresa com token JWT e tempo de expiração do token inclusos na resposta 
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthCompanyResponseDTO {

	private String access_token;
	private long expires_in;
}
