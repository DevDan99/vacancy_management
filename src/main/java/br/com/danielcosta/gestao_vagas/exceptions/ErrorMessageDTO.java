package br.com.danielcosta.gestao_vagas.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorMessageDTO {
    private String message;
    private String field;
}
