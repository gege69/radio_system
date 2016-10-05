package br.com.radio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.radio.model.AcessoUsuario;
import br.com.radio.model.Usuario;

public interface AcessoUsuarioRepository extends JpaRepository<AcessoUsuario, Long> {
	
	List<AcessoUsuario> findBySessionIdAndUsuarioAndDataLogoutIsNull( String sessionId, Usuario usuario );

}
