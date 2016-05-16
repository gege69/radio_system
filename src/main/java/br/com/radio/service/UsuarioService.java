package br.com.radio.service;

import java.security.Principal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import br.com.radio.dto.AlterarSenhaDTO;
import br.com.radio.dto.PerfilPermissaoDTO;
import br.com.radio.dto.RegistroDTO;
import br.com.radio.dto.UsuarioAmbienteDTO;
import br.com.radio.dto.UsuarioGerenciadorDTO;
import br.com.radio.enumeration.UsuarioTipo;
import br.com.radio.exception.EmailExistsException;
import br.com.radio.model.Ambiente;
import br.com.radio.model.Cliente;
import br.com.radio.model.Perfil;
import br.com.radio.model.PerfilPermissao;
import br.com.radio.model.Permissao;
import br.com.radio.model.Usuario;
import br.com.radio.model.UsuarioPerfil;
import br.com.radio.model.UsuarioPermissao;
import br.com.radio.repository.AmbienteRepository;
import br.com.radio.repository.ClienteRepository;
import br.com.radio.repository.PerfilPermissaoRepository;
import br.com.radio.repository.PerfilRepository;
import br.com.radio.repository.PermissaoRepository;
import br.com.radio.repository.UsuarioPerfilRepository;
import br.com.radio.repository.UsuarioPermissaoRepository;
import br.com.radio.repository.UsuarioRepository;
import br.com.radio.util.UtilsStr;

