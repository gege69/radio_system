package br.com.radio.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.radio.model.TipoTaxa;

public interface TipoTaxaRepository extends JpaRepository<TipoTaxa, Long> {

	Page<TipoTaxa> findByAtivoTrue( Pageable pageable );

	Page<TipoTaxa> findByAtivoTrueAndDescricaoContainingIgnoreCase( Pageable pageable, String descricao );
}
