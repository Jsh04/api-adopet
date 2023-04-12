package br.com.adopet.apiadopet.domain.abrigo;

import br.com.adopet.apiadopet.domain.Endereco;

public record DadosDetalhamentoAbrigo(Long id, String nome, Endereco endereco) {
	public DadosDetalhamentoAbrigo(Abrigo abrigo) {
		this(abrigo.getId(), abrigo.getNome(), abrigo.getEndereco());
	}
}
