package br.com.radio.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.radio.model.AudioOpcional;

public interface AudioOpcionalRepository extends JpaRepository<AudioOpcional, Long> {
	
	Page<AudioOpcional> findByNomeContainingIgnoreCaseAndAtivoTrue( Pageable pageable, String nome );

	Page<AudioOpcional> findByAtivoTrue( Pageable pageable );

	Page<AudioOpcional> findByNomeContainingIgnoreCase( Pageable pageable, String nome );
}
