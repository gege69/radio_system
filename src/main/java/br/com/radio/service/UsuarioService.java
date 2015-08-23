package br.com.radio.service;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.radio.dto.AlterarSenhaDTO;
import br.com.radio.dto.UserDTO;
import br.com.radio.exception.EmailExistsException;
import br.com.radio.model.Usuario;
import br.com.radio.repository.UsuarioRepository;

@Service
public class UsuarioService implements IUsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
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
		
		Usuario usuario = usuarioRepository.findByCdLogin( name );

		if ( usuario == null )
			throw new RuntimeException( "Usuário não encontrado" );
		else
		{
			// Já foi verificado se os passwords batem no validator durante o request...
			if ( !passwordEncoder.matches( alterarSenhaDTO.getSenha_atual(), usuario.getCdPassword() ) )
				throw new RuntimeException( "Senha atual não confere" );

			String novaSenhaEncriptada = passwordEncoder.encode( alterarSenhaDTO.getPassword() );
			
			if ( passwordEncoder.matches(alterarSenhaDTO.getPassword(), usuario.getCdPassword() ) )
				throw new RuntimeException( "A nova senha precisa ser diferente da atual" );
			
			usuario.setCdPassword( novaSenhaEncriptada );
			
			usuarioRepository.save( usuario );
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
		
		Optional<BigInteger> countUsuarios = usuarioRepository.countByEmailOrLogin( dto.getCd_email_usu(), dto.getCd_login_usu() ); 
		
		if ( countUsuarios.isPresent() && countUsuarios.get().intValue() > 0 )
			throw new EmailExistsException( "Email ou Login já existe." );

		Usuario usuario = new Usuario();
		
		usuario.setCdEmail( dto.getCd_email_usu() );
		usuario.setCdLogin( dto.getCd_login_usu() );
		usuario.setNmUsuario( dto.getNm_usuario_usu() );		
	
		usuario.setCdPassword( passwordEncoder.encode( dto.getPassword() ) );
		
		usuario.setFlAtivo( true );
		
		usuarioRepository.saveAndFlush( usuario );
		
		return usuario;
	}

	
	
	public static String encodePasswordWithBCrypt(String plainPassword){
	    return new BCryptPasswordEncoder().encode(plainPassword);
	}
	
//	public static void main(String[] aaaa)
//	{
//		System.out.println(encodePasswordWithBCrypt( "Fernando Pazin" ));
//	}
	
}
