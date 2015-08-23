package br.com.radio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.radio.dto.AlterarSenhaDTO;
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
	public void changeUserPassword( String name, AlterarSenhaDTO alterarSenhaDTO )
	{
		Usuario usuario = usuarioDAO.findByField( "cd_login_usu", name );

		if ( usuario == null )
			throw new RuntimeException( "Usuário não encontrado" );
		else
		{
			// Já foi verificado se os passwords batem no validator durante o request...
			if ( !passwordEncoder.matches( alterarSenhaDTO.getSenha_atual(), usuario.getCd_password_usu() ) )
				throw new RuntimeException( "Senha atual não confere" );

			String novaSenhaEncriptada = passwordEncoder.encode( alterarSenhaDTO.getPassword() );
			
			if ( passwordEncoder.matches(alterarSenhaDTO.getPassword(), usuario.getCd_password_usu() ) )
				throw new RuntimeException( "A nova senha precisa ser diferente da atual" );
			
			usuario.setCd_password_usu( novaSenhaEncriptada );
			
			usuarioDAO.save( usuario );
		}
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
	
		usuario.setCd_password_usu( passwordEncoder.encode( dto.getPassword() ) );
		
		usuario.setFl_ativo_usu( true );
		
		usuarioDAO.save( usuario );
		
		return usuario;
	}

	
	
	public static String encodePasswordWithBCrypt(String plainPassword){
	    return new BCryptPasswordEncoder().encode(plainPassword);
	}
	
	public static void main(String[] aaaa)
	{
		System.out.println(encodePasswordWithBCrypt( "Fernando Pazin" ));
	}
	
}
