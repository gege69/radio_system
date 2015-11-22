package br.com.radio.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.radio.model.Ambiente;
import br.com.radio.model.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long> {
	
	Page<Evento> findByAmbiente( Ambiente ambiente, Pageable page );

//	@Query(value="select min(1) existe from programacao where dia = ?2 AND id_ambiente = ?1 and ativo = true and pg_catalog.time(datetime_inicio) > pg_catalog.time(clock_timestamp()) and pg_catalog.time(datetime_fim) <= pg_catalog.time(clock_timestamp()) ", nativeQuery=true)
	
}
	