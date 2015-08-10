package br.com.radio.repository.impl;

import org.springframework.stereotype.Repository;

import br.com.radio.model.Usuario;
import br.com.radio.repository.UsuarioDAO;

@Repository("usuarioDAO")
public class UsuarioDAOImpl extends AbstractDAO<Usuario, Long> implements UsuarioDAO {

}
