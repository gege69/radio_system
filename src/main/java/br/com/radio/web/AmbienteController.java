package br.com.radio.web;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
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

import br.com.radio.dto.GeneroListDTO;
import br.com.radio.enumeration.DiaSemana;
import br.com.radio.exception.ResourceNotFoundException;
import br.com.radio.json.JSONListWrapper;
import br.com.radio.model.Ambiente;
import br.com.radio.model.AmbienteConfiguracao;
import br.com.radio.model.AmbienteGenero;
import br.com.radio.model.Bloco;
import br.com.radio.model.Funcionalidade;
import br.com.radio.model.Genero;
import br.com.radio.model.Programacao;
import br.com.radio.model.Usuario;
import br.com.radio.repository.AmbienteConfiguracaoRepository;
import br.com.radio.repository.AmbienteGeneroRepository;
import br.com.radio.repository.AmbienteRepository;
import br.com.radio.repository.BlocoRepository;
import br.com.radio.repository.FuncionalidadeRepository;
import br.com.radio.repository.ProgramacaoRepository;
import br.com.radio.service.AmbienteService;
import br.com.radio.service.ProgramacaoMusicalService;
import br.com.radio.service.UsuarioService;

@Controller
public class AmbienteController extends AbstractController {

		
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
	// DAOs ==================
	
	
	// Services ==============
	@Autowired
	private AmbienteService ambienteService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ProgramacaoMusicalService programacaoMusicalService;
	// Services ==============

	
	
	
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
	public String espelhar( @PathVariable String idAmbiente, ModelMap model, HttpServletResponse response )
	{
		model.addAttribute( "quantidade", 1 );
		
		return "ambiente/espelhar";
	}
	
