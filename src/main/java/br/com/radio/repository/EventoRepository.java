package br.com.radio.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.radio.model.Ambiente;
import br.com.radio.model.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long> {
	
	Page<Evento> findByAmbiente( Ambiente ambiente, Pageable page );
	
	List<Evento> findByAmbiente( Ambiente ambiente );

	Long deleteByAmbiente( Ambiente ambiente );
	
	List<Evento> findByAmbienteAndAtivoTrue( Ambiente ambiente );
	
	Evento findByIdEventoAndAmbiente( Long idEvento, Ambiente ambiente );

	@Query(value=
	" select e.* "+
	" from evento e "+
	" where e.datafim >= date_trunc('day', clock_timestamp()) and e.datainicio <= date_trunc('day', clock_timestamp()) "+
	" and e.id_ambiente = ?1 ", nativeQuery= true)
	List<Evento> findByHorarioAndIdAmbiente( Long idAmbiente );
	
}
	