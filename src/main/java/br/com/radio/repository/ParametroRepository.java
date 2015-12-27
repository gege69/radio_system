package br.com.radio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.radio.model.Cliente;
import br.com.radio.model.Parametro;

public interface ParametroRepository extends JpaRepository<Parametro, Long> {

	Parametro findByCodigoAndCliente( String codigo, Cliente cliente );
	
	Parametro findByCodigo( String codigo );
	
}
