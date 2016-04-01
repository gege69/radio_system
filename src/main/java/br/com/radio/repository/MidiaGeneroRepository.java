package br.com.radio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.radio.model.Genero;
import br.com.radio.model.Midia;
import br.com.radio.model.MidiaGenero;

public interface MidiaGeneroRepository extends JpaRepository<MidiaGenero, Long> {

	Long deleteByMidia( Midia midia );
	
	Long countByMidia( Midia midia );

	List<MidiaGenero> findByGenero( Genero genero );
	
}
