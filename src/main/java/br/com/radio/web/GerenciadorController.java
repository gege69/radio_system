package br.com.radio.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.json.Json;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import br.com.radio.dto.AlterarSenhaDTO;
import br.com.radio.dto.EndPointDTO;
import br.com.radio.json.JSONListWrapper;
import br.com.radio.model.Categoria;
import br.com.radio.model.FusoHorario;
import br.com.radio.model.Genero;
import br.com.radio.model.Usuario;
import br.com.radio.repository.AmbienteRepository;
import br.com.radio.repository.CategoriaRepository;
import br.com.radio.repository.FusoHorarioRepository;
import br.com.radio.repository.GeneroRepository;
import br.com.radio.repository.UsuarioRepository;
import br.com.radio.service.UsuarioService;

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
	private UsuarioService usuarioService;

	@Autowired
	private RequestMappingHandlerMapping requestMappingHandlerMapping;

	@RequestMapping( value = "endpoints", method = RequestMethod.GET )
	public String getEndPointsInView( Model model )
	{
		
		Comparator<EndPointDTO> byPath = (EndPointDTO i1, EndPointDTO i2) -> i1.getPatternsCondition().compareTo( i2.getPatternsCondition());
		
		List<EndPointDTO> endpoints = new ArrayList<EndPointDTO>();
		
		requestMappingHandlerMapping.getHandlerMethods().forEach( ( info, method ) -> {
			
			EndPointDTO dto = new EndPointDTO();
			
			dto.setController( method.getBeanType().getName() );
			dto.setPatternsCondition( info.getPatternsCondition().toString() );
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
	
	
	
	@RequestMapping(value="/principal", method=RequestMethod.GET)
	public String viewPrincipal( ModelMap model, Principal principal )
	{
		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getEmpresa() == null )
			return null;
		
		Long count = ambienteRepo.countByEmpresa( usuario.getEmpresa() );
		
		model.addAttribute( "qtdAmbientes", count );
		
		return "gerenciador/principal";
	}
	
	
	@RequestMapping(value="/fazer")
	public String fazer( ModelMap model )
	{
		return "gerenciador/fazer";
	}


	
	
	@RequestMapping(value="/senha/edit", method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('ALTERAR_SENHA')")
	public String viewAlterarSenha( ModelMap model )
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
	public String viewAtalhoChamadaFunc( HttpServletResponse response )
	{
		return "gerenciador/view-atalho-cham-func";
	}
	
	
	@RequestMapping(value="/view-list-usuarios-sistema", method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('USUARIOS')")
	public String viewListUsuariosSistema( ModelMap model )
	{
		return "gerenciador/view-list-usuarios-sistema";
	}
	

	@RequestMapping(value={ "/view-usuario/{idUsuario}", "/view-usuario" } , method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('USUARIOS')")
	public String viewUsuario( @PathVariable Map<String, String> pathVariables, ModelMap model )
	{
		if ( pathVariables.containsKey( "idUsuario" ) )
		{
			Usuario usuario = usuarioRepo.findOne( Long.valueOf( pathVariables.get( "idUsuario" ) ) );

			if ( usuario != null )
			{
				model.addAttribute( "idUsuario", usuario.getIdUsuario() );
			
				return "gerenciador/view-usuario";
			}
			else
				return "HTTPerror/404";
		}
		else
			return "gerenciador/view-usuario";
	}
	
	
	@RequestMapping( value = { "/usuarios", "/api/usuarios" }, method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	@PreAuthorize("hasAuthority('USUARIOS')")
	public @ResponseBody JSONListWrapper<Usuario> listUsuarios( @RequestParam(value="pagina", required=false) Integer pagina, 
																 @RequestParam(value="limit", required=false) Integer limit,
																 Principal principal )
	{
		// Pegando a empresa pelo usuário logado
		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getEmpresa() == null )
			return null;
		
		Pageable pageable = getPageable( pagina, limit );
			
		Page<Usuario> usuarioPage = usuarioRepo.findByEmpresa( pageable, usuario.getEmpresa() );
		
		JSONListWrapper<Usuario> jsonList = new JSONListWrapper<Usuario>(usuarioPage.getContent(), usuarioPage.getTotalElements() );

		return jsonList;
	}
	
	
	@RequestMapping( value = { "/usuarios/{idUsuario}", "/api/usuarios/{idUsuario}" }, method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	@PreAuthorize("hasAuthority('USUARIOS')")
	public @ResponseBody Usuario getAmbiente( @PathVariable Long idUsuario, HttpServletResponse response )
	{
		Usuario usuario = usuarioRepo.findOne( idUsuario );
		
		return usuario;
	}
	
	
	@RequestMapping( value = { "/fusohorarios", "/api/fusohorarios" }, method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody JSONListWrapper<FusoHorario> listFusos()
	{
		List<FusoHorario> ncmList = fusoRepo.findAllWithSortByOrderComum();
		
		int total = ncmList.size();
		
		JSONListWrapper<FusoHorario> jsonList = new JSONListWrapper<FusoHorario>(ncmList, total);

		return jsonList;
	}	
	
	

	
	/**
	 * Lista todos os gêneros que estejam cadastrados no banco de dados ( não restringe por empresa, talvez possa melhorar )
	 * 
	 * @param idAmbiente
	 * @param response
	 * @return
	 */
	@RequestMapping( value = { 	"/generos", "/api/generos" }, 
						method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody JSONListWrapper<Genero> getGeneros( HttpServletResponse response )
	{
		List<Genero> generos = generoRepo.findAll();
		
		JSONListWrapper<Genero> jsonList = new JSONListWrapper<Genero>( generos, this.qtd );
		
		return jsonList;
	}
		
	
	
	@RequestMapping( value = { 	"/categorias", "/api/categorias" }, 
			method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody JSONListWrapper<Categoria> getCategorias( @RequestParam(value="simpleUpload", required=false) Boolean simpleUpload, HttpServletResponse response )
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

	
	
	@RequestMapping(value="/senha", method=RequestMethod.POST, produces=APPLICATION_JSON_CHARSET_UTF_8)
	@PreAuthorize("hasAuthority('ALTERAR_SENHA')")
	public @ResponseBody String gravaNovaSenha( @RequestBody @Valid AlterarSenhaDTO senhaDTO, BindingResult result, Principal principal )
	{
		String jsonResult = "";

		if ( result.hasErrors() ){
			
			jsonResult = getErrorsAsJSONErroMessage(result);	
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
				jsonResult = getSingleErrorAsJSONErroMessage( "alertArea", e.getMessage() );
			}
		}
		
		return jsonResult;
	}
	
	
}
