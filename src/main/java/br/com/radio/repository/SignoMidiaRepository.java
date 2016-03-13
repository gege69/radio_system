package br.com.radio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.radio.enumeration.SignoZodiaco;
import br.com.radio.model.SignoMidia;

public interface SignoMidiaRepository extends JpaRepository<SignoMidia, Long> {

	Long countBySigno( SignoZodiaco signo );
	
	SignoMidia findBySigno( SignoZodiaco signo );
	
	List<SignoMidia> findBySignoIn( List<SignoZodiaco> signos );

}
