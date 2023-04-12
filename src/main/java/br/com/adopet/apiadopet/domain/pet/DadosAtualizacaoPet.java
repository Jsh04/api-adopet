package br.com.adopet.apiadopet.domain.pet;

import com.fasterxml.jackson.annotation.JsonAlias;

import br.com.adopet.apiadopet.dto.DadosEndereco;

public record DadosAtualizacaoPet(String nome, @JsonAlias({"id_abrigo"})Long abrigoId, String idade, DadosEndereco endereco, String descricao, Boolean adotado, String imagem) {

}
