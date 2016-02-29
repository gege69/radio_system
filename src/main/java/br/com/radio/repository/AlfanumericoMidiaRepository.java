package br.com.radio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.radio.model.AlfanumericoMidia;

public interface AlfanumericoMidiaRepository extends JpaRepository<AlfanumericoMidia, Long> {
	
	Long countByAlfanumerico( String alfanumerico );
	
	AlfanumericoMidia findByAlfanumerico( String alfanumerico );

}
