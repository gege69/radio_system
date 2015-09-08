package br.com.radio.service;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.radio.dto.AlterarSenhaDTO;
import br.com.radio.dto.UserDTO;
import br.com.radio.exception.EmailExistsException;
import br.com.radio.model.Usuario;
import br.com.radio.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public void changeUserPassword( String name, AlterarSenhaDTO alterarSenhaDTO )
	{
		Usuario usuario = usuarioRepository.findByLogin( name );

		if ( usuario == null )
			throw new RuntimeException( "Usuário não encontrado" );
		else
		{
			// Já foi verificado se os passwords batem no validator durante o request...
			if ( !passwordEncoder.matches( alterarSenhaDTO.getSenha_atual(), usuario.getPassword() ) )
				throw new RuntimeException( "Senha atual não confere" );

			String novaSenhaEncriptada = passwordEncoder.encode( alterarSenhaDTO.getPassword() );
			
			if ( passwordEncoder.matches(alterarSenhaDTO.getPassword(), usuario.getPassword() ) )
				throw new RuntimeException( "A nova senha precisa ser diferente da atual" );
			
			usuario.setPassword( novaSenhaEncriptada );
			
			usuarioRepository.save( usuario );
		}
	}

	public Usuario registerNewUserAccount( UserDTO dto )
	{
		// Proteger de algum jeito contra brute force com memoization talvez
		
		Long countUsuarios = usuarioRepository.countByEmailOrLogin( dto.getCdEmail(), dto.getCdLogin() ); 
		
		if ( countUsuarios != null && countUsuarios > 0 )
			throw new EmailExistsException( "Email ou Login já existe." );

		Usuario usuario = new Usuario();
		
		usuario.setEmail( dto.getCdEmail() );
		usuario.setLogin( dto.getCdLogin() );
		usuario.setNome( dto.getNmUsuario() );		
	
		usuario.setPassword( passwordEncoder.encode( dto.getPassword() ) );
		
		usuario.setAtivo( true );
		
		usuarioRepository.saveAndFlush( usuario );
		
		return usuario;
	}

	
	public Usuario getUserByPrincipal( Principal principal )
	{
		Usuario usuario = usuarioRepository.findByLogin( principal.getName() );
		
		return usuario;
	}

	
}
