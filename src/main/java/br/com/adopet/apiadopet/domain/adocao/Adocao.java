package br.com.adopet.apiadopet.domain.adocao;

import br.com.adopet.apiadopet.domain.pet.Pet;
import br.com.adopet.apiadopet.domain.tutor.Tutor;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
@Table(name = "TB_ADOCAO")
public class Adocao {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	private String telefone;
	
	private String mensagem;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PET_ID")
	private Pet pet;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TUTOR_ID")
	private Tutor tutor;
	
	public Adocao(DadosCadastroAdocao dados) {
		this.nome = dados.nome();
		this.telefone = dados.telefone();
		this.mensagem = dados.mensagem();
	}

}
