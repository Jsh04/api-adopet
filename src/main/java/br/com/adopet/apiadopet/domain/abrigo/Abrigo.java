package br.com.adopet.apiadopet.domain.abrigo;

import java.util.ArrayList;
import java.util.List;

import br.com.adopet.apiadopet.domain.Endereco;
import br.com.adopet.apiadopet.domain.Pet;
import br.com.adopet.apiadopet.dto.DadosCadastroAbrigo;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
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
@Table(name = "TB_ABRIGO")
public class Abrigo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	@Embedded
	private Endereco endereco;

	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST,mappedBy = "abrigo")
	private List<Pet> pets = new ArrayList<>();
 
	public Abrigo(DadosCadastroAbrigo dados) {
		this.nome = dados.nome();
		this.endereco = new Endereco(dados.endereco());
	}
}
