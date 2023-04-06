package br.com.adopet.apiadopet.domain;

import java.util.Optional;

import org.hibernate.mapping.Join;

import br.com.adopet.apiadopet.domain.abrigo.Abrigo;
import br.com.adopet.apiadopet.dto.DadoCadastroPet;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "TB_PET")
public class Pet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	@Column(name = "IMAGEM")
	private String img;
	
	@Embedded
	private Endereco endereco;
	
	@Column(name = "SOBRE")
	private String descricao;
	
	private Boolean adotado;
	
	private String idade;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_ABRIGO")
	private Abrigo abrigo;
	
	public Pet(DadoCadastroPet dados, Abrigo abrigo) {
		this.nome = dados.nome();
		this.img = dados.imagem();
		this.endereco = new Endereco(dados.endereco());
		this.descricao = dados.descricao();
		this.adotado = dados.adotado();
		this.idade = dados.idade();
		this.abrigo = abrigo;
	}

	
	
}