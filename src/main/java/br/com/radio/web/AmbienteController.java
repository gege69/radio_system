package br.com.radio.web;

import java.util.List;
import java.util.stream.Collectors;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.radio.business.AmbienteBusiness;
import br.com.radio.dto.GeneroListDTO;
import br.com.radio.json.JSONListWrapper;
import br.com.radio.model.Ambiente;
import br.com.radio.model.AmbienteGenero;
import br.com.radio.model.Categoria;
import br.com.radio.model.Funcionalidade;
import br.com.radio.model.FusoHorario;
import br.com.radio.model.Genero;
import br.com.radio.repository.AmbienteGeneroRepository;
import br.com.radio.repository.AmbienteRepository;
import br.com.radio.repository.CategoriaRepository;
import br.com.radio.repository.FuncionalidadeRepository;
import br.com.radio.repository.FusoHorarioRepository;
import br.com.radio.repository.GeneroRepository;

@Controller
public class AmbienteController extends AbstractController {

		
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
	private AmbienteBusiness ambienteBusiness;
	
	
	@RequestMapping( value = "/view-ambiente/{id}", method = RequestMethod.GET )
	public String viewAmbiente( @PathVariable Long id, ModelMap model, HttpServletResponse response )
	{
		Ambiente ambiente = ambienteRepo.findOne( id );

		if ( ambiente != null )
		{
			model.addAttribute( "id_ambiente", ambiente.getId_ambiente() );
			model.addAttribute( "nome", ambiente.getNome() );
			model.addAttribute( "urlambiente", ambiente.getUrlambiente() );
			model.addAttribute( "login", ambiente.getLogin() );
		
			return "ambiente/view-ambiente";
		}
		else
			return "HTTPerror/404";
	}
	
	
	@RequestMapping( value = { "/ambientes/{id}", "/api/ambientes/{id}" }, method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody Ambiente getAmbiente( @PathVariable Long id, HttpServletResponse response )
	{
		Ambiente ambiente = ambienteRepo.findOne( id );
		
		return ambiente;
	}
	
	
	@RequestMapping( value = { 	"/ambientes/{id}/funcionalidades/{modo}", 
								"/api/ambientes/{id}/funcionalidades/{modo}" }, 
					 method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody String getFuncionalidadesAmbiente( @PathVariable Long id, @PathVariable String modo, HttpServletResponse response )
	{
		// Dependendo do modo de operação vai entregar uma lista com mais ou menos opções para serem desenhadas...

		List<Funcionalidade> funcionalidades = funcionalidadeRepo.findAll( new Sort( Sort.Direction.ASC, "ordem" ) );
		
		JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

		funcionalidades.stream().forEach( f -> {
			JsonObject obj = Json.createObjectBuilder()
					.add("url_funcionalidade", String.format( f.getUrl(), id ) )
					.add("nome_funcionalidade", f.getNome() )
					.add("icone_funcionalidade", f.getIcone() )
					.build();
			
			jsonArrayBuilder.add(obj);
		});
		
		JsonObject objreturn = Json.createObjectBuilder().add( "data", jsonArrayBuilder.build() ).build();

		return objreturn.toString();
	}
	
	
	
	@RequestMapping( value = { "/ambientes", "/api/ambientes" }, method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody JSONListWrapper<Ambiente> listAmbiente( @RequestParam("pagina") int pagina )
	{
		Pageable pageable = new PageRequest( pagina, this.qtd );
			
		Page<Ambiente> ambientePage = ambienteRepo.findAll( pageable );
		
		JSONListWrapper<Ambiente> jsonList = new JSONListWrapper<Ambiente>(ambientePage.getContent(), this.qtd);

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
				ambiente = ambienteBusiness.saveAmbiente( ambiente );
				
				jsonResult = writeObjectAsString( ambiente );
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
	
	
	
	@RequestMapping( value = "/ambientes/{id_ambiente}/view-generos", method = RequestMethod.GET )
	public String viewGeneros( @PathVariable Long id_ambiente, ModelMap model, HttpServletResponse response )
	{
		Ambiente ambiente = ambienteRepo.findOne( id_ambiente );
		
		if ( ambiente != null )
		{
			model.addAttribute( "id_ambiente", ambiente.getId_ambiente() );
			model.addAttribute( "nome", ambiente.getNome() );
		
			return "ambiente/view-generos";
		}
		else
			return "HTTPerror/404";
	}
	

	@RequestMapping( value = { 	"/ambientes/{id_ambiente}/generos", "/api/ambientes/{id_ambiente}/generos" }, 
						method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody JSONListWrapper<Genero> getGeneros( @PathVariable Long id_ambiente, HttpServletResponse response )
	{
		// Dependendo do modo de operação vai entregar uma lista com mais ou menos opções para serem desenhadas...
		Ambiente ambiente = ambienteRepo.findOne( id_ambiente );
		
		List<AmbienteGenero> ambienteGeneros = ambienteGeneroRepo.findByAmbiente( ambiente );

		List<Genero> generos = generoRepo.findAll();
		
		JSONListWrapper<Genero> jsonList = new JSONListWrapper<Genero>( generos, this.qtd );
		
		return jsonList;
	}
		
	
	
	// DEPOIS MUDAR ISSO... PRA TALVEZ FAZER APENAS 1 REQUISIÇÃO.... 
	@RequestMapping( value = { 	"/ambientes/{id_ambiente}/generos-associacao", "/api/ambientes/{id_ambiente}/generos-associacao" }, 
						method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody JSONListWrapper<Long> getGenerosAssoc( @PathVariable Long id_ambiente, HttpServletResponse response )
	{
		// Dependendo do modo de operação vai entregar uma lista com mais ou menos opções para serem desenhadas...
		Ambiente ambiente = ambienteRepo.findOne( id_ambiente );
		
		List<AmbienteGenero> ambienteGeneros = ambienteGeneroRepo.findByAmbiente( ambiente );

		// Colecionando apenas os IDs dos gêneros que estão associados à esse ambiente
		List<Long> ids = ambienteGeneros.stream().map( ab -> ab.getGenero().getId_genero() ).collect( Collectors.toList() );

		JSONListWrapper<Long> jsonList = new JSONListWrapper<Long>( ids, this.qtd );
		
		return jsonList;
	}
	
	
	
	
	@RequestMapping( value = { 	"/ambientes/{id_ambiente}/generos", "/api/ambientes/{id_ambiente}/generos" }, method = { RequestMethod.POST }, consumes = "application/json", produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody String gravaGenerosAmbiente( @PathVariable Long id_ambiente, @RequestBody GeneroListDTO generoList, BindingResult result )
	{
		
		String jsonResult = "";
		
		try
		{
			boolean saved = ambienteBusiness.saveGeneros( id_ambiente, generoList );
				
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
	public @ResponseBody JSONListWrapper<Categoria> getCategorias( HttpServletResponse response )
	{
		List<Categoria> categorias = categoriaRepo.findAll();
		
		JSONListWrapper<Categoria> jsonList = new JSONListWrapper<Categoria>( categorias, this.qtd );
		
		return jsonList;
	}
	
	
}
