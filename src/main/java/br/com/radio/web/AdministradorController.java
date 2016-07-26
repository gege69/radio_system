package br.com.radio.web;

import java.io.File;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.multipart.MultipartFile;

import br.com.radio.conversao.ConverterParameters;
import br.com.radio.conversao.VariableBitRateOption;
import br.com.radio.dto.midia.DeleteMusicasVO;
import br.com.radio.dto.midia.RelatorioMidiaGeneroVO;
import br.com.radio.dto.midia.UpdateGenerosMusicasVO;
import br.com.radio.json.JSONBootstrapGridWrapper;
import br.com.radio.json.JSONListWrapper;
import br.com.radio.model.AudioOpcional;
import br.com.radio.model.Categoria;
import br.com.radio.model.Cliente;
import br.com.radio.model.Genero;
import br.com.radio.model.Midia;
import br.com.radio.model.TipoTaxa;
import br.com.radio.model.Usuario;
import br.com.radio.repository.AmbienteRepository;
import br.com.radio.repository.AudioOpcionalRepository;
import br.com.radio.repository.CategoriaRepository;
import br.com.radio.repository.ClienteRepository;
import br.com.radio.repository.GeneroRepository;
import br.com.radio.repository.MidiaRepository;
import br.com.radio.repository.TipoTaxaRepository;
import br.com.radio.service.ClienteService;
import br.com.radio.service.MidiaService;
import br.com.radio.service.UsuarioService;
import br.com.radio.util.UtilsStr;


@Controller
public class AdministradorController extends AbstractController {
	
	private final Logger logger = Logger.getLogger( AdministradorController.class );
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ClienteService adminService;
	
	@Autowired
	private GeneroRepository generoRepo;
	
	@Autowired
	private AudioOpcionalRepository opcionalRepo;
	
	@Autowired
	private MidiaRepository midiaRepo;

	@Autowired
	private MidiaService midiaService;

	@Autowired
	private CategoriaRepository categoriaRepo;
	
	@Autowired
	private TipoTaxaRepository tipoTaxaRepo;
	
	@Autowired
	private AmbienteRepository ambienteRepo;

	@Autowired
	private ClienteRepository clienteRepo;
	

	@Override
	protected Logger getLogger()
	{
		return this.logger;
	}


	@RequestMapping(value="/admin/painel", method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public String principal( ModelMap model, Principal principal )
	{
		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente() == null )
			return "HTTPerror/404";
		
		return "admin/painel";
	}




	@RequestMapping(value="/admin/generos/searches", method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public String generos( ModelMap model, Principal principal, @RequestParam(value="cadastro", required=false) String cadastro )
	{
		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente() == null )
			return "HTTPerror/404";
		
		Long quantidade = generoRepo.count();
		
		model.addAttribute( "qtdGeneros", quantidade.intValue() );
		
		boolean isCadastro = StringUtils.isNotBlank( cadastro );
		
		if ( isCadastro ){
			String mensagem = String.format( "Gênero '%s' salvo com sucesso" , cadastro );
			model.addAttribute( "success", mensagem );
		}
		
		return "admin/cadastro-generos";
	}
	


	@RequestMapping(value="/admin/generos/relatorio", method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public @ResponseBody List<RelatorioMidiaGeneroVO> generosRelatorio()
	{
		List<RelatorioMidiaGeneroVO> relatorio = midiaService.findRelatorioGeneros();
		
		return relatorio;
	}	
	
	@RequestMapping( value = { 	"/admin/generos", "/api/admin/generos" }, 
						method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public @ResponseBody JSONListWrapper<Genero> getGeneros(@RequestParam(value="pageNumber", required=false) Integer pageNumber, 
															@RequestParam(value="limit", required=false) Integer limit )
	{
		Pageable pageable = getPageable( pageNumber, limit, "asc", "nome" );

		Page<Genero> generos = generoRepo.findAllByOrderByNome( pageable );
		
		JSONListWrapper<Genero> jsonList = new JSONListWrapper<Genero>( generos.getContent(), generos.getTotalElements() );
		
		return jsonList;
	}
	
	
	@RequestMapping( value = { "/admin/generos/{idGenero}", "/api/admin/generos/{idGenero}" }, method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public @ResponseBody Genero getGeneros( @PathVariable Long idGenero )
	{
		Genero genero = generoRepo.findOne( idGenero );

		return genero;
	}
	
	
	@RequestMapping( value = { "/admin/generos", "/api/admin/generos" }, method = { RequestMethod.POST }, consumes = "application/json", produces = APPLICATION_JSON_CHARSET_UTF_8 )
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public @ResponseBody String saveGenero( @RequestBody @Valid Genero genero, BindingResult result, Principal principal, HttpServletRequest request )
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
				
				if ( usuario == null || usuario.getCliente().getIdCliente() == null )
					throw new RuntimeException("Usuário não encontrado");
				
				generoRepo.save( genero );

				jsonResult = writeObjectAsString( genero );
			}
			catch ( Exception e )
			{
				imprimeLogErro( "Salvar Gêneros", request, e );
				jsonResult = writeSingleErrorAsJSONErroMessage( "alertArea", e.getMessage() );
			}
		}

		return jsonResult;
	}
	
	
	@RequestMapping(value={ "/admin/generos/new" }, method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public String novoGenero( ModelMap model, Principal principal )
	{
		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente() == null )
			return "HTTPerror/404";

		return "admin/editar-genero";
	}


