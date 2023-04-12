package br.com.adopet.apiadopet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.adopet.apiadopet.domain.adocao.Adocao;

public interface AdocaoRepository extends JpaRepository<Adocao, Long> {

}
