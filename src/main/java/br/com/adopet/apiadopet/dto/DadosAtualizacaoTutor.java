package br.com.adopet.apiadopet.dto;

import jakarta.validation.constraints.NotBlank;

public record DadosAtualizacaoTutor(
		@NotBlank
		String nome, 
		String email, 
		String senha) {
	
}
