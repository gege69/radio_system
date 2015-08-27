package br.com.radio.web;

import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import br.com.radio.dto.UserDTO;
import br.com.radio.model.Usuario;
import br.com.radio.service.IUsuarioService;

@Controller
public class AuthenticationController extends AbstractController {

	@Autowired
	private IUsuarioService userService;
	
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String home( HttpServletRequest request, ModelMap model )
	{
		return "auth/login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login( HttpServletRequest request, ModelMap model )
	{
		CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        if (csrfToken != null) {
            model.addAttribute("csrfParameterName", csrfToken.getParameterName());
            model.addAttribute("csrfToken", csrfToken.getToken());
        }
		return "auth/login";
	}
	
	@RequestMapping(value="/api/legal", method=RequestMethod.GET)
	public @ResponseBody String api( HttpServletRequest request, ModelMap model )
	{
		JsonObject obj = Json.createObjectBuilder().add("ok", "true").build();
		
		return obj.toString(); 
	}
	
	@RequestMapping(value="/api/start", method=RequestMethod.GET)
	public @ResponseBody String apistart( HttpServletRequest request, ModelMap model )
	{
		JsonObject obj = Json.createObjectBuilder().add("ok", "true").build();
		
		return obj.toString(); 
	}
	
	
	@RequestMapping(value="/api/startpost", method=RequestMethod.POST)
	public @ResponseBody String apistartpost( HttpServletRequest request, ModelMap model )
	{
		JsonObject obj = Json.createObjectBuilder().add("funfou", "true").build();
		
		return obj.toString(); 
	}
	
	
	@RequestMapping(value="/403", method=RequestMethod.GET)
	public String acessoNegado( HttpServletRequest request, ModelMap model )
	{
		return "HTTPerror/403";
	}

	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String registro( HttpServletRequest request, ModelMap model )
	{
		UserDTO userDto = new UserDTO();
	    model.addAttribute("user", userDto);
    
		return "auth/register";	
	}
	
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ModelAndView registrar( @ModelAttribute("user") @Valid UserDTO accountDto, BindingResult result, WebRequest request, Errors errors )
	{
		Usuario usuario = null;
		
		ModelAndView modelAndView = new ModelAndView();
		
		String msgErro = "";
		
		// Importante proteger contra brute force aqui....
		if ( !result.hasErrors() )
		{
			try
			{
				usuario = userService.registerNewUserAccount( accountDto );				
			}
			catch ( Exception e )
			{
				msgErro = e.getMessage();
			}
		}
		
		if ( usuario == null )
		{
			modelAndView.setViewName( "auth/register" );
			result.reject( "message.regError", msgErro );
		}
		else
		{
			modelAndView.setViewName( "auth/register-success" );
			modelAndView.addObject( "username", usuario.getNome() );
		}
		
		return modelAndView;
	}
	
}
