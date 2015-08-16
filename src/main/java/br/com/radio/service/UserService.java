package br.com.radio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.radio.dto.UserDTO;
import br.com.radio.exception.EmailExistsException;
import br.com.radio.model.Usuario;
import br.com.radio.repository.UsuarioDAO;

@Service
public class UserService implements IUserService {

	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Usuario findUserByEmail( String email )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario findUserByEmailOrLogin( String email, String login )
	{
//		Boolean existe usuarioDAO.existsUsuarioByEmailOrLogin( email, login )
		
		
		
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario getUserByID( long id )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void changeUserPassword( Usuario user, String password )
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean checkIfValidOldPassword( Usuario user, String password )
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Usuario registerNewUserAccount( UserDTO dto )
	{
		// Proteger de algum jeito contra brute force com memoization talvez
		
		if ( usuarioDAO.existsUsuarioByEmailOrLogin( dto.getCd_email_usu(), dto.getCd_login_usu() ) )
			throw new EmailExistsException( "Email ou Login já existe." );

		Usuario usuario = new Usuario();
		
		usuario.setCd_email_usu( dto.getCd_email_usu() );
		usuario.setCd_login_usu( dto.getCd_login_usu() );
		usuario.setNm_usuario_usu( dto.getNm_usuario_usu() );		
		usuario.setCd_password_usu( dto.getPassword() );
		
//		usuario.setCd_password_usu( passwordEncoder.encode( dto.getPassword() ) );
		
		usuario.setFl_ativo_usu( true );
		
		usuarioDAO.save( usuario );
		
		return usuario;
	}

	
	
}
