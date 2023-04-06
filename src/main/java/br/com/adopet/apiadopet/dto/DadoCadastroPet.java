package br.com.adopet.apiadopet.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DadoCadastroPet(String nome, @JsonAlias({"id_abrigo"})Long abrigoId, String idade, DadosEndereco endereco, String descricao, Boolean adotado, String imagem) {

}
