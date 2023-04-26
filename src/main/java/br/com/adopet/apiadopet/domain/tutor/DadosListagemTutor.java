package br.com.adopet.apiadopet.domain.tutor;

import br.com.adopet.apiadopet.domain.Endereco;

public record DadosListagemTutor(Long id, String nome, String email, Endereco endereco, String foto,String telefone) {
	
	public DadosListagemTutor(Tutor tutor) {
		this(tutor.getId(), tutor.getNome(), tutor.getEmail(), tutor.getEndereco(), tutor.getFoto(), tutor.getTelefone());
	}

}
