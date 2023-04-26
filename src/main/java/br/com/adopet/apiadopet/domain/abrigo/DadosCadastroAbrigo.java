package br.com.adopet.apiadopet.domain.abrigo;

import br.com.adopet.apiadopet.dto.DadosEndereco;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroAbrigo(
		@NotBlank
		String nome, 
		
		@NotBlank
		DadosEndereco endereco, 
		
		@NotBlank 
		String email, 
		
		@NotBlank
		String senha) {
	

}
