package br.com.radio.repository;

import br.com.radio.model.Usuario;

public interface UsuarioDAO extends DAO<Usuario, Long> {

	public Boolean existsUsuarioByEmailOrLogin( String email, String login );
	
}
