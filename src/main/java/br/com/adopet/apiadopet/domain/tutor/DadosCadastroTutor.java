package br.com.adopet.apiadopet.domain.tutor;

import br.com.adopet.apiadopet.dto.DadosEndereco;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroTutor(
		@NotBlank
		String nome,
		@NotBlank
		String email,
		@NotBlank
		String senha,
		
		DadosEndereco endereco,
		
		String foto,
		
		String telefone) {
}
