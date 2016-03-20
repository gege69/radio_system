package br.com.radio.web;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.radio.dto.UsuarioAmbienteDTO;
import br.com.radio.model.Ambiente;
import br.com.radio.model.AmbienteConfiguracao;
import br.com.radio.repository.AmbienteConfiguracaoRepository;
import br.com.radio.repository.AmbienteRepository;
import br.com.radio.service.UsuarioService;

@Controller
public class PlayerWebController extends AbstractController {
	
	private static final Logger logger = Logger.getLogger(PlayerWebController.class);
	
	@Autowired
	private AmbienteRepository ambienteRepo;
	@Autowired
	private AmbienteConfiguracaoRepository ambienteConfigRepo;
	
	@Autowired
	private UsuarioService usuarioService;

	
	@ExceptionHandler(Exception.class)
	public String handleError(HttpServletRequest req, Exception exception) {
		
		logger.error("Request: " + req.getRequestURL() + " raised " + exception);
		
		return "HTTPerror/500";
	}
	
	
	
	@RequestMapping(value = "/player", method = RequestMethod.GET )
	public String player(Principal principal, HttpServletRequest request, ModelMap model ) 
	{
		UsuarioAmbienteDTO usuAmb = usuarioService.getUsuarioAmbienteByPrincipal( null, principal );
		
		Ambiente ambiente = usuAmb.getAmbiente();
		
		if ( ambiente != null )
		{
			model.addAttribute( "idAmbiente", ambiente.getIdAmbiente() );
			model.addAttribute( "nome", ambiente.getNome() );
			
			AmbienteConfiguracao configuracao = ambienteConfigRepo.findByAmbiente( ambiente );
			
			model.addAttribute( "configuracao", configuracao );
		
			return "player-web/player-web";
		}
		else
			return "HTTPerror/404";
	}
	

	
	@RequestMapping( value = "/player/ambientes/{idAmbiente}/player/view" , method = RequestMethod.GET )
	public String playerSimulador( @PathVariable Long idAmbiente, ModelMap model, HttpServletResponse response, Principal principal )
	{
		UsuarioAmbienteDTO usuAmb = usuarioService.getUsuarioAmbienteByPrincipal( idAmbiente, principal );
		
		Ambiente ambiente = usuAmb.getAmbiente();
		
		if ( ambiente != null )
		{
			model.addAttribute( "idAmbiente", ambiente.getIdAmbiente() );
			model.addAttribute( "nome", ambiente.getNome() );
			
			AmbienteConfiguracao configuracao = ambienteConfigRepo.findByAmbiente( ambiente );
			
			model.addAttribute( "configuracao", configuracao );
		
			return "player-web/player-web";
		}
		else
			return "HTTPerror/404";
	}
	
	
	
	@RequestMapping( value = "/player/ambientes/{idAmbiente}/player/chamadaveiculos/view", method = RequestMethod.GET )
	public String chamadaVeiculoModal( @PathVariable Long idAmbiente, ModelMap model, HttpServletResponse response, Principal principal )
	{
		UsuarioAmbienteDTO usuAmb = usuarioService.getUsuarioAmbienteByPrincipal( idAmbiente, principal );
		
		Ambiente ambiente = usuAmb.getAmbiente();
		
		if ( ambiente != null )
		{
			model.addAttribute( "idAmbiente", ambiente.getIdAmbiente() );
			model.addAttribute( "nome", ambiente.getNome() );
		
			return "player-web/modal-chamada-veiculos";
		}
		else
			return "HTTPerror/404";
	}
	
	
	
	@RequestMapping( value = "/player/ambientes/{idAmbiente}/player/chamadainst/view", method = RequestMethod.GET )
	public String chamadaInstantaneaModal( @PathVariable Long idAmbiente, ModelMap model, HttpServletResponse response, Principal principal )
	{
		UsuarioAmbienteDTO usuAmb = usuarioService.getUsuarioAmbienteByPrincipal( idAmbiente, principal );
		
		Ambiente ambiente = usuAmb.getAmbiente();
		
		if ( ambiente != null )
		{
			model.addAttribute( "idAmbiente", ambiente.getIdAmbiente() );
			model.addAttribute( "nome", ambiente.getNome() );
		
			return "player-web/modal-chamada-instantanea";
		}
		else
			return "HTTPerror/404";
	}
	
	
	
