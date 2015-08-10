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
import br.com.radio.repository.AmbienteDAO;
import br.com.radio.repository.FusoHorarioDAO;

/**
 * Esse controller vai refletir o primeiro nível do sistema. A visão do Gerencial.
 *
 * Existe um nível ainda maior que é o de Administrador que pode ter vários Gerenciadores clientes abaixo dele.
 * 
 * @author pazin
 *
 */
@Controller
@RequestMapping("/gerenciador")
public class GerenciadorController extends AbstractController {

	@Autowired
	private AmbienteDAO ambienteDAO;
	
	@Autowired
	private FusoHorarioDAO fusoDAO;

	
	@RequestMapping(value="/principal", method=RequestMethod.GET)
	public String principal( ModelMap model )
	{
		return "painel/principal";
	}
	
	
	@RequestMapping(value="/fazer")
	public String fazer( ModelMap model )
	{
		return "painel/fazer";
	}


	@RequestMapping(value="/alterar-senha")
	public String alterarSenha( ModelMap model )
	{
		return "painel/alterar-senha";
	}

	
	@RequestMapping(value="/administrar-ambientes")
	public String administrar( ModelMap model )
	{
		return "painel/administrar-ambiente";
	}
	
	@RequestMapping(value="/incluir-ambientes")
	public String cadastro( ModelMap model )
	{
		return "painel/incluir-ambiente";
	}

	
	@RequestMapping(value="/espelhar-ambiente/{id_ambiente_amb}", method=RequestMethod.GET)
	public String espelhar( @PathVariable String id_ambiente_amb, ModelMap model, HttpServletResponse response )
	{
		model.addAttribute( "quantidade", 1 );
		
		return "painel/espelhamento-ambiente";
	}
	
	@RequestMapping(value="/editar-ambiente/{id_ambiente_amb}", method=RequestMethod.GET)
	public String editar( @PathVariable String id_ambiente_amb, ModelMap model, HttpServletResponse response )
	{
		model.addAttribute( "id_ambiente_amb", id_ambiente_amb );
		
		return "painel/editar-ambiente";
	}
	
	
	@RequestMapping(value="/ambientes/{id_ambiente_amb}", method=RequestMethod.GET, produces=APPLICATION_JSON_CHARSET_UTF_8)
	public @ResponseBody Ambiente get( @PathVariable Long id_ambiente_amb, ModelMap model, HttpServletResponse response )
	{
		Ambiente ambiente = ambienteDAO.findById( id_ambiente_amb );
		
		return ambiente;
	}
	
	
	@RequestMapping(value="/ambientes", method=RequestMethod.GET, produces=APPLICATION_JSON_CHARSET_UTF_8)
	public @ResponseBody JSONListWrapper<Ambiente> list( @RequestParam("pagina") int pagina )
	{
		List<Ambiente> ncmList = ambienteDAO.findAll();
		
		int total = ncmList.size();
		
		int start = getStartByPage(pagina);
		
		int limit = getLimitByPage(pagina);
		
		ncmList = this.paginacao(ncmList, start, limit);
		
		JSONListWrapper<Ambiente> jsonList = new JSONListWrapper<Ambiente>(ncmList, total);

		return jsonList;
	}
	
	
	@RequestMapping(value="/ambientes", method={RequestMethod.POST, RequestMethod.PUT} , consumes = "application/json", produces=APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody String save( @RequestBody @Valid Ambiente ambiente, BindingResult result )
	{
		String jsonResult = null;
		
		if ( result.hasErrors() ){
			
			jsonResult = getErrorsAsJSONErroMessage(result);	
		}
		else{

			try
			{
				ambienteDAO.save( ambiente );
				
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
	
	
	@RequestMapping(value="/fusohorarios", method=RequestMethod.GET, produces=APPLICATION_JSON_CHARSET_UTF_8)
	public @ResponseBody JSONListWrapper<FusoHorario> listFusos()
	{
		List<FusoHorario> ncmList = fusoDAO.findAllWithOrderAsc("id_ordercomum_fuh");
		
		int total = ncmList.size();
		
		JSONListWrapper<FusoHorario> jsonList = new JSONListWrapper<FusoHorario>(ncmList, total);

		return jsonList;
	}	
	
	
}