import com.nulabinc.zxcvbn.Strength;
import com.nulabinc.zxcvbn.Zxcvbn;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Autowired
	private UsuarioPerfilRepository usuarioPerfilRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ClienteRepository clienteRepo;

	@Autowired
	private PerfilRepository perfilRepo;
	
	@Autowired
	private AmbienteRepository ambienteRepo;
	
	@Autowired
	private PermissaoRepository permissaoRepo;
	
	@Autowired
	private PerfilPermissaoRepository perfilPermissaoRepo;
	
	@Autowired
	private UsuarioPermissaoRepository usuarioPermissaoRepo;
	
	// Essas constantes determinam a força mínima para que seja possível aceitar. O password tem que ter força maior que esse valor.
	private final int FORCA_MIN_PLAYER = 0;
	private final int FORCA_MIN_GERENCIADOR = 0;
	private final int FORCA_MIN_ADM = 3;
		


	
	public void changeUserPassword( String name, AlterarSenhaDTO alterarSenhaDTO )
	{
		Usuario usuario = usuarioRepo.findByLogin( name );

		if ( usuario == null )
			throw new RuntimeException( "Usuário não encontrado" );
		else
		{
			validaForcaSenha( alterarSenhaDTO.getPassword(), usuario );

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
	public Usuario registraNovoUsuarioGerenciador( RegistroDTO dto )
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
		usuario.setUsuarioTipo( UsuarioTipo.GERENCIADOR );
	
		validaForcaSenha( dto.getPassword(), usuario );

		usuario.setPassword( passwordEncoder.encode( dto.getPassword() ) );
		
		usuario.setAtivo( true );
		
		Cliente cliente = new Cliente();
		
		cliente.setAtivo( true );
		cliente.setCnpj( dto.getCdCNPJCPF() );
		cliente.setRazaosocial( dto.getNmEmpresa() );
		cliente.setCodigo( StringUtils.remove( UtilsStr.unaccent( StringUtils.lowerCase( dto.getNmEmpresa() ) ), " " ) );
		cliente.setDominio( null );
		cliente.setDataCriacao( new Date() );
		cliente.setEstabelecimento( dto.getEstabelecimento() );
		
		clienteService.saveCliente( cliente );
		
		usuario.setCliente( cliente );
		
		usuarioRepo.saveAndFlush( usuario );
		
		UsuarioPerfil usuPerfil = new UsuarioPerfil();
		
		usuPerfil.setPerfil( perfilRepo.findByNomeAndComumTrue( "GERENTE" ) );
		usuPerfil.setUsuario( usuario );
		
		usuarioPerfilRepo.save( usuPerfil );
		
		return usuario;
	}
	
	
	
	@Transactional
	public Usuario saveUsuarioGerenciador( UsuarioGerenciadorDTO usuarioGerenciadorDTO, Cliente cliente )
	{
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

		List<Perfil> perfisDTO = usuarioGerenciadorDTO.getPerfis();

		usuario.setPerfis( perfisDTO );

		validaForcaSenha( usuarioDTO.getPassword(), usuario );
		
		if ( StringUtils.isNotBlank( usuarioDTO.getPassword() ) )
			usuario.setPassword( passwordEncoder.encode( usuarioDTO.getPassword() ) );
		
		usuario.setDataAlteracao( new Date() );

		usuarioRepo.save( usuario );
		
		if ( usuario != null && usuario.getIdUsuario() > 0 )
		{
			perfisDTO = usuarioGerenciadorDTO.getPerfis();
			
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
	

	
	
	public Usuario saveUsuarioAmbientePlayer( Ambiente ambiente, String password )
	{
		Usuario usuario = usuarioRepo.findByAmbiente( ambiente );


		if ( usuario != null ){
			
			if ( usuarioRepo.countByLoginAndIdUsuarioNot( ambiente.getLogin(), usuario.getIdUsuario() ) > 0 )
				throw new RuntimeException( "O login informado não está disponível por favor insira outro." );

			validaForcaSenha( password, usuario );

			usuario.setPassword(  passwordEncoder.encode( password )  );
			usuario.setLogin( ambiente.getLogin() );

			this.save( usuario );
		}
		else
		{
			usuario = new Usuario();
			usuario.setLogin( ambiente.getLogin() );
			usuario.setPassword( password );
			usuario.setCliente( ambiente.getCliente() );
			usuario.setNome( ambiente.getNome() );
			usuario.setUsuarioTipo( UsuarioTipo.PLAYER );
			
			validaForcaSenha( password, usuario );

			Long countUsuarios = usuarioRepo.countByLogin( usuario.getLogin() ); 
			
			if ( countUsuarios != null && countUsuarios > 0 )
				throw new EmailExistsException( "Login já existe." );
			
			usuario.setPassword( passwordEncoder.encode( usuario.getPassword() ) );
			usuario.setAtivo( true );
			
			usuario.setAmbiente( ambiente );
			
			this.save( usuario );
			
			Permissao permissao = permissaoRepo.findByCodigo( "PLAYER" );
			
			UsuarioPermissao usuarioPermissao = new UsuarioPermissao( usuario, permissao );
			
			usuarioPermissaoRepo.save( usuarioPermissao );
		}

		return usuario;
	}


	public void validaForcaSenha( String password, Usuario usuario ){
		
		Zxcvbn zxcvbn = new Zxcvbn();
		Strength strength = zxcvbn.measure( password, Arrays.asList( "eterion", "rdcenter", "radio", "ambiente", "som", "123456" ) );
		
		List<Perfil> perfis = usuario.getPerfis();
		
		int forcaMinima = FORCA_MIN_PLAYER;
		String mensagem = "";

		if ( perfis != null && CollectionUtils.containsAny( perfis, Perfil.DONOS ) ){
			forcaMinima = FORCA_MIN_ADM;
			mensagem = "A força da senha dos administradores do sistema precisa ser forte (indicador verde)";
		}
		else if ( usuario.getUsuarioTipo() != null && usuario.getUsuarioTipo().equals( UsuarioTipo.GERENCIADOR ) )
			forcaMinima = FORCA_MIN_GERENCIADOR;
		else 
			forcaMinima = FORCA_MIN_PLAYER;
		
		if ( strength.getScore() < forcaMinima )
			throw new RuntimeException("Senha é muito fraca. "+ mensagem);
	}
	
	
	public Usuario save( Usuario usuario )
	{
		usuarioRepo.saveAndFlush( usuario );
		
		return usuario;
	}

	
	public Usuario findOne( Long idUsuario ){
		Usuario usuario = usuarioRepo.findOne( idUsuario );
		return usuario;
	}

	
	public Usuario getUserByPrincipal( Principal principal )
	{
		Usuario usuario = usuarioRepo.findByLogin( principal.getName() );
		return usuario;
	}
	

	public Usuario findLogin( String login )
	{
		Usuario usuario = usuarioRepo.findByLogin( login );
		return usuario;
	}


	public Usuario getUsuarioMaisRelevantePorCliente( Cliente cliente ){
		
		return null;
	}


	
	
	public UsuarioAmbienteDTO getUsuarioAmbienteByPrincipal( Long idAmbiente, Principal principal )
	{
		Usuario usuario = getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente() == null )
			throw new RuntimeException( "Usuário não encontrado" );

		Ambiente ambiente = null;
		
		boolean isGerenciador = usuario.getUsuarioTipo().equals( UsuarioTipo.GERENCIADOR );
		boolean isPlayer = usuario.getUsuarioTipo().equals( UsuarioTipo.PLAYER );

		// Se está logado como gerenciado tem que usar as urls de simulação
		if ( isGerenciador  && ( idAmbiente == null || idAmbiente < 0 ) )
			throw new RuntimeException( "Ambiente não informado" );
		
		if ( idAmbiente == null && isPlayer )
			ambiente = ambienteRepo.findByLogin( usuario.getLogin() );

		if ( ambiente == null && idAmbiente != null )
			ambiente =ambienteRepo.findOne( idAmbiente );
		
		if ( ambiente == null )
			throw new RuntimeException( "Ambiente não encontrado." );

		if ( isPlayer && ( usuario.getAmbiente() == null || !ambiente.equals( usuario.getAmbiente() ) ) )
			throw new RuntimeException( "Tentativa de autenticar em um player que não pertence ao seu login" );
		
		if ( !ambiente.getCliente().getIdCliente().equals( usuario.getCliente().getIdCliente() ))
			throw new RuntimeException( "Tentativa de autenticar em um player que não pertence ao seu login" );
		
		UsuarioAmbienteDTO dto = new UsuarioAmbienteDTO( usuario, ambiente );
		
		return dto;
	}
	
	
	
	/**
	 * Se a lista de permissoes for passada como vazia significa que é pra apagar tudo mesmo 
	 * 
	 * @param usuario
	 * @param permissoesDTO
	 */
	@Transactional
	public void savePerfilPermissao( Usuario usuario, PerfilPermissaoDTO permissoesDTO ){
		
		Perfil perfil = perfilRepo.findOne( permissoesDTO.getIdPerfil() );

		List<Permissao> permissoesList = null;

		if ( permissoesDTO.getIdPermissoes() != null && permissoesDTO.getIdPermissoes().size() > 0 ){
			if ( isDonoSistema( usuario ) ) {
				permissoesList = permissaoRepo.findByIdPermissaoIn( permissoesDTO.getIdPermissoes() );
			}
			else {
				permissoesList = permissaoRepo.findByIdPermissaoInAndExclusivoFalse( permissoesDTO.getIdPermissoes() );
			}
		}

		if ( perfil == null )
			throw new RuntimeException("Perfil não encontrado.");
		
		boolean algumExclusivo = permissoesList.stream().anyMatch( perm -> perm.isExclusivo() );
		
		if ( algumExclusivo ){
			if ( !Perfil.DONOS.contains( perfil ) )
				throw new RuntimeException("Você selecionou permissões exclusivas que só podem ser dadas para ADMINISTRADOR ou DESENVOLVEDOR");
		}

		perfilPermissaoRepo.deleteByPerfil( perfil );
		
		for ( Permissao p : permissoesList ){
			PerfilPermissao perfilPermissao = new PerfilPermissao( perfil, p, new Date() );
			perfilPermissaoRepo.save( perfilPermissao );
		}
	}

	
	
	private boolean isDonoSistema( Usuario usuario )
	{
		List<Perfil> perfis = usuario.getPerfis();

		return ( perfis != null && CollectionUtils.containsAny( perfis, Perfil.DONOS ) );
	}
	
	
	
}
