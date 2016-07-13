package br.com.radio.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.json.Json;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import br.com.radio.dto.AlterarSenhaAdminDTO;
import br.com.radio.dto.AlterarSenhaDTO;
import br.com.radio.dto.EndPointDTO;
import br.com.radio.dto.PerfilPermissaoDTO;
import br.com.radio.dto.UsuarioGerenciadorDTO;
import br.com.radio.dto.cliente.ParametroDTO;
import br.com.radio.enumeration.UsuarioTipo;
import br.com.radio.json.JSONBootstrapGridWrapper;
import br.com.radio.json.JSONListWrapper;
import br.com.radio.model.AudioOpcional;
import br.com.radio.model.Categoria;
import br.com.radio.model.Cliente;
import br.com.radio.model.FusoHorario;
import br.com.radio.model.Genero;
import br.com.radio.model.Parametro;
import br.com.radio.model.Perfil;
import br.com.radio.model.PerfilPermissao;
import br.com.radio.model.Permissao;
import br.com.radio.model.Usuario;
import br.com.radio.repository.AmbienteRepository;
import br.com.radio.repository.AudioOpcionalRepository;
import br.com.radio.repository.CategoriaRepository;
import br.com.radio.repository.FusoHorarioRepository;
import br.com.radio.repository.GeneroRepository;
import br.com.radio.repository.ParametroRepository;
import br.com.radio.repository.PerfilPermissaoRepository;
import br.com.radio.repository.PerfilRepository;
import br.com.radio.repository.PermissaoRepository;
import br.com.radio.repository.UsuarioRepository;
import br.com.radio.service.ClienteService;
import br.com.radio.service.UsuarioService;

import com.google.common.collect.Lists;

/**
 * Esse controller vai refletir o primeiro nível do sistema. A visão do Gerencial. 
 * 
 * Algumas funcionalidades podem ter controllers exclusivos ( Ex: Ambiente ) 
 *
 * Existe um nível ainda maior que é o de Administrador que pode ter vários Gerenciadores clientes abaixo dele.
 * 
 * @author pazin
 *
 */
@Controller
public class GerenciadorController extends AbstractController {

	private final Logger logger = Logger.getLogger( GerenciadorController.class );
	
	@Autowired
	private GeneroRepository generoRepo;
	@Autowired
	private UsuarioRepository usuarioRepo;
	@Autowired
	private AmbienteRepository ambienteRepo;
	@Autowired
	private FusoHorarioRepository fusoRepo;
	@Autowired
	private CategoriaRepository categoriaRepo;
	@Autowired
	private PerfilRepository perfilRepo;
	@Autowired
	private AudioOpcionalRepository opcionalRepo;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private PermissaoRepository permissaoRepo;
	@Autowired
	private PerfilPermissaoRepository perfilPermissaoRepo;
	@Autowired
	private ParametroRepository parametroRepo;
	@Autowired
	private ClienteService clienteService;

	@Autowired
	private RequestMappingHandlerMapping requestMappingHandlerMapping;
	

	@Override
	protected Logger getLogger()
	{
		return this.logger;
	}


	@RequestMapping( value = "endpoints", method = RequestMethod.GET )
	public String getEndPointsInView( Model model )
	{
		Comparator<EndPointDTO> byPath = (EndPointDTO i1, EndPointDTO i2) -> i1.getPatternsCondition().compareTo( i2.getPatternsCondition());
		
		List<EndPointDTO> endpoints = new ArrayList<EndPointDTO>();
		
		requestMappingHandlerMapping.getHandlerMethods().forEach( ( info, method ) -> {
			
			EndPointDTO dto = new EndPointDTO();
			
			dto.setController( method.getBeanType().getName() );
			
			String path = info.getPatternsCondition().toString();
			
//			path = path.replaceAll( "||", "<br/>" );
			
			dto.setPatternsCondition( path );
			
			dto.setMethodsCondition( info.getMethodsCondition().toString() );
			dto.setParamsCondition( info.getParamsCondition().toString() );
			dto.setHeadersCondition( info.getHeadersCondition().toString() );
			dto.setConsumesCondition( info.getConsumesCondition().toString() );
			dto.setProducesCondition( info.getProducesCondition().toString() );
			dto.setCustomCondition( info.getCustomCondition() != null ? info.getCustomCondition().toString() : "" );
			
			endpoints.add( dto );
		});
		
		endpoints.sort( byPath );
		
	    model.addAttribute( "endPoints", endpoints );
	    
	    return "admin/endpoints";
	}
	
	
	
