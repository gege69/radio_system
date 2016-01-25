package br.com.radio.service;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.radio.dto.AlterarSenhaDTO;
import br.com.radio.dto.UserDTO;
import br.com.radio.dto.UsuarioAmbienteDTO;
import br.com.radio.dto.UsuarioGerenciadorDTO;
import br.com.radio.enumeration.UsuarioTipo;
import br.com.radio.exception.EmailExistsException;
import br.com.radio.model.Ambiente;
import br.com.radio.model.Cliente;
import br.com.radio.model.Perfil;
import br.com.radio.model.Usuario;
import br.com.radio.model.UsuarioPerfil;
import br.com.radio.repository.AmbienteRepository;
import br.com.radio.repository.ClienteRepository;
import br.com.radio.repository.PerfilRepository;
import br.com.radio.repository.UsuarioPerfilRepository;
import br.com.radio.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Autowired
	private UsuarioPerfilRepository usuarioPerfilRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ClienteRepository clienteRepo;
	
	@Autowired
	private PerfilRepository perfilRepo;
	
	@Autowired
	private AmbienteRepository ambienteRepo;
	
	
	public void changeUserPassword( String name, AlterarSenhaDTO alterarSenhaDTO )
	{
		Usuario usuario = usuarioRepo.findByLogin( name );

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
			
			usuarioRepo.save( usuario );
		}
	}

	
	@Transactional
	public Usuario registraNovoUsuarioGerenciador( UserDTO dto )
	{
		// Proteger de algum jeito contra brute force com memoization talvez
		
		Long countUsuarios = usuarioRepo.countByEmailOrLogin( dto.getCdEmail(), dto.getCdLogin() ); 
		
		if ( countUsuarios != null && countUsuarios > 0 )
			throw new EmailExistsException( "Email ou Login já existe." );

		if ( StringUtils.isBlank( dto.getCdEmail() ) )
			throw new RuntimeException( "Email é obrigatório" );
		
		if ( StringUtils.isBlank( dto.getPassword() ) )
			throw new RuntimeException( "Senha está em branco." );
		
		Usuario usuario = new Usuario();
		
		usuario.setEmail( dto.getCdEmail() );
		usuario.setLogin( dto.getCdLogin() );
		usuario.setNome( dto.getNmUsuario() );		
	
		usuario.setPassword( passwordEncoder.encode( dto.getPassword() ) );
		
		usuario.setAtivo( true );
		usuario.setUsuarioTipo( UsuarioTipo.GERENCIADOR );
		
		Cliente cliente = new Cliente();
		
		cliente.setAtivo( true );
		cliente.setCnpj( "46511666000105" );
		cliente.setRazaosocial( "teste" );
		cliente.setCodigo( "teste" );
		cliente.setDominio( "teste" );
		cliente.setDataCriacao( new Date() );
		cliente.setNomefantasia( "teste" );
		
		clienteRepo.save( cliente );
		

		usuario.setCliente( cliente );
		
		usuarioRepo.saveAndFlush( usuario );
		
		UsuarioPerfil usuPerfil = new UsuarioPerfil();
		
		usuPerfil.setPerfil( perfilRepo.findByNome( "DESENVOLVEDOR" ) );
		usuPerfil.setUsuario( usuario );
		
		usuarioPerfilRepo.save( usuPerfil );
		
		return usuario;
	}
	
	
	
	@Transactional
	public Usuario saveUsuarioGerenciador( UsuarioGerenciadorDTO usuarioGerenciadorDTO )
	{
		Cliente cliente = clienteRepo.findOne( 1l );
		
		Long countUsuarios = 0l;
		
		Usuario usuarioDTO = usuarioGerenciadorDTO.getUsuario();
		
		if ( usuarioDTO.getIdUsuario() != null && usuarioDTO.getIdUsuario() > 0 )
			countUsuarios = usuarioRepo.countByEmailOrLoginAndIdUsuarioNot( usuarioDTO.getEmail() , usuarioDTO.getLogin(), usuarioDTO.getIdUsuario() );		
		else
			countUsuarios = usuarioRepo.countByEmailOrLogin( usuarioDTO.getEmail() , usuarioDTO.getLogin() ); 
		
		if ( countUsuarios != null && countUsuarios > 0 )
			throw new EmailExistsException( "Email ou Login já existe." );

		if ( StringUtils.isBlank( usuarioDTO.getEmail() ) )
			throw new RuntimeException( "Email é obrigatório" );

		if ( StringUtils.isBlank( usuarioDTO.getNome() ) )
			throw new RuntimeException( "Nome é obrigatório" );

		Usuario usuario = null;
		
		if ( usuarioDTO.getIdUsuario() != null && usuarioDTO.getIdUsuario() > 0 )
			usuario = usuarioRepo.findOne( usuarioDTO.getIdUsuario() );
		else
		{
			usuario = new Usuario();
			usuario.setAtivo( true );
			usuario.setUsuarioTipo( UsuarioTipo.GERENCIADOR );
		}
		
		usuario.setNome( usuarioDTO.getNome() );
		usuario.setLogin( usuarioDTO.getLogin() );
		usuario.setEmail( usuarioDTO.getEmail() );
		
		if ( usuarioDTO.getCliente() != null )
			usuario.setCliente( usuarioDTO.getCliente() );
		else
			usuario.setCliente( cliente );

		if ( usuarioDTO.getAtivo() == null )
			usuarioDTO.setAtivo( false );
		
		usuario.setAtivo( usuarioDTO.getAtivo() );
		
		if ( StringUtils.isNotBlank( usuarioDTO.getPassword() ) )
			usuario.setPassword( passwordEncoder.encode( usuarioDTO.getPassword() ) );
		
		usuario.setDataAlteracao( new Date() );

		usuarioRepo.save( usuario );
		
		if ( usuario != null && usuario.getIdUsuario() > 0 )
		{
			List<Perfil> perfisDTO = usuarioGerenciadorDTO.getPerfis();
			
			usuarioPerfilRepo.deleteByUsuario( usuario );
			
			for ( Perfil perf : perfisDTO )
			{
				UsuarioPerfil usuPerf = new UsuarioPerfil();
				
				usuPerf.setUsuario( usuario );
				usuPerf.setPerfil( perf );
				
				usuarioPerfilRepo.save( usuPerf );
			}
		}
		
		return usuario;
	}
	
	
	
	public Usuario registerNewUserPlayer( Ambiente ambiente )
	{
		Usuario usuario = new Usuario();
		usuario.setLogin( ambiente.getLogin() );
		usuario.setPassword( ambiente.getPassword() );
		usuario.setCliente( ambiente.getCliente() );
		usuario.setNome( ambiente.getNome() );
		
		Long countUsuarios = usuarioRepo.countByLogin( usuario.getLogin() ); 
		
		if ( countUsuarios != null && countUsuarios > 0 )
			throw new EmailExistsException( "Login já existe." );

		usuario.setPassword( passwordEncoder.encode( usuario.getPassword() ) );
		usuario.setAtivo( true );
		usuario.setUsuarioTipo( UsuarioTipo.PLAYER );
		usuario.setAmbiente( ambiente );		
		
		return usuario;
	}
	
	
	public Usuario save( Usuario usuario )
	{
		usuarioRepo.save( usuario );
		
		return usuario;
	}

	
	public Usuario getUserByPrincipal( Principal principal )
	{
		Usuario usuario = usuarioRepo.findByLogin( principal.getName() );
		
		return usuario;
	}

	
	public Long countByLogin( String login )
	{
		Long result = usuarioRepo.countByLogin( login ); 
		
		if ( result == null )
			result = 0l;
		
		return result;
	}
	
	
	public UsuarioAmbienteDTO getUsuarioAmbienteByPrincipal( Long idAmbiente, Principal principal )
	{
		Usuario usuario = getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente() == null )
			throw new RuntimeException( "Usuário não encontrado" );

		
		
		
//		if ( usuario.getUsuarioTipo().equals( UsuarioTipo.GERENCIADOR ) )
//			throw new RuntimeException( "Usuário não é do tipo Ambiente" );

		Ambiente ambiente = ambienteRepo.findByLogin( usuario.getLogin() );
			
		if ( ambiente == null )
			throw new RuntimeException( "Ambiente não encontrado." );
		
		if ( !ambiente.getCliente().getIdCliente().equals( usuario.getCliente().getIdCliente() ))
			throw new RuntimeException( "Tentativa de autenticar em um player que não percence ao seu login" );
		
		UsuarioAmbienteDTO dto = new UsuarioAmbienteDTO( usuario, ambiente );
		
		return dto;
	}
	
	
}