	@RequestMapping( value = "/player/ambientes/{idAmbiente}/player/horoscopo/view", method = RequestMethod.GET )
	public String horoscopoModal( @PathVariable Long idAmbiente, ModelMap model, HttpServletResponse response, Principal principal )
	{
		UsuarioAmbienteDTO usuAmb = usuarioService.getUsuarioAmbienteByPrincipal( idAmbiente, principal );
		
		Ambiente ambiente = usuAmb.getAmbiente();
		
		if ( ambiente != null )
		{
			model.addAttribute( "idAmbiente", ambiente.getIdAmbiente() );
			model.addAttribute( "nome", ambiente.getNome() );
		
			return "player-web/modal-horoscopo";
		}
		else
			return "HTTPerror/404";
	}
	
	
	@RequestMapping( value = "/player/ambientes/{idAmbiente}/player/configcomerciais/view", method = RequestMethod.GET )
	public String configComerciaisModal( @PathVariable Long idAmbiente, ModelMap model, HttpServletResponse response, Principal principal )
	{
		UsuarioAmbienteDTO usuAmb = usuarioService.getUsuarioAmbienteByPrincipal( idAmbiente, principal );
		
		Ambiente ambiente = usuAmb.getAmbiente();
		
		if ( ambiente != null )
		{
			model.addAttribute( "idAmbiente", ambiente.getIdAmbiente() );
			model.addAttribute( "nome", ambiente.getNome() );
		
			return "player-web/modal-config-comerciais";
		}
		else
			return "HTTPerror/404";
	}
	
	
	@RequestMapping( value = "/player/ambientes/{idAmbiente}/player/configinst/view", method = RequestMethod.GET )
	public String configInstitucionaisModal( @PathVariable Long idAmbiente, ModelMap model, HttpServletResponse response, Principal principal )
	{
		UsuarioAmbienteDTO usuAmb = usuarioService.getUsuarioAmbienteByPrincipal( idAmbiente, principal );
		
		Ambiente ambiente = usuAmb.getAmbiente();
		
		if ( ambiente != null )
		{
			model.addAttribute( "idAmbiente", ambiente.getIdAmbiente() );
			model.addAttribute( "nome", ambiente.getNome() );
		
			return "player-web/modal-config-institucionais";
		}
		else
			return "HTTPerror/404";
	}
	
	@RequestMapping( value = "/player/ambientes/{idAmbiente}/player/configprogrametes/view", method = RequestMethod.GET )
	public String configProgramentesModal( @PathVariable Long idAmbiente, ModelMap model, HttpServletResponse response, Principal principal )
	{
		UsuarioAmbienteDTO usuAmb = usuarioService.getUsuarioAmbienteByPrincipal( idAmbiente, principal );
		
		Ambiente ambiente = usuAmb.getAmbiente();
		
		if ( ambiente != null )
		{
			model.addAttribute( "idAmbiente", ambiente.getIdAmbiente() );
			model.addAttribute( "nome", ambiente.getNome() );
		
			return "player-web/modal-config-programetes";
		}
		else
			return "HTTPerror/404";
	}
	
	
	@RequestMapping( value = "/player/ambientes/{idAmbiente}/player/generos/view", method = RequestMethod.GET )
	public String generosModal( @PathVariable Long idAmbiente, ModelMap model, HttpServletResponse response, Principal principal )
	{
		UsuarioAmbienteDTO usuAmb = usuarioService.getUsuarioAmbienteByPrincipal( idAmbiente, principal );
		
		Ambiente ambiente = usuAmb.getAmbiente();
		
		if ( ambiente != null )
		{
			model.addAttribute( "idAmbiente", ambiente.getIdAmbiente() );
			model.addAttribute( "nome", ambiente.getNome() );
		
			return "player-web/modal-generos";
		}
		else
			return "HTTPerror/404";
	}
	

