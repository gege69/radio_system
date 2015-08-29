package br.com.radio.web;

import java.security.Principal;

import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.radio.dto.AlterarSenhaDTO;
import br.com.radio.service.IUsuarioService;

/**
 * Esse controller vai refletir o primeiro nível do sistema. A visão do Gerencial. 
 * 
 * Algumas funcionalidades podem ter controles exclusivos ( Ex: Ambiente ) 
 *
 * Existe um nível ainda maior que é o de Administrador que pode ter vários Gerenciadores clientes abaixo dele.
 * 
 * @author pazin
 *
 */
@Controller
public class GerenciadorController extends AbstractController {

	@Autowired
	private IUsuarioService userService;
	
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
	
	@RequestMapping(value="/editar-ambiente/{id}", method=RequestMethod.GET)
	public String editarAmbiente( @PathVariable String id, ModelMap model, HttpServletResponse response )
	{
		model.addAttribute( "id", id );
		
		return "painel/editar-ambiente";
	}
	
	
}
