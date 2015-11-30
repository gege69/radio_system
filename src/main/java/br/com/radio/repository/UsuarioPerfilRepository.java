package br.com.radio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.radio.model.Usuario;
import br.com.radio.model.UsuarioPerfil;

public interface UsuarioPerfilRepository extends JpaRepository<UsuarioPerfil, Long> {


	Long deleteByUsuario( Usuario usuario );
	

}
