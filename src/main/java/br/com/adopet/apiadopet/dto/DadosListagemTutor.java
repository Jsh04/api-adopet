package br.com.adopet.apiadopet.dto;

import br.com.adopet.apiadopet.domain.Tutor;

public record DadosListagemTutor(Long id, String nome, String email) {
	
	public DadosListagemTutor(Tutor tutor) {
		this(tutor.getId(), tutor.getNome(), tutor.getEmail());
	}

}
