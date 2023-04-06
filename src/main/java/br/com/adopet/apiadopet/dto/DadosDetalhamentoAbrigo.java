package br.com.adopet.apiadopet.dto;

import br.com.adopet.apiadopet.domain.Endereco;
import br.com.adopet.apiadopet.domain.abrigo.Abrigo;

public record DadosDetalhamentoAbrigo(Long id, String nome, Endereco endereco) {
	public DadosDetalhamentoAbrigo(Abrigo abrigo) {
		this(abrigo.getId(), abrigo.getNome(), abrigo.getEndereco());
	}
}
