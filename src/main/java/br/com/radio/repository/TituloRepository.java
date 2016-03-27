package br.com.radio.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.radio.model.Cliente;
import br.com.radio.model.Titulo;

public interface TituloRepository extends JpaRepository<Titulo, Long> {
	
	List<Titulo> findByCliente( Cliente cliente );
	
	Page<Titulo> findByCliente( Pageable pageable, Cliente cliente );

}
