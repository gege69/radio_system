package br.com.radio.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.radio.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	Cliente findByCodigo( String codigo );
	
	Long countByAtivo( Boolean ativo );
	
	Cliente findByCnpj( String cnpj );
	
	Page<Cliente> findByRazaosocialContainingIgnoreCaseOrNomefantasiaContainingIgnoreCaseOrCnpjContaining( Pageable pageable, String razaosocial, String nomefantasia, String cnpj );
	
}
