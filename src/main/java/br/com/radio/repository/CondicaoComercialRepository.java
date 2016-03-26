package br.com.radio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.radio.model.Cliente;
import br.com.radio.model.CondicaoComercial;

public interface CondicaoComercialRepository extends JpaRepository<CondicaoComercial, Long> {
	
	List<CondicaoComercial> findByCliente( Cliente cliente );

}
