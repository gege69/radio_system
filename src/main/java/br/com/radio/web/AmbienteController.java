package br.com.radio.web;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

import br.com.radio.dto.AlterarSenhaAmbienteDTO;
import br.com.radio.dto.EspelharAmbienteDTO;
import br.com.radio.dto.GeneroListDTO;
import br.com.radio.dto.MidiaListDTO;
import br.com.radio.dto.midia.MidiaFilter;
import br.com.radio.enumeration.DiaSemana;
import br.com.radio.enumeration.StatusAmbiente;
import br.com.radio.exception.ResourceNotFoundException;
import br.com.radio.json.JSONBootstrapGridWrapper;
import br.com.radio.json.JSONListWrapper;
import br.com.radio.model.Ambiente;
import br.com.radio.model.AmbienteConfiguracao;
import br.com.radio.model.AmbienteGenero;
import br.com.radio.model.Bloco;
import br.com.radio.model.Evento;
import br.com.radio.model.EventoHorario;
import br.com.radio.model.Funcionalidade;
import br.com.radio.model.Genero;
import br.com.radio.model.Midia;
import br.com.radio.model.Programacao;
import br.com.radio.model.Usuario;
import br.com.radio.programacaomusical.ProgramacaoMusicalService;
import br.com.radio.repository.AmbienteConfiguracaoRepository;
import br.com.radio.repository.AmbienteGeneroRepository;
import br.com.radio.repository.AmbienteRepository;
import br.com.radio.repository.BlocoRepository;
import br.com.radio.repository.EventoHorarioRepository;
import br.com.radio.repository.EventoRepository;
import br.com.radio.repository.FuncionalidadeRepository;
import br.com.radio.repository.ProgramacaoRepository;
import br.com.radio.service.AmbienteService;
import br.com.radio.service.ClienteService;
import br.com.radio.service.UsuarioService;
import br.com.radio.service.midia.MidiaService;

@Controller
public class AmbienteController extends AbstractController {

	private final Logger logger = Logger.getLogger( AmbienteController.class );
		
	// DAOs ==================
	@Autowired
	private AmbienteRepository ambienteRepo;
	@Autowired
	private FuncionalidadeRepository funcionalidadeRepo;
	@Autowired
	private AmbienteGeneroRepository ambienteGeneroRepo;
	@Autowired
	private AmbienteConfiguracaoRepository ambienteConfigRepo;
	@Autowired
	private BlocoRepository blocoRepo;
	@Autowired
	private ProgramacaoRepository programacaoRepo;
	@Autowired
	private EventoRepository eventoRepo;
	@Autowired
	private EventoHorarioRepository eventoHorarioRepo;
	// DAOs ==================
	
	
	// Services ==============
	@Autowired
	private AmbienteService ambienteService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private MidiaService midiaService;
	@Autowired
	private ProgramacaoMusicalService programacaoMusicalService;
	// Services ==============

	
	@Override
	protected Logger getLogger()
	{
		return this.logger;
	}


	@RequestMapping( value = "/ambientes/{idAmbiente}/view", method = RequestMethod.GET )
	@PreAuthorize("hasAuthority('ADMINISTRAR_AMB')")
	public String viewAmbiente( @PathVariable Long idAmbiente, ModelMap model, HttpServletResponse response )
	{
		Ambiente ambiente = ambienteRepo.findOne( idAmbiente );

		if ( ambiente != null )
		{
			model.addAttribute( "idAmbiente", ambiente.getIdAmbiente() );
			model.addAttribute( "nome", ambiente.getNome() );
			model.addAttribute( "urlambiente", ambiente.getUrlambiente() );
			model.addAttribute( "login", ambiente.getLogin() );
		
			return "ambiente/ambiente";
		}
		else
			return "HTTPerror/404";
	}
	
	
	
	@RequestMapping(value="/ambientes/{idAmbiente}/espelhar", method=RequestMethod.GET)
	public String espelharView( @PathVariable Long idAmbiente, ModelMap model, HttpServletResponse response )
	{
		Ambiente ambiente = ambienteRepo.findOne( idAmbiente );

		if ( ambiente != null )
		{
			model.addAttribute( "idAmbiente", ambiente.getIdAmbiente() );
			model.addAttribute( "nome", ambiente.getNome() );
		
			return "ambiente/espelhar-ambiente";
		}
		else
			return "HTTPerror/404";
		
	}
	
	@RequestMapping(value="/ambientes/{idAmbiente}/editar", method=RequestMethod.GET)
	public String editar( @PathVariable Long idAmbiente, ModelMap model, HttpServletResponse response )
	{
		model.addAttribute( "idAmbiente", idAmbiente );
		
		return "ambiente/editar-ambiente";
	}


