package br.com.radio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.radio.model.Midia;
import br.com.radio.model.MidiaCategoria;

public interface MidiaCategoriaRepository extends JpaRepository<MidiaCategoria, Long> {
	
	Long deleteByMidia( Midia midia );

}
