package br.com.adopet.apiadopet.domain.abrigo;

import br.com.adopet.apiadopet.dto.DadosEndereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record DadosCadastroAbrigo(
		@NotBlank
		String nome, 
		
		@NotEmpty
		DadosEndereco endereco, 
		
		@NotBlank 
		String email, 
		
		@NotBlank
		String senha) {
	

}
