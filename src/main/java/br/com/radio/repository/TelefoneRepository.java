package br.com.radio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.radio.model.Cliente;
import br.com.radio.model.Telefone;

public interface TelefoneRepository extends JpaRepository<Telefone, Long> {
	
	Long deleteByCliente( Cliente cliente );

}
