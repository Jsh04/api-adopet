package br.com.adopet.apiadopet.domain.tutor;

import java.util.ArrayList;
import java.util.List;

import br.com.adopet.apiadopet.domain.adocao.Adocao;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "TB_TUTOR")
public class Tutor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	private String email;
	
	private String senha;

	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "tutor")
	private List<Adocao> adocoes = new ArrayList<>();
	
	public Tutor(DadosCadastroTutor dados, String senha){
		this.nome = dados.nome().toUpperCase();
		this.email = dados.email();
		this.senha = senha;
	}


}
