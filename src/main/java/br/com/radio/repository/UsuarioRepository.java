package br.com.radio.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.radio.enumeration.UsuarioTipo;
import br.com.radio.model.Ambiente;
import br.com.radio.model.Cliente;
import br.com.radio.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Long countByEmailOrLogin( String email, String login );
	
	@Query(value=" select count(*) from Usuario u where ( u.email = ?1 and u.login = ?2 ) and idUsuario <> ?3 ")
	Long countByEmailOrLoginAndIdUsuarioNot( String email, String login, Long idUsuario );
	
	Long countByLogin( String login );

	Usuario findByAmbiente( Ambiente ambiente );

	Long countByLoginAndIdUsuarioNot( String login, Long idUsuario );
	
	Usuario findByLogin( String login );
	
	Page<Usuario> findByClienteAndUsuarioTipo( Pageable pageable, Cliente cliente, UsuarioTipo usuarioTipo );

	Page<Usuario> findByCliente( Pageable pageable, Cliente cliente );
	
	// Para poder ignorar o próprio usuário
	Page<Usuario> findByClienteAndIdUsuarioNot( Pageable pageable, Cliente cliente, Long idUsuario );
	
}
