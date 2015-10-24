package br.com.radio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.radio.model.Ambiente;
import br.com.radio.model.Bloco;

public interface BlocoRepository extends JpaRepository<Bloco, Long> {
	
	Bloco findByAmbiente( Ambiente ambiente );

}
	