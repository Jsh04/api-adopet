package br.com.adopet.apiadopet.domain;

import br.com.adopet.apiadopet.dto.DadosCadastroTutor;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	
	public Tutor(DadosCadastroTutor dados, String senha){
		this.nome = dados.nome();
		this.email = dados.email();
		this.senha = senha;
	}


}
