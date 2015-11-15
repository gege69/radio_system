package br.com.radio.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.radio.model.Ambiente;
import br.com.radio.model.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long> {
	
	Page<Evento> findByAmbiente( Ambiente ambiente, Pageable page );

}
	