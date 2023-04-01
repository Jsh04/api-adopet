package br.com.adopet.apiadopet.dto;

import br.com.adopet.apiadopet.domain.Tutor;

public record DadosDetalhamentoTutor(String nome, String email ) {
	public DadosDetalhamentoTutor(Tutor tutor) {
		this(tutor.getNome(), tutor.getEmail());
	}
}
