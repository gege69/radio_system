package br.com.radio.web;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.radio.json.JSONListWrapper;
import br.com.radio.model.Ambiente;
import br.com.radio.model.FusoHorario;
import br.com.radio.repository.AmbienteRepository;
import br.com.radio.repository.FusoHorarioRepository;

@Controller
public class AmbienteController extends AbstractController {

		
	@Autowired
	private FusoHorarioRepository fusoRepository;
	
	@Autowired
	private AmbienteRepository ambienteRepository;
	
	
	@RequestMapping( value = { "/ambientes/{id}", "/api/ambientes/{id}" }, method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody Ambiente getAmbiente( @PathVariable Long id, ModelMap model, HttpServletResponse response )
	{
		Ambiente ambiente = ambienteRepository.findOne( id );
		
		return ambiente;
	}
	
	
	@RequestMapping( value = { "/ambientes", "/api/ambientes" }, method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody JSONListWrapper<Ambiente> listAmbiente( @RequestParam("pagina") int pagina )
	{
		List<Ambiente> ncmList = ambienteRepository.findAll();
		
		int total = ncmList.size();
		
		int start = getStartByPage(pagina);
		
		int limit = getLimitByPage(pagina);
		
		ncmList = this.paginacao(ncmList, start, limit);
		
		JSONListWrapper<Ambiente> jsonList = new JSONListWrapper<Ambiente>(ncmList, total);

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
				ambiente = ambienteRepository.saveAndFlush( ambiente );
				
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
	
		
}