	/**
	 * Em caso de sucesso no login esse é o primeiro ponto de acesso à aplicação. 
	 * ( Exceto quando o login é de um player... nesse caso é jogado para o Player ).
	 * 
	 * @param model
	 * @param principal
	 * @return
	 */
	@RequestMapping(value="/principal", method=RequestMethod.GET)
	public String principal( ModelMap model, Principal principal, HttpServletRequest request )
	{
		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		Cliente cliente = usuario.getCliente();
		
		if ( usuario == null || cliente == null )
			return null;
		
		Parametro color = parametroRepo.findByCodigoAndCliente( "BACKGROUNDCOLOR", cliente );
		Parametro tema = parametroRepo.findByCodigoAndCliente( "TEMA", cliente );
		
		if ( color != null && StringUtils.isNotBlank( color.getValor() ) ){
			request.getSession().setAttribute( "backgroundColor", color.getValor() );
		}
		
		if ( tema != null && StringUtils.isNotBlank( tema.getValor() ) ){
			request.getSession().setAttribute( "tema", tema.getValor() );
		}
		
		boolean isDono = verificaDono( usuario );

		model.addAttribute( "isDono", isDono );

		Long count = ambienteRepo.countByCliente( cliente );
		
		model.addAttribute( "razaoSocial", cliente.getRazaosocial() );
		model.addAttribute( "nomeFantasia", cliente.getNomefantasia() );
		
		model.addAttribute( "qtdAmbientes", count );
		model.addAttribute( "isAdministrador", hasAuthority( "ADM_SISTEMA" ));
		
		return "gerenciador/principal";
	}



	
	@RequestMapping( value = { "/parametros", "/api/parametros" }, method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody ParametroDTO getParametro( HttpServletResponse response, Principal principal )
	{
		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente() == null )
			throw new RuntimeException("Cliente não encontrado.");

		// Por enquanto só retorna o tema
		Parametro parametro = parametroRepo.findByCodigoAndCliente( "TEMA", usuario.getCliente() );
		
		ParametroDTO parametroDTO = new ParametroDTO();
		parametroDTO.setTema( parametro.getValor() );
		
		return parametroDTO;
	}


	