	@RequestMapping(value={ "/admin/generos/{idGenero}/view" }, method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public String editarGenero( @PathVariable Long idGenero, ModelMap model, Principal principal )
	{
		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente() == null )
			return "HTTPerror/404";
		
		Genero genero = generoRepo.findOne( idGenero );
		
		model.addAttribute( "idGenero", genero.getIdGenero() );

		return "admin/editar-genero";
	}	
	


	@RequestMapping(value= { "/admin/generos/{idGenero}", "/api/admin/generos/{idGenero}" }, method=RequestMethod.DELETE)
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public ResponseEntity<String> deleteGenero( @PathVariable Long idGenero, Principal principal, Model model, HttpServletRequest request )
	{
		String jsonResult = "";

		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente() == null )
			throw new RuntimeException("Usuário não encontrado ou Cliente não encontrada." );
		
		try
		{
			midiaService.deleteGenero( idGenero );
			jsonResult = writeOkResponse();
		}
		catch ( Exception e )
		{
			imprimeLogErro( "Deletar Gêneros", request, e );

			jsonResult = writeSingleErrorAsJSONErroMessage( "alertArea", e.getMessage() );
			return respondeErro500( jsonResult );
		}

		return respondeOk200( jsonResult );
    }	



	@RequestMapping(value="/admin/opcionais/searches", method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public String opcionais( ModelMap model, Principal principal )
	{
		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente() == null )
			return "HTTPerror/404";
		
		Long quantidade = opcionalRepo.count();
		
		model.addAttribute( "qtdOpcionais", quantidade.intValue() );
		
		return "admin/cadastro-opcionais";
	}
	
	
	@RequestMapping(value="/admin/upload-painel/view", method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public String uploadPainel( ModelMap model, Principal principal )
	{
		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente() == null )
			return "HTTPerror/404";
		
		return "admin/upload-painel";
	}
	

	@RequestMapping(value="/admin/musicas/gerencia/view", method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public String gerenciaMusicas( ModelMap model, Principal principal,
									@RequestParam(value="sucessos", required=false) Boolean sucessos )
	{
		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente() == null )
			return "HTTPerror/404";
		
		if ( sucessos == null )
			sucessos = false;

		model.addAttribute( "sucessos", sucessos );
		
		return "admin/gerencia-musica";
	}


	
	@RequestMapping( value = { 	"/admin/opcionais", "/api/admin/opcionais" }, method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public @ResponseBody JSONListWrapper<AudioOpcional> listOpcionais(
												@RequestParam(value="search", required=false) String search, 
												@RequestParam(value="pageNumber", required=false) Integer pageNumber, 
												@RequestParam(value="limit", required=false) Integer limit )
	{
		Pageable pageable = getPageable( pageNumber, limit, "asc", "nome" );

		Page<AudioOpcional> generos = null;
		
		if ( StringUtils.isBlank( search ) )
			generos = opcionalRepo.findAll( pageable );
		else {
			String nome = "%" + search + "%";
			generos = opcionalRepo.findByNomeContainingIgnoreCase( pageable, nome );
		}

		JSONListWrapper<AudioOpcional> jsonList = new JSONListWrapper<AudioOpcional>( generos.getContent(), generos.getTotalElements() );

		return jsonList;
	}


	@RequestMapping( value = { "/admin/opcionais/{idOpcional}", "/api/admin/opcionais/{idOpcional}" }, method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public @ResponseBody AudioOpcional getOpcional( @PathVariable Long idOpcional )
	{
		AudioOpcional opcional = opcionalRepo.findOne( idOpcional );

		return opcional;
	}


	@RequestMapping( value = { "/admin/opcionais", "/api/admin/opcionais" }, method = { RequestMethod.POST }, consumes = "application/json", produces = APPLICATION_JSON_CHARSET_UTF_8 )
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public @ResponseBody String saveOpcional( @RequestBody @Valid AudioOpcional opcional, BindingResult result, Principal principal, HttpServletRequest request )
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
				
				if ( usuario == null || usuario.getCliente().getIdCliente() == null )
					throw new RuntimeException("Usuário não encontrado");
				
				if ( opcional.getAtivo() == null )
					opcional.setAtivo( false );

				opcionalRepo.save( opcional );

				jsonResult = writeObjectAsString( opcional );
			}
			catch ( Exception e )
			{
				imprimeLogErro( "Erro ao salvar Opcional", request, e );
				jsonResult = writeSingleErrorAsJSONErroMessage( "alertArea", e.getMessage() );
			}
		}

		return jsonResult;
	}


	@RequestMapping(value={ "/admin/opcionais/new" }, method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public String novoOpcional( ModelMap model, Principal principal )
	{
		Usuario usuario = usuarioService.getUserByPrincipal( principal );

		if ( usuario == null || usuario.getCliente() == null )
		return "HTTPerror/404";

		return "admin/editar-opcional";
	}


	@RequestMapping(value={ "/admin/opcionais/{idOpcional}/view" }, method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public String editarOpcional( @PathVariable Long idOpcional, ModelMap model, Principal principal )
	{
		Usuario usuario = usuarioService.getUserByPrincipal( principal );

		if ( usuario == null || usuario.getCliente() == null )
		return "HTTPerror/404";

		AudioOpcional opcional = opcionalRepo.findOne( idOpcional );

		model.addAttribute( "idOpcional", opcional.getIdOpcional() );

		return "admin/editar-opcional";
	}	



	@RequestMapping(value= { "/admin/opcionais/{idOpcional}", "/api/admin/opcionais/{idOpcional}" }, method=RequestMethod.DELETE)
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public ResponseEntity<String> inativarOpcional( @PathVariable Long idOpcional, Principal principal, Model model, HttpServletRequest request )
	{
		String jsonResult = "";

		Usuario usuario = usuarioService.getUserByPrincipal( principal );

		if ( usuario == null || usuario.getCliente() == null )
			throw new RuntimeException("Usuário não encontrado ou Cliente não encontrada." );

		try
		{
			AudioOpcional opcional = opcionalRepo.findOne( idOpcional );
			
			if ( opcional == null )
				throw new RuntimeException("Opcional não encontrado");
			
			opcional.setAtivo( false );
			
			opcionalRepo.save( opcional );
			
			jsonResult = writeOkResponse();
		}
		catch ( Exception e )
		{
			imprimeLogErro( "Erro ao inativar Opcional", request, e );

			jsonResult = writeSingleErrorAsJSONErroMessage( "alertArea", e.getMessage() );
			return respondeErro500( jsonResult );
		}

		return respondeOk200( jsonResult );
	}	




	@RequestMapping(value="/admin/upload-chamadas-veiculos/view", method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public String uploadChamadasVeiculos( ModelMap model, Principal principal )
	{
		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente() == null )
			return "HTTPerror/404";
		
		return "admin/upload-chamadas-veiculos";
	}
	
	
	@RequestMapping(value="/admin/upload-letras/view", method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public String uploadLetras( ModelMap model, Principal principal )
	{
		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente() == null )
			return "HTTPerror/404";
		
		return "admin/upload-chamadas-veiculos-alfanum";
	}
	
	
	@RequestMapping(value="/admin/upload-musica/view", method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public String uploadMusica( ModelMap model, Principal principal )
	{
		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente() == null )
			return "HTTPerror/404";
		
		return "admin/upload-musica";
	}
	
	
	@RequestMapping(value="/admin/upload-opcional/view", method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public String uploadOpcional( ModelMap model, Principal principal )
	{
		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente() == null )
			return "HTTPerror/404";
		
		return "admin/upload-opcional";
	}
	
	
	
	@RequestMapping(value={ "/admin/tipotaxas/new" }, method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public String novoTipoTaxa( ModelMap model, Principal principal )
	{
		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente() == null )
			return "HTTPerror/404";

		return "admin/editar-tipotaxas";
	}


	@RequestMapping(value="/admin/tipotaxas/searches", method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public String tipotaxas( ModelMap model, Principal principal )
	{
		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente() == null )
			return "HTTPerror/404";
		
		Long quantidade = tipoTaxaRepo.count();
		
		model.addAttribute( "qtdTipotaxas", quantidade.intValue() );
		
		return "admin/cadastro-tipotaxas";
	}
	
	
	@RequestMapping(value={ "/admin/tipotaxas/{idTipotaxa}/view" }, method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public String editarTipoTaxa( @PathVariable Long idTipotaxa, ModelMap model, Principal principal )
	{
		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente() == null )
			return "HTTPerror/404";
		
		TipoTaxa tipoTaxa = tipoTaxaRepo.findOne( idTipotaxa );
		
		model.addAttribute( "idTipotaxa", tipoTaxa.getIdTipotaxa() );

		return "admin/editar-tipotaxas";
	}	


	@RequestMapping( value = { "/admin/tipotaxas/{idTipotaxa}", "/api/admin/tipotaxas/{idTipotaxa}" }, method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public @ResponseBody TipoTaxa getTipoTaxa( @PathVariable Long idTipotaxa )
	{
		TipoTaxa tipotaxa = tipoTaxaRepo.findOne( idTipotaxa );

		return tipotaxa;
	}



	@RequestMapping( value = { "/admin/tipotaxas", "/api/admin/tipotaxas" }, method = { RequestMethod.POST }, consumes = "application/json", produces = APPLICATION_JSON_CHARSET_UTF_8 )
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public @ResponseBody String saveTipoTaxa( @RequestBody @Valid TipoTaxa tipoTaxa, BindingResult result, Principal principal, HttpServletRequest request )
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
				
				if ( usuario == null || usuario.getCliente().getIdCliente() == null )
					throw new RuntimeException("Usuário não encontrado");
				
				tipoTaxaRepo.save( tipoTaxa );

				jsonResult = writeObjectAsString( tipoTaxa );
			}
			catch ( Exception e )
			{
				imprimeLogErro( "Erro ao salvar Tipo Taxa", request, e );
				jsonResult = writeSingleErrorAsJSONErroMessage( "alertArea", e.getMessage() );
			}
		}

		return jsonResult;
	}


	@RequestMapping( value = { "/admin/tipotaxas", "/api/admin/tipotaxas" }, method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody JSONBootstrapGridWrapper<TipoTaxa> listTipoTaxas( 
																 @RequestParam(value="search", required=false) String search, 
																 @RequestParam(value="pageNumber", required=false) Integer pageNumber, 
																 @RequestParam(value="limit", required=false) Integer limit,
																 Principal principal )
	{
		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente().getIdCliente() == null )
			return null;
			
		Pageable pageable = getPageable( pageNumber, limit, "asc", "descricao" );

		Page<TipoTaxa> tipotaxaPage = null;
		
		if ( StringUtils.isBlank( UtilsStr.notNull( search ) ) )
			tipotaxaPage = tipoTaxaRepo.findByAtivoTrue( pageable );
		else
			tipotaxaPage = tipoTaxaRepo.findByAtivoTrueAndDescricaoContainingIgnoreCase( pageable,  "%" + search + "%" );
		
		JSONBootstrapGridWrapper<TipoTaxa> jsonList = new JSONBootstrapGridWrapper<TipoTaxa>( tipotaxaPage.getContent(), tipotaxaPage.getTotalElements() );

		return jsonList;
	}
	



	@RequestMapping(value= { "/admin/tipotaxas/{idTipotaxa}", "/api/admin/tipotaxas/{idTipotaxa}" }, method=RequestMethod.DELETE)
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public ResponseEntity<String> deleteTipoTaxa( @PathVariable Long idTipotaxa, Principal principal, Model model, HttpServletRequest request )
	{
		String jsonResult = "";

		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente() == null )
			throw new RuntimeException("Usuário não encontrado ou Cliente não encontrada." );
		
		try
		{
			TipoTaxa tipoTaxa = tipoTaxaRepo.findOne( idTipotaxa );
			
			if ( tipoTaxa == null )
				throw new RuntimeException("Tipo de Taxa não foi encontrado");
			
			// apenas inativando... não posso remover as ligações com as condições comerciais que existem....
			tipoTaxa.setAtivo( false );
			tipoTaxaRepo.save( tipoTaxa );

			jsonResult = writeOkResponse();
		}
		catch ( Exception e )
		{
			imprimeLogErro( "Erro ao deletar tipo Taxa", request, e );

			jsonResult = writeSingleErrorAsJSONErroMessage( "alertArea", e.getMessage() );
			return respondeErro500( jsonResult );
		}

		return respondeOk200( jsonResult );
    }	

	
	
	@RequestMapping( value = { "/admin/midias/musicas", "/api/admin/midias/musicas" }, method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody JSONBootstrapGridWrapper<Midia> listMusicas(
																	@RequestParam(value="search", required = false) String search,  
																	@RequestParam(value="pageNumber", required = false) Integer pageNumber,  
																	@RequestParam(value="limit", required = false) Integer limit,
																	@RequestParam(value="generoFixado", required = false) Long generoFixado 
																	)
	{
		Pageable pageable = getPageable( pageNumber, limit, "asc", "nome" ); 
		
		Page<Midia> page = null;
		
		if ( generoFixado != null && generoFixado > 0 )
			page = midiaRepo.findByCustomSearchByGenero( pageable, UtilsStr.ilike( search ), generoFixado);
		else
			page = midiaRepo.findByCustomSearch( pageable, UtilsStr.ilike( search ));

		List<Midia> midiasList = page.getContent();
		
		Map<Midia, Boolean> mapFilaConversao = midiaService.verificaMusicasNaFilaConversao( midiasList );
		
		midiasList.forEach( midia -> {
			String generos = midiaService.getResumoGenerosDaMidia( midia );
			midia.getMidiaView().put( "generosResumo", generos );
			midia.getMidiaView().put( "conversao", mapFilaConversao.get(midia) );
		});
		
		JSONBootstrapGridWrapper<Midia> jsonList = new JSONBootstrapGridWrapper<>( midiasList, page.getTotalElements() );

		return jsonList;
	}
	


	@RequestMapping( value = { "/admin/midias/musicas/generos", "/api/admin/midias/musicas/generos" }, method = { RequestMethod.POST },  produces = APPLICATION_JSON_CHARSET_UTF_8  )
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public @ResponseBody String updateGenerosGeral( @RequestBody UpdateGenerosMusicasVO generosMidiaVO, BindingResult result, HttpServletRequest request )
	{
		String jsonResult = "";
		
		if ( result.hasErrors() )
			jsonResult = writeErrorsAsJSONErroMessage( result );
		else
		{
			try
			{
				midiaService.alteraGenerosGeral(generosMidiaVO);
				jsonResult = writeOkResponse();
			}
			catch ( Exception e )
			{
				imprimeLogErro( "Altera Generos varias músicas", request, e );
				jsonResult = writeSingleErrorAsJSONErroMessage( "alertArea", e.getMessage() );
			}
		}

		return jsonResult;
	}


	@RequestMapping( value = { "/admin/midias/musicas", "/api/admin/midias/musicas" }, method = { RequestMethod.DELETE },  produces = APPLICATION_JSON_CHARSET_UTF_8  )
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public @ResponseBody String deleteMusicasSelecao( @RequestBody DeleteMusicasVO deleteMusicasVO, BindingResult result, HttpServletRequest request )
	{
		String jsonResult = "";
		
		if ( result.hasErrors() )
			jsonResult = writeErrorsAsJSONErroMessage( result );
		else
		{
			try
			{
				midiaService.deleteMusicasSelecao(deleteMusicasVO);
				jsonResult = writeOkResponse();
			}
			catch ( Exception e )
			{
				imprimeLogErro( "Erro ao deletar uma seleção de músicas", request, e );
				jsonResult = writeSingleErrorAsJSONErroMessage( "alertArea", e.getMessage() );
			}
		}

		return jsonResult;
	}



	
	@RequestMapping( value = { "/admin/midias", "/api/admin/midias" }, method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody JSONBootstrapGridWrapper<Midia> listMidiaByCategoria(
																	@RequestParam(value="search", required = false) String search,  
																	@RequestParam(value="codigo", required = false) String codigo,  
																	@RequestParam(value="pageNumber", required = false) Integer pageNumber,  
																	@RequestParam(value="limit", required = false) Integer limit, 
																	@RequestParam(value="order", required = false) String order )
	{
		Pageable pageable = null;
		
		List<Categoria> categorias = new ArrayList<Categoria>();

		if ( StringUtils.isNotBlank( codigo ) )
		{
			categorias.add( categoriaRepo.findByCodigo( codigo ) );
			pageable = getPageable( pageNumber, limit, order, "nome" ); 
		}
		else
		{
			String[] cats = new String[] { Categoria.VEIC_PLACA_LETRA, Categoria.VEIC_PLACA_NUMERO };
			categorias = categoriaRepo.findByCodigoIn( Arrays.asList( cats ) );
			pageable = getPageable( pageNumber, limit, "asc", "descricao" ); 
		}
		
		if ( categorias == null )
			throw new RuntimeException("Categoria não encontrada");
		
		Page<Midia> page = null;
		
		if ( StringUtils.isBlank( search ) )
			page = midiaRepo.findByCategoriasInAndValidoTrue( pageable, categorias );
		else
		{
			String nome = "%" + search + "%";
			
			page = midiaRepo.findByNomeContainingAndCategoriasInAndValidoTrue( pageable, nome, categorias );
		}

		List<Midia> midiasList = page.getContent();
		
		midiasList.forEach( midia -> {
			String generos = midiaService.getResumoGenerosDaMidia( midia );
			midia.getMidiaView().put( "generosResumo", generos );
		});
		
		JSONBootstrapGridWrapper<Midia> jsonList = new JSONBootstrapGridWrapper<>( midiasList, page.getTotalElements() );

		return jsonList;
	}
	



	
	@RequestMapping( value = { "/admin/midias/opcionais", "/api/admin/midias/opcionais" }, method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody JSONBootstrapGridWrapper<Midia> listMidiaOpcional(
//																	@RequestParam(value="search", required = false) String search,  
																	@RequestParam(value="idOpcional", required = false) Long idOpcional,  
																	@RequestParam(value="pageNumber", required = false) Integer pageNumber,  
																	@RequestParam(value="limit", required = false) Integer limit, 
																	@RequestParam(value="order", required = false) String order )
	{
		Pageable pageable = null;
		
		pageable = getPageable( pageNumber, limit, order, "nome" ); 

		AudioOpcional opcional = null;

		if ( idOpcional != null )
			opcional = opcionalRepo.findOne( idOpcional );
		
		Page<Midia> page = midiaService.getMidiasOpcionais( null, opcional, pageable );
		
		List<Midia> midiasList = page.getContent();
		
		for ( Midia midia : midiasList ){
			String generos = midiaService.getResumoGenerosDaMidia( midia );
			midia.getMidiaView().put( "generos", generos );
			if ( opcional != null )
				midia.getMidiaView().put( "opcional", opcional.getNome() );
		}
		
		JSONBootstrapGridWrapper<Midia> jsonList = new JSONBootstrapGridWrapper<>( midiasList, page.getTotalElements() );

		return jsonList;
	}



	@RequestMapping(value= { "/admin/midias/opcionais/{idMidia}", "/api/admin/midias/opcionais/{idMidia}" }, method=RequestMethod.DELETE)
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public ResponseEntity<String> deleteMidiaOpcional( @PathVariable Long idMidia, Principal principal, Model model, HttpServletRequest request )
	{
		String jsonResult = "";

		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente() == null )
			throw new RuntimeException("Usuário não encontrado ou Cliente não encontrada." );
		
		try
		{
			midiaService.deleteMidiaOpcionalSePossivel( idMidia );
			jsonResult = writeOkResponse();
		}
		catch ( Exception e )
		{
			imprimeLogErro( "Deletar Opcional", request, e );

			jsonResult = writeSingleErrorAsJSONErroMessage( "alertArea", e.getMessage() );
			return respondeErro500( jsonResult );
		}

		return respondeOk200( jsonResult );
    }	
	
	
	
	@RequestMapping( value = { "/admin/upload-chamadas-veiculos", "/api/admin/upload-chamadas-veiculos" }, method = { RequestMethod.POST },  produces = APPLICATION_JSON_CHARSET_UTF_8 )
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public ResponseEntity<String> saveUploadChamadasVeiculos( @RequestParam("file") MultipartFile file, 
															@RequestParam("codigo") String codigo, 
															@RequestParam(value="descricao", required=false) String descricao,
															Principal principal,
															HttpServletRequest request)
	{
		String jsonResult = null;
		
		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente().getIdCliente() == null )
			throw new RuntimeException("Usuário não encontrado");
		
		if ( file != null && !file.isEmpty() )
		{
			try
			{
				Midia midia = midiaService.saveUploadChamadaVeiculo( file, codigo, usuario.getCliente(), descricao );
				
				JsonObjectBuilder builder = Json.createObjectBuilder();
				JsonObjectBuilder builder2 = Json.createObjectBuilder();
				jsonResult = builder.add("files", builder2.add( "name", midia.getNome() ) ).build().toString();
			}
			catch ( Exception e )
			{
				imprimeLogErro( "Upload Chamada Veículos", request, e );

				jsonResult = writeSingleErrorAsJSONErroMessage( "alertArea", e.getMessage() );
				return respondeErro500( jsonResult );
			}
		}
		else
		{
			jsonResult = writeSingleErrorAsJSONErroMessage( "alertArea", "Arquivo está vazio" );
			return respondeErro500( jsonResult );
		}

		return respondeOk200( jsonResult );
	}	
	
	
	
	@RequestMapping( value = { "/admin/chamada-veiculos", 
							   "/api/admin/chamada-veiculos",
							   "/api/admin/midia" }, method = { RequestMethod.POST }, consumes = "application/json", produces = APPLICATION_JSON_CHARSET_UTF_8 )
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public @ResponseBody String saveNomeMidia( @RequestBody Midia midiaVO, BindingResult result, Principal principal, HttpServletRequest request )
	{
		String jsonResult = null;
	
		try
		{
			Usuario usuario = usuarioService.getUserByPrincipal( principal );
			
			if ( usuario == null || usuario.getCliente().getIdCliente() == null )
				throw new RuntimeException("Usuário não encontrado");
			
			midiaService.alteraNomeMidia( midiaVO );
			
			jsonResult = writeOkResponse();
		}
		catch ( Exception e )
		{
			imprimeLogErro( "Salvar nome Mídia", request, e );
			jsonResult = writeSingleErrorAsJSONErroMessage( "alertArea", e.getMessage() );
		}

		return jsonResult;
	}
	



	@RequestMapping( value = { "/admin/midias/musicas" }, method = { RequestMethod.POST }, consumes = "application/json", produces = APPLICATION_JSON_CHARSET_UTF_8 )
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public @ResponseBody String saveMusica( @RequestBody Midia midiaVO, BindingResult result, Principal principal, HttpServletRequest request )
	{
		String jsonResult = null;
	
		try
		{
			Usuario usuario = usuarioService.getUserByPrincipal( principal );
			
			if ( usuario == null || usuario.getCliente().getIdCliente() == null )
				throw new RuntimeException("Usuário não encontrado");
			
			midiaService.alteraMusica( midiaVO );
			
			jsonResult = writeOkResponse();
		}
		catch ( Exception e )
		{
			imprimeLogErro( "Salvar dados Música", request, e );
			jsonResult = writeSingleErrorAsJSONErroMessage( "alertArea", e.getMessage() );
		}

		return jsonResult;
	}

	@RequestMapping(value= { "/admin/chamada-veiculos/{idMidia}", 
							 "/api/admin/chamada-veiculos/{idMidia}",
							 "/api/admin/midia/{idMidia}" }, method=RequestMethod.DELETE)
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public ResponseEntity<String> deleteChamadaVeiculo( @PathVariable Long idMidia, Principal principal, Model model, HttpServletRequest request )
	{
		String jsonResult = "";

		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente() == null )
			throw new RuntimeException("Usuário não encontrado ou Cliente não encontrada." );
		
		try
		{
			midiaService.deleteMidiaSePossivel( idMidia );
			jsonResult = writeOkResponse();
		}
		catch ( Exception e )
		{
			imprimeLogErro( "Deletar Chamada Veículo", request, e );

			jsonResult = writeSingleErrorAsJSONErroMessage( "alertArea", e.getMessage() );
			return respondeErro500( jsonResult );
		}

		return respondeOk200( jsonResult );
    }	


	@RequestMapping(value = { "/admin/midias/{idMidia}", "/api/admin/midias/{idMidia}" }, method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public @ResponseBody ResponseEntity<FileSystemResource> downloadMidia(@PathVariable Long idMidia, Principal principal) {
		
		Midia midia = midiaRepo.findOne( idMidia );
		
		if ( midia == null )
			return ResponseEntity.unprocessableEntity().body( null );
		
		File arquivo = new File( midia.getFilepath() );
		
		FileSystemResource fsr = new FileSystemResource( arquivo );

		String name = Integer.valueOf( midia.getNome().hashCode() ).toString();
		
		return ResponseEntity.ok()
				.header( "Content-Disposition", "attachment; filename=\""+ name +"\"" )
				.contentLength( midia.getFilesize() )
				.contentType( MediaType.parseMediaType( midia.getMimetype() ) )
				.body( fsr );
	}

	

//	@RequestMapping(value="/api/midias/converter", method=RequestMethod.POST)
//	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
//	public ResponseEntity<String> converteMidia(
//			@RequestBody ConverterParameters parameters,
//			BindingResult result,
//    		Principal principal, 
//    		Model model,
//    		HttpServletRequest request )
//	{
//		String jsonResult = "";
//
//		Usuario usuario = usuarioService.getUserByPrincipal( principal );
//		
//		if ( usuario == null || usuario.getCliente() == null )
//			throw new RuntimeException( "Usuário não encontrado ou Cliente não encontrada."  );
//		
//		if ( result.hasErrors() ){
//			jsonResult = writeErrorsAsJSONErroMessage(result);	
//		}
//		else { 
//				
//			try
//			{
//				midiaService.converteMusica( parameters );
//						
//				jsonResult = writeOkResponse();
//			}
//			catch ( Exception e )
//			{
//				imprimeLogErro( "Conversão de Mídia", request, e );
//
//				jsonResult = writeSingleErrorAsJSONErroMessage( "alertArea", e.getMessage() );
//				return respondeErro500( jsonResult );
//			}
//		}
//
//		return respondeOk200( jsonResult );
//    }	
		


	@RequestMapping(value={ "/admin/conversao-config/view" }, method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public String conversao( ModelMap model, Principal principal )
	{
		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente() == null )
			return "HTTPerror/404";
		
		return "admin/conversao-config";
	}	

	
	@RequestMapping(value="/admin/conversao-config", method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public @ResponseBody ConverterParameters getConversaoParametros()
	{
		ConverterParameters converterParameters = midiaService.getConverterParameters();
		
		return converterParameters;
	}	



	@RequestMapping(value="/admin/conversao-config/quantidade-mp3", method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public @ResponseBody String getQuantidadeMP3()
	{
		Long qtdMP3 = midiaRepo.countByExtensao( "mp3" );
		
		JsonObject obj = Json.createObjectBuilder()
				.add("qtd", qtdMP3)
				.build();
				
		String json = obj.toString();
				
		return json;
	}	

	@RequestMapping(value="/admin/conversao-config", method=RequestMethod.POST)
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public ResponseEntity<String> saveConversaoParametros(
			@RequestBody @Valid ConverterParameters params,
			BindingResult result,
    		Principal principal, 
    		Model model,
    		HttpServletRequest request)
	{
		String jsonResult = "";

		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente() == null )
			throw new RuntimeException("Usuário não encontrado ou Cliente não encontrada." );
		
		if ( result.hasErrors() ){
			
			jsonResult = writeErrorsAsJSONErroMessage(result);	
		}
		else
		{
			try
			{
				midiaService.saveConfiguracoesConversao( params );
						
				jsonResult = writeOkResponse();
			}
			catch ( Exception e )
			{
				imprimeLogErro( "Salvar Parâmetros de Conversão", request, e );

				jsonResult = writeSingleErrorAsJSONErroMessage( "alertArea", e.getMessage() );
				return respondeErro500( jsonResult );
			}
		}

		return respondeOk200( jsonResult );
    }		



	@RequestMapping(value="/admin/conversao-config/tudo", method=RequestMethod.POST)
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public ResponseEntity<String> converterTudo(
    		Principal principal, 
    		Model model,
    		HttpServletRequest request)
	{
		String jsonResult = "";

		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente() == null )
			throw new RuntimeException("Usuário não encontrado ou Cliente não encontrada." );
		
		try
		{
			midiaService.converterTudo();
					
			jsonResult = writeOkResponse();
		}
		catch ( Exception e )
		{
			imprimeLogErro( "Conversão Total", request, e );

			jsonResult = writeSingleErrorAsJSONErroMessage( "alertArea", e.getMessage() );
			return respondeErro500( jsonResult );
		}

		return respondeOk200( jsonResult );
    }		



	@RequestMapping(value="/admin/bitrates", method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public @ResponseBody List<VariableBitRateOption> listVariableBitRate()
	{
		List<VariableBitRateOption> variables = VariableBitRateOption.listaExibicao();
		
		return variables;
	}	

	
	@RequestMapping(value={ "/admin/tocar-chamadas-veiculos/view" }, method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public String tocarChamadasVeiculos( ModelMap model, Principal principal )
	{
		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente() == null )
			return "HTTPerror/404";

		return "admin/tocar-chamadas-veiculos";
	}
	
	
	@RequestMapping(value="/admin/upload-opcional", method=RequestMethod.POST)
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public ResponseEntity<String> uploadOpcional(
    		@RequestParam("file") MultipartFile file, 
    		@RequestParam("idOpcional") Long idOpcional,
    		@RequestParam(name="descricao", required=false) String descricao,
    		Principal principal, 
    		Model model,
    		HttpServletRequest request)
	{
		String jsonResult = "";

		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente() == null )
			throw new RuntimeException("Usuário não encontrado ou Cliente não encontrada." );
		
		if ( file != null && !file.isEmpty() )
		{
			try
			{
				midiaService.saveUploadOpcional( file, usuario.getCliente(), descricao, idOpcional );
						
				jsonResult = writeOkResponse();
			}
			catch ( Exception e )
			{
				imprimeLogErro( "Upload de Opcional", request, e );

				jsonResult = writeSingleErrorAsJSONErroMessage( "alertArea", e.getMessage() );
				return respondeErro500( jsonResult );
			}
		}
		else
		{
			jsonResult = writeSingleErrorAsJSONErroMessage( "alertArea", "Arquivo está vazio" );
			return respondeErro500( jsonResult );
		}

		return respondeOk200( jsonResult );
    }		
	
	
	
	@RequestMapping(value="/admin/clientes/{idCliente}/ambientes", method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public String cadastroAmbientesAdmin( @PathVariable Long idCliente, ModelMap model, Principal principal )
	{
		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente() == null )
			return "HTTPerror/404";
		
		Cliente cliente = clienteRepo.findOne( idCliente );
		
		if ( cliente == null )
			return "HTTPerror/404";
	
		Long count = ambienteRepo.countByCliente( cliente );
		
		model.addAttribute( "qtdAmbientes", count );
		model.addAttribute( "idCliente", idCliente );
		
		return "admin/cadastro-ambientes-admin";
	}
	
}

