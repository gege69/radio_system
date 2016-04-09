package br.com.radio.service;

import java.security.Principal;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.radio.enumeration.UsuarioTipo;
import br.com.radio.model.Conversa;
import br.com.radio.model.Mensagem;
import br.com.radio.model.Usuario;
import br.com.radio.repository.ConversaRepository;
import br.com.radio.repository.MensagemRepository;
import br.com.radio.util.UtilsDates;


@Service
public class ConversaService {
	
	@Autowired
	private MensagemRepository mensagemRepo;
	
	@Autowired
	private ConversaRepository conversaRepo;
	
	@Autowired
	private UsuarioService usuarioService;
	
	
	public Page<Conversa> getListaConversasPorUsuario( Usuario usuario, Pageable pageable )
	{
		Page<Conversa> conversaPage = null;
		
		if ( usuario.getUsuarioTipo().equals( UsuarioTipo.PLAYER ) )
			conversaPage = conversaRepo.findByClienteAndAtivoAndUsuariosIn( pageable, usuario.getCliente(), true, Arrays.asList( new Usuario[] { usuario } ) );
		else
			conversaPage = conversaRepo.findByClienteAndAtivo( pageable, usuario.getCliente(), true );
		
		List<Conversa> conversas = conversaPage.getContent();
		
		conversas.forEach( c -> c.buildView() );
		return conversaPage;
	}
	
	
	public Conversa saveConversa( Conversa conversa, Principal principal )
	{
		return saveConversa( conversa, null, principal );
	}
	
	public Conversa saveConversa( Conversa conversa, Conversa conversaOrigem, Principal principal )
	{
		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente() == null )
			throw new RuntimeException( "Impossível determinar o Cliente do usuário" );
		
		if ( conversa != null )
		{
			if ( !conversa.getUsuarios().stream().anyMatch( u -> u.equals( usuario ) ) ) 
				conversa.getUsuarios().add( usuario );
			
			Date hoje = new Date();
			
			conversa.setCliente( usuario.getCliente() );
			conversa.setAtivo( true );
			conversa.setDataCriacao( hoje );
			conversa.setDataAtualizacao( hoje );
			
			conversa.setConversaOrigem( conversaOrigem );

			conversaRepo.save( conversa );	
		}
		
		conversa.buildView();
		
		return conversa;
	}
	
	
	private boolean conversaEhBroadcast( Conversa conversa, Usuario usuario )
	{
		boolean result = false;
		
		if ( conversa != null )
		{
			boolean existeAlgumOutroPlayer = conversa.getUsuarios().stream().anyMatch( u -> !u.equals( usuario ) && u.getUsuarioTipo().equals( UsuarioTipo.PLAYER ) );
			
			result = existeAlgumOutroPlayer;
		}
		
		return result;
	}
	
	
	
	@Transactional
	public Mensagem saveMensagem( Mensagem mensagem, Principal principal )
	{
		Usuario usuarioLogado = usuarioService.getUserByPrincipal( principal );
		
		if ( usuarioLogado == null || usuarioLogado.getCliente() == null )
			throw new RuntimeException( "Impossível determinar o Cliente do usuário" );
		
		Conversa conversaOriginal = conversaRepo.findOne( mensagem.getConversa().getIdConversa() );
		
		Date hoje = new Date();

		// Se estamos em um Player tenho que verificar para não mandar resposta para outros players....
		if ( usuarioLogado.getUsuarioTipo().equals( UsuarioTipo.PLAYER ) &&
			 conversaEhBroadcast( conversaOriginal, usuarioLogado ) )
		{
			Conversa novaConversa = new Conversa();
			
			Set<Usuario> usuariosConversaOriginal = new HashSet<Usuario>( conversaOriginal.getUsuarios() );

			List<Usuario> usuariosNovaConversa = usuariosConversaOriginal.stream().filter( u -> u.getUsuarioTipo().equals( UsuarioTipo.GERENCIADOR ) ).collect( Collectors.toList() );

			usuariosNovaConversa.add( usuarioLogado );
			novaConversa.setUsuarios( usuariosNovaConversa );
			
			novaConversa = saveConversa( novaConversa, conversaOriginal, principal );
			
			
			// :TODO talvez duplicar as mensagens do broadcast inicial.... ao invés disso
			Mensagem mensagemExplicacao = new Mensagem();
			mensagemExplicacao.setConteudo( String.format( "Essa conversação foi criada automaticamente como resposta ao Broadcast com data em : %s ", UtilsDates.format( conversaOriginal.getDataCriacao(), "dd/MM/yyyy HH:mm" )  ) );
			mensagemExplicacao.setConversa( novaConversa );
			mensagemExplicacao.setDataEnvio( hoje );
			mensagemRepo.save( mensagemExplicacao );
			
			mensagem.setConversa( novaConversa );
			mensagem.getMensagemView().put( "novaConversa", "true" );
		}
		else
		{
			conversaOriginal.setDataAtualizacao( hoje );
			conversaRepo.save( conversaOriginal );
		}
		
		mensagem.setDataEnvio( hoje );
		mensagem.setUsuario( usuarioLogado );
		mensagemRepo.save( mensagem );
		
		mensagem.buildView( usuarioLogado );
		
		return mensagem;
	}
	

}
