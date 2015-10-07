package br.com.radio.web;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.Date;
import java.util.List;
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
import br.com.radio.exception.ResourceNotFoundException;
import br.com.radio.json.JSONListWrapper;
import br.com.radio.model.Ambiente;
import br.com.radio.model.AmbienteConfiguracao;
import br.com.radio.model.AmbienteGenero;
import br.com.radio.model.Bloco;
import br.com.radio.model.Categoria;
import br.com.radio.model.Funcionalidade;
import br.com.radio.model.FusoHorario;
import br.com.radio.model.Genero;
import br.com.radio.model.Usuario;
import br.com.radio.repository.AmbienteConfiguracaoRepository;
import br.com.radio.repository.AmbienteGeneroRepository;
import br.com.radio.repository.AmbienteRepository;
import br.com.radio.repository.BlocoRepository;
import br.com.radio.repository.CategoriaRepository;
import br.com.radio.repository.FuncionalidadeRepository;
import br.com.radio.repository.FusoHorarioRepository;
import br.com.radio.repository.GeneroRepository;
import br.com.radio.service.AmbienteService;
import br.com.radio.service.UsuarioService;

@Controller
public class AmbienteController extends AbstractController {

		
	// DAOs ==================
	@Autowired
	private FusoHorarioRepository fusoRepo;
	@Autowired
	private AmbienteRepository ambienteRepo;
	@Autowired
	private FuncionalidadeRepository funcionalidadeRepo;
	@Autowired
	private CategoriaRepository categoriaRepo;
	@Autowired
	private GeneroRepository generoRepo;
	@Autowired
	private AmbienteGeneroRepository ambienteGeneroRepo;
	@Autowired
	private AmbienteConfiguracaoRepository ambienteConfigRepo;
	@Autowired
	private BlocoRepository blocoRepo;
	// DAOs ==================
	
	
	// Services ==============
	@Autowired
	private AmbienteService ambienteService;
	
	@Autowired
	private UsuarioService usuarioService;
	// Services ==============

	
	
	
	@RequestMapping( value = "/view-ambiente/{idAmbiente}", method = RequestMethod.GET )
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
		
			return "ambiente/view-ambiente";
		}
		else
			return "HTTPerror/404";
	}
	
	
	@RequestMapping( value = "/ambientes/{idAmbiente}/view-expediente", method = RequestMethod.GET )
	public String viewExpediente( @PathVariable Long idAmbiente, ModelMap model, HttpServletResponse response )
	{
		Ambiente ambiente = ambienteRepo.findOne( idAmbiente );
		
		if ( ambiente != null )
		{
			model.addAttribute( "idAmbiente", ambiente.getIdAmbiente() );
			model.addAttribute( "nome", ambiente.getNome() );
		
			return "ambiente/view-expediente";
		}
		else
			return "HTTPerror/404";
	}
	
	
	@RequestMapping( value = "/ambientes/{idAmbiente}/view-generos", method = RequestMethod.GET )
	public String viewGeneros( @PathVariable Long idAmbiente, ModelMap model, HttpServletResponse response )
	{
		Ambiente ambiente = ambienteRepo.findOne( idAmbiente );
		
		if ( ambiente != null )
		{
			model.addAttribute( "idAmbiente", ambiente.getIdAmbiente() );
			model.addAttribute( "nome", ambiente.getNome() );
		
			return "ambiente/view-generos";
		}
		else
			return "HTTPerror/404";
	}
	
	
	@RequestMapping( value = "/ambientes/{idAmbiente}/view-configuracoes", method = RequestMethod.GET )
	public String viewConfiguracoes( @PathVariable Long idAmbiente, ModelMap model, HttpServletResponse response )
	{
		Ambiente ambiente = ambienteRepo.findOne( idAmbiente );
		
		if ( ambiente != null )
		{
			model.addAttribute( "idAmbiente", ambiente.getIdAmbiente() );
			model.addAttribute( "nome", ambiente.getNome() );
		
			return "ambiente/view-configuracoes";
		}
		else
			return "HTTPerror/404";
	}
	
	
	@RequestMapping( value = "/ambientes/{idAmbiente}/view-bloco", method = RequestMethod.GET )
	public String viewBlocos( @PathVariable Long idAmbiente, ModelMap model, HttpServletResponse response )
	{
		Ambiente ambiente = ambienteRepo.findOne( idAmbiente );
		
		if ( ambiente != null )
		{
			model.addAttribute( "idAmbiente", ambiente.getIdAmbiente() );
			model.addAttribute( "nome", ambiente.getNome() );
		
			return "ambiente/view-bloco";
		}
		else
			return "HTTPerror/404";
	}
	
	
	
	@RequestMapping( value = "/ambientes/{idAmbiente}/view-logomarca", method = RequestMethod.GET )
	public String viewLogomarca( @PathVariable Long idAmbiente, ModelMap model, HttpServletResponse response )
	{
		Ambiente ambiente = ambienteRepo.findOne( idAmbiente );
		
		if ( ambiente != null )
		{
			model.addAttribute( "idAmbiente", ambiente.getIdAmbiente() );
			model.addAttribute( "nome", ambiente.getNome() );
		
			return "ambiente/view-logomarca";
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
	@RequestMapping( value = { 	"/ambientes/generos", "/api/ambientes/generos" }, 
						method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody JSONListWrapper<Genero> getGeneros( HttpServletResponse response )
	{
		List<Genero> generos = generoRepo.findAll();
		
		JSONListWrapper<Genero> jsonList = new JSONListWrapper<Genero>( generos, this.qtd );
		
		return jsonList;
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
	
	
	@RequestMapping( value = { 	"/ambientes/categorias", "/api/ambientes/categorias" }, 
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
	
	

	@RequestMapping( value = { 	"/ambientes/{idAmbiente}/expediente", "/api/ambientes/{idAmbiente}/expediente" }, method = { RequestMethod.POST }, consumes = "application/json", produces = APPLICATION_JSON_CHARSET_UTF_8 )
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
	
	
	
	@RequestMapping( value = { "/ambientes/{idAmbiente}/bloco", "/api/ambientes/{idAmbiente}/bloco" }, method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody Bloco getBloco( @PathVariable Long idAmbiente, HttpServletResponse response )
	{
		Ambiente ambiente = ambienteRepo.findOne( idAmbiente );
		
		Bloco bloco = ambiente.getBloco();
		
		return bloco;
	}
	
	

	@RequestMapping( value = { 	"/ambientes/{idAmbiente}/bloco", "/api/ambientes/{idAmbiente}/bloco" }, method = { RequestMethod.POST }, consumes = "application/json", produces = APPLICATION_JSON_CHARSET_UTF_8 )
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

	    return "ambiente/view-logomarca";
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
	
	
	
}
