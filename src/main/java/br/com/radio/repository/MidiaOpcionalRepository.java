package br.com.radio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.radio.model.Midia;
import br.com.radio.model.MidiaOpcional;

public interface MidiaOpcionalRepository extends JpaRepository<MidiaOpcional, Long> {
	
	List<MidiaOpcional> findByMidia( Midia midia );

}
