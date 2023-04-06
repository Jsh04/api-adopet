package br.com.adopet.apiadopet.domain;

import br.com.adopet.apiadopet.dto.DadosEndereco;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
	private String logradouro;

    private String bairro;

    private String estado;


    public Endereco(DadosEndereco endereco) {
        this.logradouro = endereco.logradouro();
        this.bairro = endereco.bairro();
        this.estado = endereco.estado();
        
    }
}