	@RequestMapping( value = "/player/ambientes/{idAmbiente}/player/blocos/view", method = RequestMethod.GET )
	public String blocosModal( @PathVariable Long idAmbiente, ModelMap model, HttpServletResponse response, Principal principal )
	{
		UsuarioAmbienteDTO usuAmb = usuarioService.getUsuarioAmbienteByPrincipal( idAmbiente, principal );
		
		Ambiente ambiente = usuAmb.getAmbiente();
		
		if ( ambiente != null )
		{
			model.addAttribute( "idAmbiente", ambiente.getIdAmbiente() );
			model.addAttribute( "nome", ambiente.getNome() );
		
			return "player-web/modal-blocos";
		}
		else
			return "HTTPerror/404";
	}
	
	
	@RequestMapping( value = "/player/ambientes/{idAmbiente}/player/nobreak/view", method = RequestMethod.GET )
	public String nobreakModal( @PathVariable Long idAmbiente, ModelMap model, HttpServletResponse response, Principal principal )
	{
		UsuarioAmbienteDTO usuAmb = usuarioService.getUsuarioAmbienteByPrincipal( idAmbiente, principal );
		
		Ambiente ambiente = usuAmb.getAmbiente();
		
		if ( ambiente != null )
		{
			model.addAttribute( "idAmbiente", ambiente.getIdAmbiente() );
			model.addAttribute( "nome", ambiente.getNome() );
		
			return "player-web/modal-nobreak";
		}
		else
			return "HTTPerror/404";
	}
	
	
	
	@RequestMapping( value = "/player/ambientes/{idAmbiente}/player/downloads/view", method = RequestMethod.GET )
	public String downloadsModal( @PathVariable Long idAmbiente, ModelMap model, HttpServletResponse response, Principal principal )
	{
		UsuarioAmbienteDTO usuAmb = usuarioService.getUsuarioAmbienteByPrincipal( idAmbiente, principal );
		
		Ambiente ambiente = usuAmb.getAmbiente();
		
		if ( ambiente != null )
		{
			model.addAttribute( "idAmbiente", ambiente.getIdAmbiente() );
			model.addAttribute( "nome", ambiente.getNome() );
		
			return "player-web/modal-downloads";
		}
		else
			return "HTTPerror/404";
	}

	
	@RequestMapping( value = "/player/ambientes/{idAmbiente}/player/relatorios/view", method = RequestMethod.GET )
	public String relatoriosModal( @PathVariable Long idAmbiente, ModelMap model, HttpServletResponse response, Principal principal )
	{
		UsuarioAmbienteDTO usuAmb = usuarioService.getUsuarioAmbienteByPrincipal( idAmbiente, principal );
		
		Ambiente ambiente = usuAmb.getAmbiente();
		
		if ( ambiente != null )
		{
			model.addAttribute( "idAmbiente", ambiente.getIdAmbiente() );
			model.addAttribute( "nome", ambiente.getNome() );
		
			return "player-web/modal-relatorios";
		}
		else
			return "HTTPerror/404";
	}

	
	
	@RequestMapping( value = "/player/ambientes/{idAmbiente}/player/atendimento/view", method = RequestMethod.GET )
	public String atendimentoModal( @PathVariable Long idAmbiente, ModelMap model, HttpServletResponse response, Principal principal )
	{
		UsuarioAmbienteDTO usuAmb = usuarioService.getUsuarioAmbienteByPrincipal( idAmbiente, principal );
		
		Ambiente ambiente = usuAmb.getAmbiente();
		
		if ( ambiente != null )
		{
			model.addAttribute( "idAmbiente", ambiente.getIdAmbiente() );
			model.addAttribute( "nome", ambiente.getNome() );
		
			return "player-web/modal-conversas";
		}
		else
			return "HTTPerror/404";
	}
	
	
	@RequestMapping( value = "/player/ambientes/{idAmbiente}/player/conversas/view", method = RequestMethod.GET )
	public String conversasModal( @PathVariable Long idAmbiente, ModelMap model, HttpServletResponse response, Principal principal )
	{
		UsuarioAmbienteDTO usuAmb = usuarioService.getUsuarioAmbienteByPrincipal( idAmbiente, principal );
		
		Ambiente ambiente = usuAmb.getAmbiente();
		
		if ( ambiente != null )
		{
			model.addAttribute( "idAmbiente", ambiente.getIdAmbiente() );
			model.addAttribute( "nome", ambiente.getNome() );
		
			return "mensagens/template-conversas";
		}
		else
			return "HTTPerror/404";
	}
	
}
