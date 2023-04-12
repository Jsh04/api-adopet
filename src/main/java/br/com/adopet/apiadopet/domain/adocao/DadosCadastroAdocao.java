package br.com.adopet.apiadopet.domain.adocao;

import com.fasterxml.jackson.annotation.JsonAlias;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroAdocao(
		@NotBlank
		String nome, 
		@NotBlank
		String telefone,
		@NotBlank
		@JsonAlias({"nome_animal"})
		String nomeAnimal,
		String mensagem) {

}