	@RequestMapping(value="/ambientes/{idAmbiente}/senha/edit", method=RequestMethod.GET)
	public String alterarSenhaAmbiente( @PathVariable Long idAmbiente, ModelMap model, HttpServletResponse response )
	{
		Ambiente ambiente = ambienteRepo.findOne( idAmbiente );

		if ( ambiente != null )
		{
			model.addAttribute( "idAmbiente", ambiente.getIdAmbiente() );
			model.addAttribute( "nome", ambiente.getNome() );
			model.addAttribute( "login", ambiente.getLogin() );
		
			return "ambiente/alterar-login-senha-ambiente";
		}
		else
			return "HTTPerror/404";
	}

	
	@RequestMapping(value="/ambientes/new", method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('INCLUIR_AMB')")
	public String incluir( ModelMap model )
	{
		return "ambiente/incluir";
	}
	
	@RequestMapping(value="/ambientes/searches" , method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMINISTRAR_AMB')")
	public String cadastroAmbientes( ModelMap model, Principal principal )
	{
		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente() == null )
			return null;
		
		Long count = ambienteRepo.countByCliente( usuario.getCliente() );
		
		model.addAttribute( "qtdAmbientes", count );
		
		return "ambiente/cadastro-ambientes";
	}
	
	
	@RequestMapping( value = "/ambientes/{idAmbiente}/expedientes/view", method = RequestMethod.GET )
	public String expedientes( @PathVariable Long idAmbiente, ModelMap model, HttpServletResponse response )
	{
		Ambiente ambiente = ambienteRepo.findOne( idAmbiente );
		
		if ( ambiente != null )
		{
			model.addAttribute( "idAmbiente", ambiente.getIdAmbiente() );
			model.addAttribute( "nome", ambiente.getNome() );

			model.addAttribute( "func", funcionalidadeRepo.findByCodigo( "expediente" ) );	

			return "ambiente/expedientes";
		}
		else
			return "HTTPerror/404";
	}
	
	
	@RequestMapping( value = "/ambientes/{idAmbiente}/generos/view", method = RequestMethod.GET )
	public String generos( @PathVariable Long idAmbiente, ModelMap model, HttpServletResponse response )
	{
		Ambiente ambiente = ambienteRepo.findOne( idAmbiente );
		
		if ( ambiente != null )
		{
			model.addAttribute( "idAmbiente", ambiente.getIdAmbiente() );
			model.addAttribute( "nome", ambiente.getNome() );
			model.addAttribute( "func", funcionalidadeRepo.findByCodigo( "generos" ) );	
		
			return "ambiente/generos";
		}
		else
			return "HTTPerror/404";
	}
	
	
	@RequestMapping( value = "/ambientes/{idAmbiente}/configuracoes/view", method = RequestMethod.GET )
	public String configuracoes( @PathVariable Long idAmbiente, ModelMap model, HttpServletResponse response )
	{
		Ambiente ambiente = ambienteRepo.findOne( idAmbiente );
		
		if ( ambiente != null )
		{
			model.addAttribute( "idAmbiente", ambiente.getIdAmbiente() );
			model.addAttribute( "nome", ambiente.getNome() );
			model.addAttribute( "func", funcionalidadeRepo.findByCodigo( "config" ) );	

			return "ambiente/configuracoes";
		}
		else
			return "HTTPerror/404";
	}
	
	
	@RequestMapping( value = "/ambientes/{idAmbiente}/blocos/view", method = RequestMethod.GET )
	public String blocos( @PathVariable Long idAmbiente, ModelMap model, HttpServletResponse response )
	{
		Ambiente ambiente = ambienteRepo.findOne( idAmbiente );
		
		if ( ambiente != null )
		{
			model.addAttribute( "idAmbiente", ambiente.getIdAmbiente() );
			model.addAttribute( "nome", ambiente.getNome() );
			model.addAttribute( "func", funcionalidadeRepo.findByCodigo( "blocos" ) );	
		
			return "ambiente/blocos";
		}
		else
			return "HTTPerror/404";
	}
	
	
	@RequestMapping( value = "/ambientes/{idAmbiente}/programacoes/view", method = RequestMethod.GET )
	public String programacoes( @PathVariable Long idAmbiente, ModelMap model, HttpServletResponse response )
	{
		Ambiente ambiente = ambienteRepo.findOne( idAmbiente );
		
		if ( ambiente != null )
		{
			model.addAttribute( "idAmbiente", ambiente.getIdAmbiente() );
			model.addAttribute( "nome", ambiente.getNome() );
			model.addAttribute( "func", funcionalidadeRepo.findByCodigo( "prog_musical" ) );	

			return "ambiente/programacoes";
		}
		else
			return "HTTPerror/404";
	}


	
	@RequestMapping( value = "/ambientes/{idAmbiente}/logomarcas/view", method = RequestMethod.GET )
	public String logomarcas( @PathVariable Long idAmbiente, ModelMap model, HttpServletResponse response )
	{
		Ambiente ambiente = ambienteRepo.findOne( idAmbiente );
		
		if ( ambiente != null )
		{
			model.addAttribute( "idAmbiente", ambiente.getIdAmbiente() );
			model.addAttribute( "nome", ambiente.getNome() );
			model.addAttribute( "func", funcionalidadeRepo.findByCodigo( "logomarca" ) );	

			return "ambiente/logomarcas";
		}
		else
			return "HTTPerror/404";
	}
	
	
	
	@RequestMapping( value = "/ambientes/{idAmbiente}/eventos/view", method = RequestMethod.GET )
	public String eventos( @PathVariable Long idAmbiente, ModelMap model, HttpServletResponse response )
	{
		Ambiente ambiente = ambienteRepo.findOne( idAmbiente );
		
		if ( ambiente != null )
		{
			model.addAttribute( "idAmbiente", ambiente.getIdAmbiente() );
			model.addAttribute( "nome", ambiente.getNome() );
			model.addAttribute( "func", funcionalidadeRepo.findByCodigo( "eventos" ) );	

			return "ambiente/eventos";
		}
		else
			return "HTTPerror/404";
	}

	
	
	@RequestMapping( value = { "/ambientes/{idAmbiente}", "/api/ambientes/{idAmbiente}" }, method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	@PreAuthorize("hasAuthority('ADMINISTRAR_AMB')")
	public @ResponseBody Ambiente getAmbiente( @PathVariable Long idAmbiente, HttpServletResponse response )
	{
		Ambiente ambiente = ambienteRepo.findOne( idAmbiente );
		
		return ambiente;
	}
	
	


	@RequestMapping( value = { 	"/ambientes/{idAmbiente}/funcionalidades/{modo}", 
								"/api/ambientes/{idAmbiente}/funcionalidades/{modo}" }, 
					 method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody List<Funcionalidade> getFuncionalidadesAmbiente( @PathVariable Long idAmbiente, @PathVariable String modo, HttpServletResponse response )
	{
		// Dependendo do modo de operação vai entregar uma lista com mais ou menos opções para serem desenhadas...
		List<Funcionalidade> funcionalidades = funcionalidadeRepo.findByAtivo( new Sort( Sort.Direction.ASC, "ordem" ) , true );
		
		funcionalidades.stream().forEach( f -> {
			f.getFuncView().put( "url_funcionalidade", String.format( f.getUrl(), idAmbiente ) );
		});
		
		return funcionalidades;
	}
	
	
	
	@RequestMapping( value = { "/ambientes", "/api/ambientes" }, method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody JSONBootstrapGridWrapper<Ambiente> listAmbiente( 
																 @RequestParam(value="search", required=false) String search, 
																 @RequestParam(value="pageNumber", required=false) Integer pageNumber, 
																 @RequestParam(value="limit", required=false) Integer limit,
																 Principal principal )
	{
		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente().getIdCliente() == null )
			return null;
		
		Pageable pageable = getPageable( pageNumber, limit, "asc", "nome" );
		
		Page<Ambiente> ambientePage = null;
		
		if ( StringUtils.isBlank( search )){
			ambientePage = ambienteRepo.findByCliente( pageable, usuario.getCliente() );
		}
		else
		{
			String nome = "%"+ search + "%";
			ambientePage = ambienteRepo.findByClienteAndNomeContainingIgnoreCase( pageable, usuario.getCliente(), nome );
		}
		
		JSONBootstrapGridWrapper<Ambiente> jsonList = new JSONBootstrapGridWrapper<Ambiente>(ambientePage.getContent(), ambientePage.getTotalElements() );

		return jsonList;
	}


	
	@RequestMapping( value = { "/ambientes", "/api/ambientes" }, method = { RequestMethod.POST }, consumes = "application/json", produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody String saveAmbiente( @RequestBody @Valid Ambiente ambiente, BindingResult result, Principal principal, HttpServletRequest request )
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
					throw new RuntimeException("Cliente do usuário não econtrado");
				
				ambiente.setCliente( usuario.getCliente() );
				
				ambienteService.saveAmbiente( ambiente );
				
				programacaoMusicalService.verificaECriaProgramacaoDefault( ambiente );
				
				jsonResult = writeOkResponse();
			}
			catch ( Exception e )
			{
				imprimeLogErroAmbiente( "Erro ao salvar ambiente", ambiente, request, e );

				jsonResult = writeSingleErrorAsJSONErroMessage( "alertArea", e.getMessage() );
			}
		}

		return jsonResult;
	}
	

	@RequestMapping(value="/ambientes/{idAmbiente}/senha", method=RequestMethod.POST, produces=APPLICATION_JSON_CHARSET_UTF_8)
	@PreAuthorize("hasAuthority('ALTERAR_SENHA')")
	public @ResponseBody String saveNovaSenhaAmbiente( @PathVariable Long idAmbiente, @RequestBody @Valid AlterarSenhaAmbienteDTO senhaDTO, BindingResult result, Principal principal, HttpServletRequest request )
	{
		String jsonResult = "";

		if ( result.hasErrors() ){
			
			jsonResult = writeErrorsAsJSONErroMessage(result);	
		}
		else
		{
			try
			{
				ambienteService.alteraLoginSenhaAmbiente( idAmbiente, senhaDTO );
				
				jsonResult = Json.createObjectBuilder().add("ok", true).build().toString();
			}
			catch ( Exception e )
			{
				imprimeLogErroAmbiente( "Salvar Nova Senha", idAmbiente, request, e);
				jsonResult = writeSingleErrorAsJSONErroMessage( "alertArea", e.getMessage() );
			}
		}
		
		return jsonResult;
	}



	@RequestMapping(value="/ambientes/espelhar", method=RequestMethod.POST, consumes = "application/json", produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody String espelhar( @RequestBody EspelharAmbienteDTO espelharDTO, BindingResult result, Principal principal, HttpServletRequest request )
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
					throw new RuntimeException("Cliente do usuário não econtrado");
				
				ambienteService.espelharAmbiente( espelharDTO );
				
				jsonResult = writeOkResponse();
			}
			catch ( Exception e )
			{
				imprimeLogErro( String.format("Erro ao espelhar ambientes (%s)", espelharDTO.toString() ), request, e );
				jsonResult = writeSingleErrorAsJSONErroMessage( "alertArea", e.getMessage() );
			}
		}

