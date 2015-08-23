package br.com.radio.web;

import java.security.Principal;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.radio.dto.AlterarSenhaDTO;
import br.com.radio.json.JSONListWrapper;
import br.com.radio.model.Ambiente;
import br.com.radio.model.FusoHorario;
import br.com.radio.repository.AmbienteRepository;
import br.com.radio.repository.FusoHorarioRepository;
import br.com.radio.service.IUsuarioService;

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
	private FusoHorarioRepository fusoRepository;
	
	@Autowired
	private IUsuarioService userService;
	
	@Autowired
	private AmbienteRepository ambienteRepository;

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


	@RequestMapping(value="/incluir-ambiente")
	@PreAuthorize("hasAuthority('INCLUIR_AMB')")
	public String cadastro( ModelMap model )
	{
		return "painel/incluir-ambiente";
	}
	
	@RequestMapping(value="/administrar-ambiente")
	@PreAuthorize("hasAuthority('ADMINISTRAR_AMB')")
	public String administrar( ModelMap model )
	{
		return "painel/administrar-ambiente";
	}
	
	@RequestMapping(value="/alterar-senha", method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('ALTERAR_SENHA')")
	public String alterarSenha( ModelMap model )
	{
		return "painel/alterar-senha";
	}
	
	
	@RequestMapping(value="/alterar-senha", method=RequestMethod.POST, produces=APPLICATION_JSON_CHARSET_UTF_8)
	@PreAuthorize("hasAuthority('ALTERAR_SENHA')")
	public @ResponseBody String gravaNovaSenha( @RequestBody @Valid AlterarSenhaDTO senhaDTO, BindingResult result, Principal principal )
	{
		String jsonResult = "";

		if ( result.hasErrors() ){
			
			jsonResult = getErrorsAsJSONErroMessage(result);	
		}
		else
		{
			String name = principal.getName(); //get logged in username
			
			try
			{
				userService.changeUserPassword( name, senhaDTO );
			}
			catch ( Exception e )
			{
				jsonResult = getSingleErrorAsJSONErroMessage( "alertArea", e.getMessage() );
			}
			
			if ( StringUtils.isBlank( jsonResult ) )
			{
				JsonObject obj = Json.createObjectBuilder().add("ok", true).build();
				
				jsonResult = obj.toString();
			}
		}
		
		return jsonResult;
	}
	
	
	@RequestMapping(value="/espelhar-ambiente/{id_ambiente_amb}", method=RequestMethod.GET)
	public String espelharAmbiente( @PathVariable String id_ambiente_amb, ModelMap model, HttpServletResponse response )
	{
		model.addAttribute( "quantidade", 1 );
		
		return "painel/espelhamento-ambiente";
	}
	
	@RequestMapping(value="/editar-ambiente/{id_ambiente_amb}", method=RequestMethod.GET)
	public String editarAmbiente( @PathVariable String id_ambiente_amb, ModelMap model, HttpServletResponse response )
	{
		model.addAttribute( "id_ambiente_amb", id_ambiente_amb );
		
		return "painel/editar-ambiente";
	}
	
	
	@RequestMapping(value="/ambientes/{id_ambiente_amb}", method=RequestMethod.GET, produces=APPLICATION_JSON_CHARSET_UTF_8)
	public @ResponseBody Ambiente getAmbiente( @PathVariable Long id_ambiente_amb, ModelMap model, HttpServletResponse response )
	{
		Ambiente ambiente = ambienteRepository.findOne( id_ambiente_amb );
		
		return ambiente;
	}
	
	
	@RequestMapping(value="/ambientes", method=RequestMethod.GET, produces=APPLICATION_JSON_CHARSET_UTF_8)
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
	
	
	@RequestMapping(value="/ambientes", method={RequestMethod.POST, RequestMethod.PUT} , consumes = "application/json", produces=APPLICATION_JSON_CHARSET_UTF_8 )
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
	
	
	@RequestMapping(value="/fusohorarios", method=RequestMethod.GET, produces=APPLICATION_JSON_CHARSET_UTF_8)
	public @ResponseBody JSONListWrapper<FusoHorario> listFusos()
	{
		List<FusoHorario> ncmList = fusoRepository.findAllWithSortByOrderComum();
		
		int total = ncmList.size();
		
		JSONListWrapper<FusoHorario> jsonList = new JSONListWrapper<FusoHorario>(ncmList, total);

		return jsonList;
	}	
	
	
}
