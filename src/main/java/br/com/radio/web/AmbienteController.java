package br.com.radio.web;

import java.util.List;

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
import br.com.radio.json.JSONListWrapper;
import br.com.radio.model.Ambiente;
import br.com.radio.model.Funcionalidade;
import br.com.radio.model.FusoHorario;
import br.com.radio.model.Genero;
import br.com.radio.repository.AmbienteRepository;
import br.com.radio.repository.FuncionalidadeRepository;
import br.com.radio.repository.FusoHorarioRepository;
import br.com.radio.repository.GeneroRepository;

@Controller
public class AmbienteController extends AbstractController {

		
	@Autowired
	private FusoHorarioRepository fusoRepository;
	
	@Autowired
	private AmbienteRepository ambienteRepository;
	
	@Autowired
	private FuncionalidadeRepository funcionalidadeRepository;
	
	@Autowired
	private GeneroRepository generoRepository;
	
	@Autowired
	private AmbienteBusiness ambienteBusiness;
	
	
	@RequestMapping( value = "/view-ambiente/{id}", method = RequestMethod.GET )
	public String viewAmbiente( @PathVariable Long id, ModelMap model, HttpServletResponse response )
	{
		Ambiente ambiente = ambienteRepository.findOne( id );

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
		Ambiente ambiente = ambienteRepository.findOne( id );
		
		return ambiente;
	}
	
	
	@RequestMapping( value = { 	"/ambientes/{id}/funcionalidades/{modo}", 
								"/api/ambientes/{id}/funcionalidades/{modo}" }, 
					 method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody String getFuncionalidadesAmbiente( @PathVariable Long id, @PathVariable String modo, HttpServletResponse response )
	{
		// Dependendo do modo de operação vai entregar uma lista com mais ou menos opções para serem desenhadas...

		List<Funcionalidade> funcionalidades = funcionalidadeRepository.findAll( new Sort( Sort.Direction.ASC, "ordem" ) );
		
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
			
		Page<Ambiente> ambientePage = ambienteRepository.findAll( pageable );
		
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
		List<FusoHorario> ncmList = fusoRepository.findAllWithSortByOrderComum();
		
		int total = ncmList.size();
		
		JSONListWrapper<FusoHorario> jsonList = new JSONListWrapper<FusoHorario>(ncmList, total);

		return jsonList;
	}	
	
	
	
	@RequestMapping( value = "/ambientes/{id_ambiente}/view-generos", method = RequestMethod.GET )
	public String viewGeneros( @PathVariable Long id_ambiente, ModelMap model, HttpServletResponse response )
	{
		Ambiente ambiente = ambienteRepository.findOne( id_ambiente );
		
		// Ainda precisa fazer a tabela de ligação

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
	public @ResponseBody JSONListWrapper<Genero> getGenerosAmbiente( @PathVariable Long id_ambiente, HttpServletResponse response )
	{
		// Dependendo do modo de operação vai entregar uma lista com mais ou menos opções para serem desenhadas...

		List<Genero> generos = generoRepository.findAll();
		
//		Page<Genero> generos = generoRepository.findAll(new PageRequest( 0, 3 ) );

		JSONListWrapper<Genero> jsonList = new JSONListWrapper<Genero>( generos, this.qtd );
		
		return jsonList;
	}
		
	
}
