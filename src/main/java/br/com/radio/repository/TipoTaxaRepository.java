package br.com.radio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.radio.model.TipoTaxa;

public interface TipoTaxaRepository extends JpaRepository<TipoTaxa, Long> {

}
