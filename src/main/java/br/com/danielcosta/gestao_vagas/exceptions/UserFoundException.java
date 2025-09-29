package br.com.danielcosta.gestao_vagas.exceptions;

// Exceção personalizada para indicar que um usuário já foi encontrado.
// Extende RuntimeException para ser uma exceção não verificada.
public class UserFoundException extends RuntimeException {

	// Construtor que aceita uma mensagem de erro.
	public UserFoundException(String message) {
		super(message);
	}

}
