package br.com.adopet.apiadopet.dto;

import br.com.adopet.apiadopet.domain.Endereco;
import br.com.adopet.apiadopet.domain.Pet;

public record DadosDetalhamentoPet(Long id, Long idAbrigo, String nome, String descricao, Boolean adotado, String idade, Endereco endereco, String imagem){

	public DadosDetalhamentoPet(Pet pet) {
		this(pet.getId(), pet.getAbrigo().getId(), pet.getNome(), pet.getDescricao(), pet.getAdotado(), pet.getIdade(), pet.getEndereco(), pet.getImg());
	}
}
