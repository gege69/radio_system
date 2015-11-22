package br.com.radio.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.radio.model.Evento;
import br.com.radio.model.EventoHorario;

public interface EventoHorarioRepository extends JpaRepository<EventoHorario, Long> {
	
	Page<EventoHorario> findByEvento( Evento evento, Pageable pageable );
	
	Long deleteByEvento( Evento evento );

}
