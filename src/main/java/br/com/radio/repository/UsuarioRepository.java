package br.com.radio.repository;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.radio.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	@Query( value="select count(*) qtd from usuario where cd_email_usu = :email or cd_login_usu = :login", nativeQuery=true )
	public Optional<BigInteger> countByEmailOrLogin( @Param("email") String email, @Param("login") String login);
	
	
	public Usuario findByCdLogin( String cdLogin );
	
	
}
