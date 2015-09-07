package br.com.radio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.radio.model.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

	Empresa findByCodigo( String codigo );
	
}
