package br.com.radio.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.radio.model.Empresa;
import br.com.radio.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Long countByEmailOrLogin( String email, String login );
	
	Usuario findByLogin( String login );
	
	Page<Usuario> findByEmpresa( Pageable pageable, Empresa empresa );
	
}
