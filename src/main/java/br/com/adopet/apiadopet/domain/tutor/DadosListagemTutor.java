package br.com.adopet.apiadopet.domain.tutor;

public record DadosListagemTutor(Long id, String nome, String email) {
	
	public DadosListagemTutor(Tutor tutor) {
		this(tutor.getId(), tutor.getNome(), tutor.getEmail());
	}

}