	@RequestMapping(value="/ambientes/{idAmbiente}/editar", method=RequestMethod.GET)
	public String editar( @PathVariable Long idAmbiente, ModelMap model, HttpServletResponse response )
	{
		model.addAttribute( "idAmbiente", idAmbiente );
		
		return "ambiente/editar";
	}

	
	@RequestMapping(value="/ambientes/new", method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('INCLUIR_AMB')")
	public String incluir( ModelMap model )
	{
		return "ambiente/incluir";
	}
	
	@RequestMapping(value="/ambientes/administrar" , method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMINISTRAR_AMB')")
	public String administrar( ModelMap model, Principal principal )
	{
		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getEmpresa() == null )
			return null;
		
		Long count = ambienteRepo.countByEmpresa( usuario.getEmpresa() );
		
		model.addAttribute( "qtdAmbientes", count );
		
		
		return "ambiente/administrar";
	}
	
	
	@RequestMapping( value = "/ambientes/{idAmbiente}/expedientes/view", method = RequestMethod.GET )
	public String expedientes( @PathVariable Long idAmbiente, ModelMap model, HttpServletResponse response )
	{
		Ambiente ambiente = ambienteRepo.findOne( idAmbiente );
		
		if ( ambiente != null )
		{
			model.addAttribute( "idAmbiente", ambiente.getIdAmbiente() );
			model.addAttribute( "nome", ambiente.getNome() );
		
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
		
			return "ambiente/logomarcas";
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
	public @ResponseBody String getFuncionalidadesAmbiente( @PathVariable Long idAmbiente, @PathVariable String modo, HttpServletResponse response )
	{
		// Dependendo do modo de operação vai entregar uma lista com mais ou menos opções para serem desenhadas...

		List<Funcionalidade> funcionalidades = funcionalidadeRepo.findAll( new Sort( Sort.Direction.ASC, "ordem" ) );
		
		JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

		funcionalidades.stream().forEach( f -> {
			JsonObject obj = Json.createObjectBuilder()
					.add("url_funcionalidade", String.format( f.getUrl(), idAmbiente ) )
					.add("nome_funcionalidade", f.getNome() )
					.add("icone_funcionalidade", f.getIcone() )
					.build();
			
			jsonArrayBuilder.add(obj);
		});
		
		JsonObject objreturn = Json.createObjectBuilder().add( "rows", jsonArrayBuilder.build() ).build();

		return objreturn.toString();
	}
	
	
	
	@RequestMapping( value = { "/ambientes", "/api/ambientes" }, method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody JSONListWrapper<Ambiente> listAmbiente( @RequestParam(value="pagina", required=false) Integer pagina, 
																 @RequestParam(value="limit", required=false) Integer limit,
																 Principal principal )
	{
		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getEmpresa() == null )
			return null;
		
		Pageable pageable = getPageable( pagina, limit );
			
		Page<Ambiente> ambientePage = ambienteRepo.findByEmpresa( pageable, usuario.getEmpresa() );
		
		JSONListWrapper<Ambiente> jsonList = new JSONListWrapper<Ambiente>(ambientePage.getContent(), ambientePage.getTotalElements() );

		return jsonList;
	}

	
	@RequestMapping( value = { "/ambientes", "/api/ambientes" }, method = { RequestMethod.POST }, consumes = "application/json", produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody String saveAmbiente( @RequestBody @Valid Ambiente ambiente, BindingResult result )
	{
		String jsonResult = null;
		
		if ( result.hasErrors() ){
			
			jsonResult = getErrorsAsJSONErroMessage(result);	
		}
		else{

			try
			{
				ambienteService.saveAmbiente( ambiente );
				
				jsonResult = getOkResponse();
			}
			catch ( Exception e )
			{
				e.printStackTrace();
				jsonResult = getSingleErrorAsJSONErroMessage( "alertArea", e.getMessage() );
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
	public @ResponseBody String saveGeneros( @PathVariable Long idAmbiente, @RequestBody GeneroListDTO generoList, BindingResult result )
	{
		String jsonResult = "";
		
		try
		{
			boolean saved = ambienteService.saveGeneros( idAmbiente, generoList );
				
			if ( saved )
				jsonResult = getOkResponse();
			else
				jsonResult = getSingleErrorAsJSONErroMessage( "alertArea", "Erro ao salvar associação de Gêneros do ambiente" );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			jsonResult = getSingleErrorAsJSONErroMessage( "alertArea", e.getMessage() );
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
	public @ResponseBody String saveProgramacao( @PathVariable Long idAmbiente, @RequestBody Programacao programacaoDTO, BindingResult result )
	{
		String jsonResult = "";
		
		if ( result.hasErrors() )
			jsonResult = getErrorsAsJSONErroMessage( result );
		else
		{
			try
			{
				Ambiente ambiente = ambienteRepo.findOne( idAmbiente );
				
				programacaoMusicalService.saveProgramacao( ambiente, programacaoDTO );
					
				jsonResult = getOkResponse();
			}
			catch ( Exception e )
			{
				e.printStackTrace();
				jsonResult = getSingleErrorAsJSONErroMessage( "alertArea", e.getMessage() );
			}
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
		
		return configuracoes;
	}
	
	
	@RequestMapping( value = { 	"/ambientes/{idAmbiente}/configuracoes", "/api/ambientes/{idAmbiente}/configuracoes" }, method = { RequestMethod.POST }, consumes = "application/json", produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody String saveConfiguracoes( @PathVariable Long idAmbiente, @RequestBody AmbienteConfiguracao ambienteConfiguracao, Principal principal )
	{
		String jsonResult = "";
		
		try
		{
			Ambiente ambiente = ambienteRepo.findOne( idAmbiente );
			
			if ( ambiente == null )
				throw new RuntimeException("Ambiente não encontrado");

			Usuario usuario = usuarioService.getUserByPrincipal( principal );
			
			if ( usuario == null )
				throw new RuntimeException("Usuário não encontrado");
			
			AmbienteConfiguracao configuracaoAnterior = null;
			
			if ( ambienteConfiguracao.getIdAmbConfig() != null && ambienteConfiguracao.getIdAmbConfig() > 0 )
				configuracaoAnterior = ambienteConfigRepo.findOne( ambienteConfiguracao.getIdAmbConfig() );
			
			if ( configuracaoAnterior == null ) // apenas para garantir : verificar se já não existe uma configuração anterior para o ambiente ( mesmo que o ID da config não seja passado )
				configuracaoAnterior = ambiente.getConfiguracao();
			
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
				
			jsonResult = getOkResponse();
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			jsonResult = getSingleErrorAsJSONErroMessage( "alertArea", "Não foi possível gravar : " + e.getMessage() );
		}
		
		return jsonResult;
	}
	
	

	@RequestMapping( value = { 	"/ambientes/{idAmbiente}/expedientes", "/api/ambientes/{idAmbiente}/expedientes" }, method = { RequestMethod.POST }, consumes = "application/json", produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody String saveExpediente( @PathVariable Long idAmbiente, @RequestBody Ambiente ambienteDTO, BindingResult result )
	{
		String jsonResult = "";
		
		if ( result.hasErrors() )
			jsonResult = getErrorsAsJSONErroMessage( result );
		else
		{
			try
			{
				Ambiente ambiente = ambienteRepo.findOne( idAmbiente );
				
				ambiente.setHoraIniExpediente( ambienteDTO.getHoraIniExpediente() );
				ambiente.setHoraFimExpediente( ambienteDTO.getHoraFimExpediente() );
				ambiente.setMinutoIniExpediente( ambienteDTO.getMinutoIniExpediente() );
				ambiente.setMinutoFimExpediente( ambienteDTO.getMinutoFimExpediente() );
					
				ambienteRepo.save( ambiente );
					
				jsonResult = getOkResponse();
			}
			catch ( Exception e )
			{
				e.printStackTrace();
				jsonResult = getSingleErrorAsJSONErroMessage( "alertArea", e.getMessage() );
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
	public @ResponseBody String saveBloco( @PathVariable Long idAmbiente, @RequestBody Bloco bloco, BindingResult result )
	{
		String jsonResult = "";
		
		if ( result.hasErrors() )
			jsonResult = getErrorsAsJSONErroMessage( result );
		else
		{
			try
			{
				Ambiente ambiente = ambienteRepo.findOne( idAmbiente );

				if ( ambiente.getBloco() != null )
					bloco.setIdBloco( ambiente.getBloco().getIdBloco() ); // update
				
				bloco.setAmbiente( ambiente );
				blocoRepo.save( bloco );
				
				jsonResult = getOkResponse();
			}
			catch ( Exception e )
			{
				e.printStackTrace();
				jsonResult = getSingleErrorAsJSONErroMessage( "alertArea", e.getMessage() );
			}
		}
		
		return jsonResult;
	}	
	
	
	
	
	
	
	
	
	// ALTERAR PARA A API NÃO CHAMAR MÉTODO CHAMADOS VIEW
	@RequestMapping( value = { 	"/ambientes/{idAmbiente}/view-logomarca" }, method = { RequestMethod.POST } )
	public String uploadLogomarca( @PathVariable Long idAmbiente, @RequestParam("file") MultipartFile file, Principal principal, Model model )
	{
		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getEmpresa() == null )
			return "HTTPerror/404";
		
		Ambiente ambiente = ambienteRepo.findOne( idAmbiente );
		
		if ( ambiente != null )
		{
			model.addAttribute( "idAmbiente", ambiente.getIdAmbiente() );
			model.addAttribute( "nome", ambiente.getNome() );

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
					e.printStackTrace();

					model.addAttribute( "error", e.getMessage() );
				}
			}
		}

	    return "ambiente/logomarcas";
	}	

	
	
	@RequestMapping( value = { 	"/ambientes/{idAmbiente}/logomarca" }, method = { RequestMethod.GET } )
	public void getLogomarca( @PathVariable Long idAmbiente, Principal principal, HttpServletResponse response )
	{
		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getEmpresa() == null )
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
				e.printStackTrace();
				throw new ResourceNotFoundException();
			}
			finally
			{
				IOUtils.closeQuietly( bin );
			}
		}
		
	}
	
//	
//	@RequestMapping( value = { 	"/ambientes/{idAmbiente}/testeprog" }, method = { RequestMethod.GET } )
//	public @ResponseBody String teste( @PathVariable Long idAmbiente, Model model )
//	{
//		Ambiente ambiente = ambienteRepo.findOne( idAmbiente );
//		
//		Programacao p = new Programacao();
//		
//		Random r = new Random();
//		int mindia = 1;
//		int maxdia = 7;
//
//		int minhora = 0;
//		int maxhora = 23;
//		
//		int minmin = 0;
//		int maxmin = 59;
//		
//		p.setAmbiente( ambienteRepo.findOne( idAmbiente ) );
//		p.setAtivo( true );
//		p.setDiaSemana( DiaSemana.getByIndex( r.nextInt(maxdia-mindia) + mindia ) );
//		
//		p.setHoraInicio( r.nextInt(maxhora-minhora) + minhora );
//		p.setHoraFim( r.nextInt(maxhora-p.getHoraInicio()) + p.getHoraInicio() );
//		
//		
//		p.setMinutoInicio( r.nextInt(maxmin-minmin) + minmin );
//		p.setMinutoFim( r.nextInt(maxmin-p.getMinutoInicio()) + p.getMinutoInicio() );
//		
//		p.setDateTimeInicio( p.getDate( p.getHoraInicio(), p.getMinutoInicio() ) );
//		p.setDateTimeFim( p.getDate( p.getHoraFim(), p.getMinutoFim() ) );
//		
//		programacaoMusicalService.saveProgramacao( ambiente, p );
//		
////		programacaoRepo.save( p );
//		
//		System.out.println(p.toString());
//		
//		return writeObjectAsString( p );
//	}
	
}
