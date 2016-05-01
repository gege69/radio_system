package br.com.radio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.radio.model.Usuario;
import br.com.radio.model.UsuarioPerfil;

public interface UsuarioPerfilRepository extends JpaRepository<UsuarioPerfil, Long> {

	Long deleteByUsuario( Usuario usuario );
	
	List<UsuarioPerfil> findByUsuario( Usuario usuario );

}
