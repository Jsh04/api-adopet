package br.com.adopet.apiadopet.dto;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroTutor(
		@NotBlank
		String nome,
		@NotBlank
		String email,
		@NotBlank
		String senha) {
}