		return jsonResult;
	}	

	@RequestMapping( value = { "/ambientes/inativar", "/api/ambientes/inativar" }, method = { RequestMethod.POST }, consumes = "application/json", produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody String inativarAmbiente( @RequestBody Ambiente ambienteVO, BindingResult result, Principal principal, HttpServletRequest request )
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
					throw new RuntimeException("Cliente do usuário não econtrado");
				
				Ambiente ambienteAtual = ambienteRepo.findOne( ambienteVO.getIdAmbiente() );
				
				if ( ambienteAtual == null )
					throw new RuntimeException( "Ambiente não encontrado" );
				
				if ( ambienteAtual.getStatus().equals( StatusAmbiente.INATIVO ) )
					throw new RuntimeException("Ambiente já está inativo");
				
				ambienteAtual.setStatus( StatusAmbiente.INATIVO );;
				ambienteAtual.setDataAlteracao( new Date() );
				
				// Log vai ser no monitorar
				ambienteRepo.save( ambienteAtual );
				
				jsonResult = writeOkResponse();
			}
			catch ( Exception e )
			{
				imprimeLogErroAmbiente( "Inativar", ambienteVO.getIdAmbiente(), request, e );

				jsonResult = writeSingleErrorAsJSONErroMessage( "alertArea", e.getMessage() );
			}
		}

		return jsonResult;
	}	


	@RequestMapping( value = { "/ambientes/ativar", "/api/ambientes/ativar" }, method = { RequestMethod.POST }, consumes = "application/json", produces = APPLICATION_JSON_CHARSET_UTF_8 )
	@PreAuthorize("hasAuthority('ADMINISTRAR_AMB')")
	public @ResponseBody String ativarAmbiente( @RequestBody Ambiente ambienteVO, BindingResult result, Principal principal, HttpServletRequest request )
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
					throw new RuntimeException("Cliente do usuário não econtrado");
				
				Ambiente ambienteAtual = ambienteRepo.findOne( ambienteVO.getIdAmbiente() );
				
				if ( ambienteAtual == null )
					throw new RuntimeException( "Ambiente não encontrado" );
				
				if ( StatusAmbiente.ATIVO.equals( ambienteAtual.getStatus() ) )
					throw new RuntimeException("Ambiente já está ativo");
				
				ambienteAtual.setStatus( StatusAmbiente.ATIVO );
				ambienteAtual.setDataAlteracao( new Date() );
				
				ambienteRepo.save( ambienteAtual );
				
				jsonResult = writeOkResponse();
			}
			catch ( Exception e )
			{
				imprimeLogErroAmbiente( "Ativar", ambienteVO.getIdAmbiente(), request, e );

				jsonResult = writeSingleErrorAsJSONErroMessage( "alertArea", e.getMessage() );
			}
		}

		return jsonResult;
	}	

	
	
	/**
	 * Retorna uma lista os gêneros que estão associados com esse ambiente específico.
	 * 
	 * @param idAmbiente
	 * @param response
	 * @return
	 */
	@RequestMapping( value = { 	"/ambientes/{idAmbiente}/generos", "/api/ambientes/{idAmbiente}/generos" }, 
						method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody JSONListWrapper<Genero> getGenerosAssoc( @PathVariable Long idAmbiente, HttpServletResponse response )
	{
		Ambiente ambiente = ambienteRepo.findOne( idAmbiente );

		// Talvez eu possa mudar isso pra que o relacionando fique diretamente na Entity de Gênero 
		List<AmbienteGenero> ambienteGeneros = ambienteGeneroRepo.findByAmbiente( ambiente );

		// Colecionando apenas os IDs dos gêneros que estão associados à esse ambiente
		List<Genero> generos = ambienteGeneros.stream().map( ab -> ab.getGenero() ).collect( Collectors.toList() );

		JSONListWrapper<Genero> jsonList = new JSONListWrapper<Genero>( generos, this.qtd );
		
		return jsonList;
	}
	
	
	
	
	@RequestMapping( value = { 	"/ambientes/{idAmbiente}/generos", "/api/ambientes/{idAmbiente}/generos" }, method = { RequestMethod.POST }, consumes = "application/json", produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody String saveGeneros( @PathVariable Long idAmbiente, @RequestBody GeneroListDTO generoList, BindingResult result, HttpServletRequest request )
	{
		String jsonResult = "";
		
		try
		{
			boolean saved = ambienteService.saveGeneros( idAmbiente, generoList );
				
			if ( saved )
				jsonResult = writeOkResponse();
			else
				jsonResult = writeSingleErrorAsJSONErroMessage( "alertArea", "Erro ao salvar associação de Gêneros do ambiente" );
		}
		catch ( Exception e )
		{
			imprimeLogErroAmbiente( "Associar Gêneros ao ambiente", idAmbiente, request, e );
			jsonResult = writeSingleErrorAsJSONErroMessage( "alertArea", e.getMessage() );
		}
		
		return jsonResult;
	}

	
	
	@RequestMapping( value = { 	"/ambientes/{idAmbiente}/programacoes/{idProgramacao}/generos", "/api/ambientes/{idAmbiente}/programacoes/{idProgramacao}/generos" }, 
				method = { RequestMethod.POST }, consumes = "application/json", produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody String saveProgramacoesGeneros( @PathVariable Long idAmbiente, @PathVariable Long idProgramacao, @RequestBody GeneroListDTO generoList, BindingResult result, HttpServletRequest request )
	{
		String jsonResult = "";
		
		Ambiente ambiente = null;
		try
		{
			ambiente = ambienteRepo.findOne( idAmbiente );

			Programacao programacao = programacaoRepo.findOne( idProgramacao );

			programacaoMusicalService.gravaGenerosProgramacao( ambiente, programacao, generoList.getLista() );
				
			jsonResult = writeOkResponse();
		}
		catch ( Exception e )
		{
			imprimeLogErroAmbiente( "Gravar Gêneros da Programação", ambiente, request, e );

			jsonResult = writeSingleErrorAsJSONErroMessage( "alertArea", e.getMessage() );
		}
		
		return jsonResult;
	}



	
	@RequestMapping( value = { 	"/ambientes/{idAmbiente}/programacoes/generos/{dia}", "/api/ambientes/{idAmbiente}/programacoes/generos/{dia}" }, 
			method = { RequestMethod.POST }, consumes = "application/json", produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody String saveProgramacoesGenerosDiaInteiro( @PathVariable Long idAmbiente, @PathVariable String dia, @RequestBody GeneroListDTO generoList, BindingResult result, HttpServletRequest request )
	{
		String jsonResult = "";
		
		Ambiente ambiente = null;
		try
		{
			ambiente = ambienteRepo.findOne( idAmbiente );

			DiaSemana diaSemana = DiaSemana.valueOf( dia );
			
			programacaoMusicalService.gravaGenerosProgramacaoDiaInteiro( ambiente, diaSemana, generoList.getLista() );
				
			jsonResult = writeOkResponse();
		}
		catch ( Exception e )
		{
			imprimeLogErroAmbiente( "Gravar Gêneros Programção Dia", ambiente, request, e );

			jsonResult = writeSingleErrorAsJSONErroMessage( "alertArea", e.getMessage() );
		}
		
		return jsonResult;
	}

	
	
	/**
	 * Retorna a lista com as programações do ambiente.
	 * 
	 * @param idAmbiente
	 * @param response
	 * @return
	 */
	@RequestMapping( value = { 	"/ambientes/{idAmbiente}/programacoes", "/api/ambientes/{idAmbiente}/programacoes" }, 
						method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody JSONListWrapper<Programacao> getProgramacoes( @PathVariable Long idAmbiente, HttpServletResponse response )
	{
		Ambiente ambiente = ambienteRepo.findOne( idAmbiente );

		List<Programacao> result = programacaoMusicalService.getProgramacaoAtivaByAmbiente( ambiente );

		JSONListWrapper<Programacao> jsonList = new JSONListWrapper<Programacao>( result, result.size() );
		
		return jsonList;
	}


	
	
	@RequestMapping( value = { 	"/ambientes/{idAmbiente}/programacoes", "/api/ambientes/{idAmbiente}/programacoes" }, method = { RequestMethod.POST }, consumes = "application/json", produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody String saveProgramacao( @PathVariable Long idAmbiente, @RequestBody Programacao programacaoDTO, BindingResult result, HttpServletRequest request )
	{
		String jsonResult = "";
		
		if ( result.hasErrors() )
			jsonResult = writeErrorsAsJSONErroMessage( result );
		else
		{
			Ambiente ambiente = null;
			try
			{
				ambiente = ambienteRepo.findOne( idAmbiente );
				Programacao prog = programacaoMusicalService.gravaNovaProgramacao( ambiente, programacaoDTO );
					
				jsonResult = writeObjectAsString( prog );
			}
			catch ( Exception e )
			{
				imprimeLogErroAmbiente( "Gravar Programação", ambiente, request, e );

				jsonResult = writeSingleErrorAsJSONErroMessage( "alertArea", e.getMessage() );
			}
		}
		
		return jsonResult;
	}	
	
	
	


	
	@RequestMapping( value = { 	"/ambientes/{idAmbiente}/programacoes/{idProgramacao}/generos", "/api/ambientes/{idAmbiente}/programacoes/{idProgramacao}/generos" }, 
			method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody JSONListWrapper<Genero> getGenerosByProgramacao( @PathVariable Long idAmbiente, @PathVariable Long idProgramacao, HttpServletResponse response )
	{
		Ambiente ambiente = ambienteRepo.findOne( idAmbiente );

		Programacao prog = programacaoRepo.findByAmbienteAndIdProgramacao( ambiente, idProgramacao );
		
		List<Genero> generos = prog.getGeneros();

		JSONListWrapper<Genero> jsonList = new JSONListWrapper<Genero>( generos, generos.size() );
		
		return jsonList;
	}
	
	


	@RequestMapping( value = { 	"/ambientes/{idAmbiente}/programacoes/generos/total", "/api/ambientes/{idAmbiente}/programacoes/generos/total" }, 
			method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody JSONListWrapper<Genero> getGenerosByProgramacaoTotal( @PathVariable Long idAmbiente, HttpServletResponse response )
	{
		Ambiente ambiente = ambienteRepo.findOne( idAmbiente );

		Set<Genero> generos = programacaoMusicalService.getGenerosProgramacaoMusicalTotal( ambiente );

		JSONListWrapper<Genero> jsonList = new JSONListWrapper<Genero>( generos, generos.size() );
		
		return jsonList;
	}



	@RequestMapping( value = { 	"/ambientes/{idAmbiente}/programacoes/generos/total", "/api/ambientes/{idAmbiente}/programacoes/generos/total" }, method = { RequestMethod.POST }, consumes = "application/json", produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody String saveProgramacaoMusicalTotal( @PathVariable Long idAmbiente, @RequestBody GeneroListDTO generoList, Principal principal, HttpServletRequest request )
	{
		String jsonResult = "";
		
		Ambiente ambiente = null;
		try
		{
			ambiente = ambienteRepo.findOne( idAmbiente );
			if ( ambiente == null )
				throw new RuntimeException("Ambiente não encontrado");

			Usuario usuario = usuarioService.getUserByPrincipal( principal );
			
			if ( usuario == null )
				throw new RuntimeException("Usuário não encontrado");
			
			programacaoMusicalService.saveProgramacaoMusicalTotal(ambiente, generoList);
			programacaoMusicalService.geraTransmissao( ambiente );
				
			jsonResult = writeOkResponse();
		}
		catch ( Exception e )
		{
			imprimeLogErroAmbiente( "Programação Total", ambiente, request, e );

			jsonResult = writeSingleErrorAsJSONErroMessage( "alertArea", "Não foi possível gravar : " + e.getMessage() );
		}
		
		return jsonResult;
	}


	



	
	
	@RequestMapping( value = { 	"/ambientes/{idAmbiente}/configuracoes", "/api/ambientes/{idAmbiente}/configuracoes" }, 
			method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody AmbienteConfiguracao getConfiguracoes( @PathVariable Long idAmbiente )
	{
		Ambiente ambiente = ambienteRepo.findOne( idAmbiente );

		AmbienteConfiguracao configuracoes = null;
		
		if ( ambiente != null )
			configuracoes = ambienteConfigRepo.findByAmbiente( ambiente );
		
		if ( configuracoes != null )
			configuracoes.buildView();

		return configuracoes;
	}
	
	
	@RequestMapping( value = { 	"/ambientes/{idAmbiente}/configuracoes", "/api/ambientes/{idAmbiente}/configuracoes" }, method = { RequestMethod.POST }, consumes = "application/json", produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody String saveConfiguracoes( @PathVariable Long idAmbiente, @RequestBody AmbienteConfiguracao ambienteConfiguracao, Principal principal, HttpServletRequest request )
	{
		String jsonResult = "";
		
		Ambiente ambiente = null;
		try
		{
			ambiente = ambienteRepo.findOne( idAmbiente );
			if ( ambiente == null )
				throw new RuntimeException("Ambiente não encontrado");

			Usuario usuario = usuarioService.getUserByPrincipal( principal );
			
			if ( usuario == null )
				throw new RuntimeException("Usuário não encontrado");
			
			AmbienteConfiguracao configuracaoAnterior = ambienteConfigRepo.findByAmbiente( ambiente );

			if ( configuracaoAnterior != null )
			{
				ambienteConfiguracao.setIdAmbConfig( configuracaoAnterior.getIdAmbConfig() );  // apenas garantindo o id do registro existente para que ele faça um update...
				ambienteConfiguracao.setDataCriacao( configuracaoAnterior.getDataCriacao() ); // histórico
				ambienteConfiguracao.setUsuarioCriacao( configuracaoAnterior.getUsuarioCriacao() ); // historico
			}
			else
				ambienteConfiguracao.setUsuarioCriacao( usuario );
			
			ambienteConfiguracao.setDataAlteracao( new Date() );
			ambienteConfiguracao.setUsuarioAlteracao( usuario );
			
			ambienteConfiguracao.setAmbiente( ambiente );

			ambienteConfigRepo.save( ambienteConfiguracao );
				
			jsonResult = writeOkResponse();
		}
		catch ( Exception e )
		{
			imprimeLogErroAmbiente( "Configurações", ambiente, request, e );

			jsonResult = writeSingleErrorAsJSONErroMessage( "alertArea", "Não foi possível gravar : " + e.getMessage() );
		}
		
		return jsonResult;
	}
	
	@RequestMapping( value = { 	"/ambientes/{idAmbiente}/configuracoes/resetpass", "/api/ambientes/{idAmbiente}/configuracoes/resetpass" }, method = { RequestMethod.POST }, consumes = "application/json", produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody String resetPasswordProgMusicalConfiguracao( @PathVariable Long idAmbiente, Principal principal, HttpServletRequest request )
	{
		String jsonResult = "";
		
		Ambiente ambiente = null;
		try
		{
			ambiente = ambienteRepo.findOne( idAmbiente );
			if ( ambiente == null )
				throw new RuntimeException("Ambiente não encontrado");

			Usuario usuario = usuarioService.getUserByPrincipal( principal );
			
			if ( usuario == null )
				throw new RuntimeException("Usuário não encontrado");
			
			AmbienteConfiguracao configuracaoAtual = ambienteConfigRepo.findByAmbiente( ambiente );

			configuracaoAtual.setSenhaProgMusicalPlayer("");

			ambienteConfigRepo.save( configuracaoAtual );
				
			jsonResult = writeOkResponse();
		}
		catch ( Exception e )
		{
			imprimeLogErroAmbiente( "Configurações", ambiente, request, e );

			jsonResult = writeSingleErrorAsJSONErroMessage( "alertArea", "Não foi possível gravar : " + e.getMessage() );
		}
		
		return jsonResult;
	}

	@RequestMapping( value = { 	"/ambientes/{idAmbiente}/expedientes", "/api/ambientes/{idAmbiente}/expedientes" }, method = { RequestMethod.POST }, consumes = "application/json", produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody String saveExpediente( @PathVariable Long idAmbiente, @RequestBody Ambiente ambienteDTO, BindingResult result, HttpServletRequest request )
	{
		String jsonResult = "";
		
		if ( result.hasErrors() )
			jsonResult = writeErrorsAsJSONErroMessage( result );
		else
		{
			try
			{
				Boolean ok = ambienteService.saveExpediente( idAmbiente, ambienteDTO );	
				
				if ( ok )
					jsonResult = writeOkResponse();
				else
					throw new RuntimeException("Não foi possível gravar o Expediente");
			}
			catch ( Exception e )
			{
				imprimeLogErroAmbiente( "Salvar Expediente", idAmbiente, request, e );

				jsonResult = writeSingleErrorAsJSONErroMessage( "alertArea", e.getMessage() );
			}
		}
		
		return jsonResult;
	}	
	
	
	
	@RequestMapping( value = { "/ambientes/{idAmbiente}/blocos", "/api/ambientes/{idAmbiente}/blocos" }, method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody Bloco getBloco( @PathVariable Long idAmbiente, HttpServletResponse response )
	{
		Ambiente ambiente = ambienteRepo.findOne( idAmbiente );
		
		Bloco bloco = ambiente.getBloco();
		
		return bloco;
	}
	
	

	@RequestMapping( value = { 	"/ambientes/{idAmbiente}/blocos", "/api/ambientes/{idAmbiente}/blocos" }, method = { RequestMethod.POST }, consumes = "application/json", produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody String saveBloco( @PathVariable Long idAmbiente, @RequestBody Bloco bloco, BindingResult result, HttpServletRequest request )
	{
		String jsonResult = "";
		
		if ( result.hasErrors() )
			jsonResult = writeErrorsAsJSONErroMessage( result );
		else
		{
			Ambiente ambiente = null;
			try
			{
				ambiente = ambienteRepo.findOne( idAmbiente );

				if ( ambiente == null )
					throw new RuntimeException( "Ambiente não encontrado.");

				ambienteService.saveBloco( bloco, ambiente );
				
				jsonResult = writeOkResponse();
			}
			catch ( Exception e )
			{
				imprimeLogErroAmbiente( "Salvar Bloco", ambiente, request, e );

				jsonResult = writeSingleErrorAsJSONErroMessage( "alertArea", e.getMessage() );
			}
		}
		
		return jsonResult;
	}	
	
	
	
	
	
	
	
	
	// ALTERAR PARA A API NÃO CHAMAR MÉTODO CHAMADOS VIEW
	@RequestMapping( value = { 	"/ambientes/{idAmbiente}/view-logomarca" }, method = { RequestMethod.POST } )
	public String uploadLogomarca( @PathVariable Long idAmbiente, @RequestParam("file") MultipartFile file, Principal principal, Model model, HttpServletRequest request )
	{
		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente() == null )
			return "HTTPerror/404";
		
		Ambiente ambiente = ambienteRepo.findOne( idAmbiente );
		
		if ( ambiente != null )
		{
			model.addAttribute( "idAmbiente", ambiente.getIdAmbiente() );
			model.addAttribute( "nome", ambiente.getNome() );

			model.addAttribute( "func", funcionalidadeRepo.findByCodigo( "logomarca" ) );	

			boolean erro = false;
			
			if ( !file.isEmpty() )
			{
				if ( !mimeImagensSet.contains( file.getContentType() ) )
				{
					model.addAttribute( "error", "O arquivo não é uma imagem." );
					erro = true;
				}
			}
			else
			{
				model.addAttribute( "error", "O arquivo está vazio" );
				erro = true;
			}
			
			
			if ( erro == false )
			{
				try
				{
					ambiente.setLogomarca( file.getBytes() );
					ambiente.setLogomimetype( file.getContentType() );
					
					ambienteRepo.save( ambiente );
					
					model.addAttribute( "success", String.format( "Logomarca \"%s\" enviado com sucesso", file.getOriginalFilename() ) );
				}
				catch ( Exception e )
				{
					imprimeLogErroAmbiente("Upload de Marca", ambiente, request, e);

					model.addAttribute( "error", e.getMessage() );
				}
			}
		}

	    return "ambiente/logomarcas";
	}	

	
	
	@RequestMapping( value = { 	"/ambientes/{idAmbiente}/logomarca", "/api/ambientes/{idAmbiente}/logomarca" }, method = { RequestMethod.GET } )
	public void getLogomarca( @PathVariable Long idAmbiente, Principal principal, HttpServletResponse response, HttpServletRequest request )
	{
		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente() == null )
			throw new ResourceNotFoundException();
		
		Ambiente ambiente = ambienteRepo.findOne( idAmbiente );
		
		if ( ambiente != null && ambiente.getLogomarca() != null && StringUtils.isNotBlank( ambiente.getLogomimetype() ) )
		{
			ByteArrayInputStream bin = new ByteArrayInputStream( ambiente.getLogomarca() );
			
			try
			{
				response.setContentType( ambiente.getLogomimetype() );
				IOUtils.copy( bin, response.getOutputStream() );
			}
			catch ( IOException e )
			{
				imprimeLogErroAmbiente( "Obter Logo", ambiente, request, e );

				throw new ResourceNotFoundException();
			}
			finally
			{
				IOUtils.closeQuietly( bin );
			}
		}
		
	}
	
	
	
	/**
	 * Retorna uma lista com os eventos que estão associados com esse ambiente específico.
	 * 
	 * @param idAmbiente
	 * @param response
	 * @return
	 */
	@RequestMapping( value = { 	"/ambientes/{idAmbiente}/eventos", "/api/ambientes/{idAmbiente}/eventos" }, 
						method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody JSONListWrapper<Evento> getEventos( @PathVariable Long idAmbiente, 
															 @RequestParam(value="pageNumber", required = false) Integer pageNumber,
															 @RequestParam(value="limit", required = false) Integer limit, 
															 HttpServletResponse response )
	{
		Ambiente ambiente = ambienteRepo.findOne( idAmbiente );
		
		Pageable pageable = getPageable( pageNumber, limit, "DESC", "idEvento" ); 
		
		Page<Evento> eventos = eventoRepo.findByAmbiente( ambiente, pageable );

		JSONListWrapper<Evento> jsonList = new JSONListWrapper<Evento>( eventos.getContent(), eventos.getTotalElements() );
		
		return jsonList;
	}
	

	
	@RequestMapping( value = { 	"/ambientes/{idAmbiente}/eventos", "/api/ambientes/{idAmbiente}/eventos" }, method = { RequestMethod.POST }, consumes = "application/json", produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody String saveEvento( @PathVariable Long idAmbiente, @RequestBody Evento eventoDTO, BindingResult result, HttpServletRequest request )
	{
		String jsonResult = "";
		
		if ( result.hasErrors() )
			jsonResult = writeErrorsAsJSONErroMessage( result );
		else
		{
			try
			{
				eventoDTO = ambienteService.saveEvento( idAmbiente, eventoDTO );
								
				if ( eventoDTO != null && eventoDTO.getIdEvento() > 0 )
					jsonResult = writeOkResponse();
				else
					throw new RuntimeException("Não foi possível gravar o Evento");
			}
			catch ( Exception e )
			{
				imprimeLogErroAmbiente( "Salvar Evento", idAmbiente, request, e );

				jsonResult = writeSingleErrorAsJSONErroMessage( "alertArea", e.getMessage() );
			}
		}
		
		return jsonResult;
	}	

	
	@RequestMapping( value = { 	"/ambientes/{idAmbiente}/eventos/{idEvento}/horarios", "/api/ambientes/{idAmbiente}/eventos/{idEvento}/horarios" }, 
				method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody JSONListWrapper<EventoHorario> getEventoHorarios( 
													 @PathVariable Long idAmbiente,
													 @PathVariable Long idEvento, 
													 @RequestParam(value="pageNumber", required = false) Integer pageNumber,
													 @RequestParam(value="limit", required = false) Integer limit, 
													 HttpServletResponse response )
	{
		Ambiente ambiente = ambienteRepo.findOne( idAmbiente );
		
		Evento evento = eventoRepo.findByIdEventoAndAmbiente( idEvento, ambiente );
		
		Pageable pageable = getPageable( pageNumber, limit, "ASC", "hora" );
		
		Page<EventoHorario> horarios = eventoHorarioRepo.findByEvento( evento, pageable );
		
		JSONListWrapper<EventoHorario> jsonList = new JSONListWrapper<EventoHorario>( horarios.getContent(), horarios.getTotalElements() );
		
		return jsonList;
	}
	
	
	
	@RequestMapping( value = { "/ambientes/{idAmbiente}/blocos/exemplo" }, method = RequestMethod.POST, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody String getExemploBloco( @PathVariable Long idAmbiente, @RequestBody Bloco bloco, HttpServletRequest request ){
		
		String jsonResult = "";
		
		Ambiente ambiente = null;
		try
		{
			ambiente = ambienteRepo.findOne( idAmbiente );
			
			if ( ambiente == null )
				throw new RuntimeException("Ambiente não encontrado");
			
			if ( bloco == null )
				throw new RuntimeException("Bloco não encontrado");
			
			List<String> exemplo = programacaoMusicalService.getExemploSequenciaBlocoComMusicas( bloco );
			
			JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
			
			for ( String str : exemplo ){
				jsonArrayBuilder.add(str);
			}

			jsonResult = jsonArrayBuilder.build().toString();
		}
		catch ( Exception e )
		{
			imprimeLogErroAmbiente( "Exemplo Bloco", ambiente, request, e );
			jsonResult = writeSingleErrorAsJSONErroMessage( "alertArea", e.getMessage() );
		}
		
		return jsonResult;
	}
	
	
	@RequestMapping( value = { "/ambientes/funcionalidades/midias", 
							   "/api/ambientes/funcionalidades/midias" }, 
					 method = RequestMethod.GET, 
					 produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody Map<String, Funcionalidade> listFuncionalidadesCategoriasMidias(){
		
		 Map<String, Funcionalidade> result = midiaService.getFuncionalidadesDasCategoriasMidias();
		 
		 return result;
	}
	


	@RequestMapping( value = { 	"/ambientes/{idAmbiente}/programacoes/midias/block", "/api/ambientes/{idAmbiente}/programacoes/midias/block" }, 
			method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody JSONListWrapper<Midia> getMidiasBlock( @PathVariable Long idAmbiente, 
																 @RequestParam( value = "idCategoria") Long idCategoria, 
																 HttpServletResponse response )
	{
		MidiaFilter filter = MidiaFilter.create()
										.setIdAmbiente( idAmbiente )
										.setIdCategoria( idCategoria );

		List<Midia> midias = midiaService.findMidiasCategorias( filter );

		JSONListWrapper<Midia> jsonList = new JSONListWrapper<Midia>( midias, midias.size() );
		
		return jsonList;
	}



	@RequestMapping( value = { 	"/ambientes/{idAmbiente}/programacoes/midias/block", "/api/ambientes/{idAmbiente}/programacoes/midias/block" }, method = { RequestMethod.POST }, consumes = "application/json", produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody String saveMidiasBlock( @PathVariable Long idAmbiente, @RequestBody MidiaListDTO midiaList, Principal principal, HttpServletRequest request )
	{
		String jsonResult = "";
		
		Ambiente ambiente = null;
		try
		{
			ambiente = ambienteRepo.findOne( idAmbiente );
			if ( ambiente == null )
				throw new RuntimeException("Ambiente não encontrado");

			Usuario usuario = usuarioService.getUserByPrincipal( principal );
			
			if ( usuario == null )
				throw new RuntimeException("Usuário não encontrado");
			
			programacaoMusicalService.saveProgramacaoMusicalTotal(ambiente, generoList);
			programacaoMusicalService.geraTransmissao( ambiente );
				
			jsonResult = writeOkResponse();
		}
		catch ( Exception e )
		{
			imprimeLogErroAmbiente( "Programação Total", ambiente, request, e );

			jsonResult = writeSingleErrorAsJSONErroMessage( "alertArea", "Não foi possível gravar : " + e.getMessage() );
		}
		
		return jsonResult;
	}

}
