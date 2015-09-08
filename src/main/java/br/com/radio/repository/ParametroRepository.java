package br.com.radio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.radio.model.Empresa;
import br.com.radio.model.Parametro;

public interface ParametroRepository extends JpaRepository<Parametro, Long> {

	Parametro findByCodigoAndEmpresa( String codigo, Empresa empresa );
	
}