	@RequestMapping( value = { "/parametros", "/api/parametros" }, method = { RequestMethod.POST }, consumes = "application/json", produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody String saveParametro( @RequestBody ParametroDTO parametroDTO, BindingResult result, Principal principal, HttpServletRequest request )
	{
		String jsonResult = null;
		
		if ( result.hasErrors() ){
			
			jsonResult = writeErrorsAsJSONErroMessage(result);	
		}
		else
		{
			try
			{
				Usuario usuario = usuarioService.getUserByPrincipal( principal );
				
				if ( usuario == null || usuario.getCliente() == null )
					throw new RuntimeException("Cliente não encontrado.");

				clienteService.saveParametro( parametroDTO, usuario.getCliente() );
				
				jsonResult = writeOkResponse();
			}
			catch ( Exception e )
			{
				imprimeLogErro( "Salvar Parâmetros", request, e );
				jsonResult = writeSingleErrorAsJSONErroMessage( "alertArea", e.getMessage() );
			}
		}

		return jsonResult;
	}

	
	@RequestMapping(value="/fazer")
	public String fazer( ModelMap model )
	{
		return "gerenciador/fazer";
	}


	
	
	@RequestMapping(value="/senha/edit", method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('ALTERAR_SENHA')")
	public String alterarSenha( ModelMap model )
	{
		return "gerenciador/alterar-senha";
	}
	
	
	// O método POST com essa mesma URL está no MidiaController até que o Upload via ajax seja feito...
	@RequestMapping(value="/view-upload-multi", method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('UPLOAD_AMBIENTE')")
	public String viewUploadMulti( ModelMap model )
	{
		return "gerenciador/upload-multi";
	}

	
	@RequestMapping(value="/view-atalho-cham-func", method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('CHAMADA_FUNCIO')")
	public String atalhoChamadaFunc( HttpServletResponse response )
	{
		return "gerenciador/view-atalho-cham-func";
	}
	
	
	@RequestMapping(value="/usuarios/searches", method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('USUARIOS')")
	public String usuariosSistema( ModelMap model, Principal principal )
	{
		return "gerenciador/cadastro-usuarios";
	}



	@RequestMapping(value={ "/usuarios/view", "/usuarios/{idUsuario}/view" } , method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('USUARIOS')")
	public String usuarios( @PathVariable Map<String, String> pathVariables, ModelMap model )
	{
		if ( pathVariables.containsKey( "idUsuario" ) )
		{
			Usuario usuario = usuarioRepo.findOne( Long.valueOf( pathVariables.get( "idUsuario" ) ) );

			if ( usuario != null )
			{
				model.addAttribute( "idUsuario", usuario.getIdUsuario() );
				model.addAttribute( "idCliente", usuario.getCliente().getIdCliente() );
			
				return "gerenciador/editar-usuario";
			}
			else
				return "HTTPerror/404";
		}
		else
			return "gerenciador/editar-usuario";
	}
	
	
	

	@RequestMapping( value = { "/usuarios", "/api/usuarios" }, method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	@PreAuthorize("hasAuthority('USUARIOS')")
	public @ResponseBody JSONListWrapper<Usuario> getUsuarios( @RequestParam(value="pageNumber", required=false) Integer pageNumber, 
																 @RequestParam(value="limit", required=false) Integer limit,
																 @RequestParam(value="all", required=false) Boolean all,
																 @RequestParam(value="sort", required=false) String sort,
																 Principal principal )
	{
		// Pegando a cliente pelo usuário logado
		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente() == null )
			return null;
		
		Pageable pageable = getPageable( pageNumber, limit, "asc", sort );
		
		Page<Usuario> usuarioPage = null;
		
		if ( all != null && all )
			usuarioPage = usuarioRepo.findByCliente( pageable, usuario.getCliente() );
		else
			usuarioPage = usuarioRepo.findByClienteAndUsuarioTipo( pageable, usuario.getCliente(), UsuarioTipo.GERENCIADOR );
		
		JSONListWrapper<Usuario> jsonList = new JSONListWrapper<Usuario>(usuarioPage.getContent(), usuarioPage.getTotalElements() );

		return jsonList;
	}
	
	
	@RequestMapping( value = { "/usuarios/{idUsuario}", "/api/usuarios/{idUsuario}" }, method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	@PreAuthorize("hasAuthority('USUARIOS')")
	public @ResponseBody Usuario getUsuario( @PathVariable Long idUsuario, HttpServletResponse response )
	{
		Usuario usuario = usuarioRepo.findOne( idUsuario );
		
		return usuario;
	}

	
	
	@RequestMapping( value = { "/usuarios", "/api/usuarios" }, method = { RequestMethod.POST }, consumes = "application/json", produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody String saveUsuario( @RequestBody @Valid UsuarioGerenciadorDTO usuarioGerenciadorDTO, BindingResult result, Principal principal, HttpServletRequest request )
	{
		String jsonResult = null;
		
		if ( result.hasErrors() ){
			
			jsonResult = writeErrorsAsJSONErroMessage(result);	
		}
		else
		{
			try
			{
				Usuario usuario = usuarioService.getUserByPrincipal( principal );
				
				if ( usuario == null || usuario.getCliente() == null )
					throw new RuntimeException("Cliente não encontrado.");

				usuarioService.saveUsuarioGerenciador( usuarioGerenciadorDTO, usuario.getCliente() );
				
				jsonResult = writeOkResponse();
			}
			catch ( Exception e )
			{
				imprimeLogErro( "Salvar Usuário", request, e );
				jsonResult = writeSingleErrorAsJSONErroMessage( "alertArea", e.getMessage() );
			}
		}

		return jsonResult;
	}
	


	@RequestMapping( value = { "/usuarios/{idUsuario}/senha", "/api/usuarios/{idUsuario}/senha" }, method = { RequestMethod.POST }, consumes = "application/json", produces = APPLICATION_JSON_CHARSET_UTF_8 )
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public @ResponseBody String alteraSenhaUsuarioGerenciador( @RequestBody @Valid AlterarSenhaAdminDTO alteraSenhaAdminDTO, BindingResult result, Principal principal, HttpServletRequest request )
	{
		String jsonResult = null;
		
		if ( result.hasErrors() ){
			
			jsonResult = writeErrorsAsJSONErroMessage(result);	
		}
		else
		{
			try
			{
				Usuario usuario = usuarioService.getUserByPrincipal( principal );
				
				if ( usuario == null || usuario.getCliente() == null )
					throw new RuntimeException("Cliente não encontrado.");

				usuarioService.alteraSenhaUsuarioGerenciador( alteraSenhaAdminDTO, usuario.getCliente() );
				
				jsonResult = writeOkResponse();
			}
			catch ( Exception e )
			{
				imprimeLogErro( "Senha Usuario", request, e );
				jsonResult = writeSingleErrorAsJSONErroMessage( "alertArea", e.getMessage() );
			}
		}

		return jsonResult;
	}


	
	
	@RequestMapping(value="/perfis/view", method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('PERFIS')")
	public String perfisView( ModelMap model )
	{
		return "gerenciador/perfil";
	}
		

	
	@RequestMapping( value = { "/perfis", "/api/perfis" }, method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	@PreAuthorize("hasAuthority('USUARIOS') OR hasAuthority('PERFIS')")
	public @ResponseBody JSONBootstrapGridWrapper<Perfil> listPerfis()
	{
		boolean admSistema = hasAuthority( "ADM_SISTEMA" );

		List<Perfil> perfilList = null;
		
		if ( admSistema )
			perfilList = perfilRepo.findAll();
		else
			perfilList = perfilRepo.findByComumTrue();
		
		int total = perfilList.size();
		
		JSONBootstrapGridWrapper<Perfil> jsonList = new JSONBootstrapGridWrapper<Perfil>(perfilList, total);

		return jsonList;
	}
	
	
	@RequestMapping( value = { "/perfis/permissoes", "/api/perfis/permissoes" }, method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	@PreAuthorize("hasAuthority('PERFIS')")
	public @ResponseBody JSONBootstrapGridWrapper<Permissao> listPermissoes(
																	 @RequestParam(value="pageNumber", required=false) Integer pageNumber, 
																	 @RequestParam(value="limit", required=false) Integer limit, 
																	 Principal principal 
																 )
	{
		Pageable pageable = getPageable( pageNumber, 99, "asc", "codigo" );

		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente() == null )
			throw new RuntimeException("Cliente não encontrado.");

		Page<Permissao> permissaoPage;

		boolean isDono = verificaDono( usuario );

		if ( isDono )
			permissaoPage = permissaoRepo.findAll( pageable );
		else 
			permissaoPage = permissaoRepo.findByExclusivoFalse( pageable );

		JSONBootstrapGridWrapper<Permissao> jsonList = new JSONBootstrapGridWrapper<Permissao>(permissaoPage.getContent(), permissaoPage.getTotalElements());

		return jsonList;
	}


	private boolean verificaDono( Usuario usuario )
	{
		List<Perfil> perfis = usuario.getPerfis();

		boolean isDono =  perfis != null && CollectionUtils.containsAny( perfis, Perfil.DONOS );

		return isDono;
	}	
	
	
	@RequestMapping( value = { "/perfis/{idPerfil}/permissoes", "/api/perfis/{idPerfil}/permissoes" }, method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	@PreAuthorize("hasAuthority('PERFIS')")
	public @ResponseBody JSONBootstrapGridWrapper<PerfilPermissao> listPermissoes(
																	 @PathVariable Long idPerfil,
																	 Principal principal )
	{
		Pageable pageable = getPageable( 1, 99, "asc", "idPerfperm" );

		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente() == null )
			throw new RuntimeException("Cliente não encontrado.");
		
		Perfil perfil = perfilRepo.findOne( idPerfil );

		Page<PerfilPermissao> perfilPermissaoPage = perfilPermissaoRepo.findByPerfil( pageable, perfil );
		
		JSONBootstrapGridWrapper<PerfilPermissao> jsonList = new JSONBootstrapGridWrapper<PerfilPermissao>(perfilPermissaoPage.getContent(), perfilPermissaoPage.getTotalElements());

		return jsonList;
	}


	@RequestMapping( value = { "/perfis/{idPerfil}/permissoes", "/api/perfis/{idPerfil}/permissoes" }, method = { RequestMethod.POST }, consumes = "application/json", produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody String savePerfilPermissao( @PathVariable Long idPerfil, @RequestBody @Valid PerfilPermissaoDTO permissoesDTO, BindingResult result, Principal principal, HttpServletRequest request )
	{
		String jsonResult = null;
		
		if ( result.hasErrors() ){
			jsonResult = writeErrorsAsJSONErroMessage(result);	
		}
		else
		{
			try
			{
				Usuario usuario = usuarioService.getUserByPrincipal( principal );
				
				if ( usuario == null || usuario.getCliente() == null )
					throw new RuntimeException("Cliente não encontrado.");
				
				usuarioService.savePerfilPermissao( usuario, permissoesDTO );
				
				jsonResult = writeOkResponse();
			}
			catch ( Exception e )
			{
				imprimeLogErro( "Salvar Perfil Permissão", request, e );
				jsonResult = writeSingleErrorAsJSONErroMessage( "alertArea", e.getMessage() );
			}
		}

		return jsonResult;
	}


	
	@RequestMapping( value = { "/fusohorarios", "/api/fusohorarios" }, method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody JSONListWrapper<FusoHorario> listFusos()
	{
		List<FusoHorario> fusoList = fusoRepo.findAllWithSortByOrderComum();
		
		int total = fusoList.size();
		
		JSONListWrapper<FusoHorario> jsonList = new JSONListWrapper<FusoHorario>(fusoList, total);

		return jsonList;
	}	
	
	

	
	/**
	 * Lista todos os gêneros que estejam cadastrados no banco de dados ( não restringe por cliente, talvez possa melhorar )
	 * 
	 * @param idAmbiente
	 * @param response
	 * @return
	 */
	@RequestMapping( value = { 	"/generos", "/api/generos" }, 
						method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody JSONListWrapper<Genero> listGeneros( HttpServletResponse response )
	{
		List<Genero> generos = generoRepo.findAllByOrderByNome();
		
		JSONListWrapper<Genero> jsonList = new JSONListWrapper<Genero>( generos, this.qtd );
		
		return jsonList;
	}
		
	
	
	@RequestMapping( value = { 	"/categorias", "/api/categorias" }, 
			method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody JSONListWrapper<Categoria> listCategorias( @RequestParam(value="simpleUpload", required=false) Boolean simpleUpload, HttpServletResponse response )
	{
		List<Categoria> categorias = null;
		
		if ( simpleUpload != null )
			categorias = categoriaRepo.findBySimpleUpload( simpleUpload );
		else
			categorias = categoriaRepo.findAll();
		
		// O parametro upload caso seja true filtra o download apenas para categorias onde o usuário pode fazer upload pela tela de Ambiente
		JSONListWrapper<Categoria> jsonList = new JSONListWrapper<Categoria>( categorias, this.qtd );
		
		return jsonList;
	}



	@RequestMapping( value = { "/opcionais", "/api/opcionais" }, method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody JSONBootstrapGridWrapper<AudioOpcional> listOpcionais()
	{
		List<AudioOpcional> opcionalList = opcionalRepo.findAll();
		
		int total = opcionalList.size();
		
		JSONBootstrapGridWrapper<AudioOpcional> jsonList = new JSONBootstrapGridWrapper<AudioOpcional>(opcionalList, total);

		return jsonList;
	}


	
	
	@RequestMapping(value="/senha", method=RequestMethod.POST, produces=APPLICATION_JSON_CHARSET_UTF_8)
	@PreAuthorize("hasAuthority('ALTERAR_SENHA')")
	public @ResponseBody String gravaNovaSenha( @RequestBody @Valid AlterarSenhaDTO senhaDTO, BindingResult result, Principal principal, HttpServletRequest request )
	{
		String jsonResult = "";

		if ( result.hasErrors() ){
			
			jsonResult = writeErrorsAsJSONErroMessage(result);	
		}
		else
		{
			try
			{
				usuarioService.changeUserPassword( principal.getName(), senhaDTO );
				
				jsonResult = Json.createObjectBuilder().add("ok", true).build().toString();
			}
			catch ( Exception e )
			{
				imprimeLogErro( "Grava Nova Senha", request, e );
				jsonResult = writeSingleErrorAsJSONErroMessage( "alertArea", e.getMessage() );
			}
		}
		
		return jsonResult;
	}
	


		
}
