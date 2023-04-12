package br.com.adopet.apiadopet.domain.adocao;

import java.time.LocalDate;

public record DadosDetalhamentoAdocao(Long id, Long animalId, Long tutorId, LocalDate data) {

	public DadosDetalhamentoAdocao(Adocao adocao) {
		this(adocao.getId(), adocao.getPet().getId(), adocao.getTutor().getId(), LocalDate.now());
	}

}
