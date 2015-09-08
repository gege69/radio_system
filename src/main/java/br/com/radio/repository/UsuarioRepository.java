package br.com.radio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.radio.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Long countByEmailOrLogin( String email, String login );
	
	Usuario findByLogin( String login );
	
}
