package br.com.adopet.apiadopet.domain.tutor;

public record DadosDetalhamentoTutor(String nome, String email ) {
	public DadosDetalhamentoTutor(Tutor tutor) {
		this(tutor.getNome(), tutor.getEmail());
	}

}
