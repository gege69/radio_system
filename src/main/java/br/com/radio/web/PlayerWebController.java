package br.com.radio.web;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	
	@RequestMapping( value = "/player/ambientes/{idAmbiente}/simulacoes/view", method = RequestMethod.GET )
	public String playerSimulador( @PathVariable Long idAmbiente, ModelMap model, HttpServletResponse response, Principal principal )
	{
		UsuarioAmbienteDTO usuAmb = usuarioService.getUsuarioAmbienteByPrincipal( principal );
		
		Ambiente ambiente = usuAmb.getAmbiente();
		
		if ( ambiente != null )
		{
			model.addAttribute( "idAmbiente", ambiente.getIdAmbiente() );
			model.addAttribute( "nome", ambiente.getNome() );
			
			AmbienteConfiguracao configuracao = ambienteConfigRepo.findByAmbiente( ambiente );
			
			model.addAttribute( "configuracao", configuracao );
		
			return "player-web/player-simulador";
		}
		else
			return "HTTPerror/404";
	}
	
	
	
	@RequestMapping( value = "/player/ambientes/{idAmbiente}/simulacoes/chamadaveiculos/view", method = RequestMethod.GET )
	public String chamadaVeiculoModal( @PathVariable Long idAmbiente, ModelMap model, HttpServletResponse response, Principal principal )
	{
		UsuarioAmbienteDTO usuAmb = usuarioService.getUsuarioAmbienteByPrincipal( principal );
		
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
	
	
	@RequestMapping( value = "/player/ambientes/{idAmbiente}/simulacoes/chamadafuncionarios/view", method = RequestMethod.GET )
	public String chamadaFuncionariosModal( @PathVariable Long idAmbiente, ModelMap model, HttpServletResponse response, Principal principal )
	{
		UsuarioAmbienteDTO usuAmb = usuarioService.getUsuarioAmbienteByPrincipal( principal );
		
		Ambiente ambiente = usuAmb.getAmbiente();
		
		if ( ambiente != null )
		{
			model.addAttribute( "idAmbiente", ambiente.getIdAmbiente() );
			model.addAttribute( "nome", ambiente.getNome() );
		
			return "player-web/modal-chamada-funcionarios";
		}
		else
			return "HTTPerror/404";
	}
	
	
	@RequestMapping( value = "/player/ambientes/{idAmbiente}/simulacoes/chamadainst/view", method = RequestMethod.GET )
	public String chamadaInstantaneaModal( @PathVariable Long idAmbiente, ModelMap model, HttpServletResponse response, Principal principal )
	{
		UsuarioAmbienteDTO usuAmb = usuarioService.getUsuarioAmbienteByPrincipal( principal );
		
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
	
	
	
	@RequestMapping( value = "/player/ambientes/{idAmbiente}/simulacoes/horoscopo/view", method = RequestMethod.GET )
	public String horoscopoModal( @PathVariable Long idAmbiente, ModelMap model, HttpServletResponse response, Principal principal )
	{
		UsuarioAmbienteDTO usuAmb = usuarioService.getUsuarioAmbienteByPrincipal( principal );
		
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
	
	
	@RequestMapping( value = "/player/ambientes/{idAmbiente}/simulacoes/configcomerciais/view", method = RequestMethod.GET )
	public String configComerciaisModal( @PathVariable Long idAmbiente, ModelMap model, HttpServletResponse response, Principal principal )
	{
		UsuarioAmbienteDTO usuAmb = usuarioService.getUsuarioAmbienteByPrincipal( principal );
		
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
	
	
	@RequestMapping( value = "/player/ambientes/{idAmbiente}/simulacoes/configinst/view", method = RequestMethod.GET )
	public String configInstitucionaisModal( @PathVariable Long idAmbiente, ModelMap model, HttpServletResponse response, Principal principal )
	{
		UsuarioAmbienteDTO usuAmb = usuarioService.getUsuarioAmbienteByPrincipal( principal );
		
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
	
	@RequestMapping( value = "/player/ambientes/{idAmbiente}/simulacoes/configprogrametes/view", method = RequestMethod.GET )
	public String configProgramentesModal( @PathVariable Long idAmbiente, ModelMap model, HttpServletResponse response, Principal principal )
	{
		UsuarioAmbienteDTO usuAmb = usuarioService.getUsuarioAmbienteByPrincipal( principal );
		
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
	
	
	@RequestMapping( value = "/player/ambientes/{idAmbiente}/simulacoes/generos/view", method = RequestMethod.GET )
	public String generosModal( @PathVariable Long idAmbiente, ModelMap model, HttpServletResponse response, Principal principal )
	{
		UsuarioAmbienteDTO usuAmb = usuarioService.getUsuarioAmbienteByPrincipal( principal );
		
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
	

	@RequestMapping( value = "/player/ambientes/{idAmbiente}/simulacoes/blocos/view", method = RequestMethod.GET )
	public String blocosModal( @PathVariable Long idAmbiente, ModelMap model, HttpServletResponse response, Principal principal )
	{
		UsuarioAmbienteDTO usuAmb = usuarioService.getUsuarioAmbienteByPrincipal( principal );
		
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
	
	
	@RequestMapping( value = "/player/ambientes/{idAmbiente}/simulacoes/nobreak/view", method = RequestMethod.GET )
	public String nobreakModal( @PathVariable Long idAmbiente, ModelMap model, HttpServletResponse response, Principal principal )
	{
		UsuarioAmbienteDTO usuAmb = usuarioService.getUsuarioAmbienteByPrincipal( principal );
		
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
	
	
	
	@RequestMapping( value = "/player/ambientes/{idAmbiente}/simulacoes/downloads/view", method = RequestMethod.GET )
	public String downloadsModal( @PathVariable Long idAmbiente, ModelMap model, HttpServletResponse response, Principal principal )
	{
		UsuarioAmbienteDTO usuAmb = usuarioService.getUsuarioAmbienteByPrincipal( principal );
		
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

	
	@RequestMapping( value = "/player/ambientes/{idAmbiente}/simulacoes/relatorios/view", method = RequestMethod.GET )
	public String relatoriosModal( @PathVariable Long idAmbiente, ModelMap model, HttpServletResponse response, Principal principal )
	{
		UsuarioAmbienteDTO usuAmb = usuarioService.getUsuarioAmbienteByPrincipal( principal );
		
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

	
	
	@RequestMapping( value = "/player/ambientes/{idAmbiente}/simulacoes/atendimento/view", method = RequestMethod.GET )
	public String atendimentoModal( @PathVariable Long idAmbiente, ModelMap model, HttpServletResponse response, Principal principal )
	{
		UsuarioAmbienteDTO usuAmb = usuarioService.getUsuarioAmbienteByPrincipal( principal );
		
		Ambiente ambiente = usuAmb.getAmbiente();
		
		if ( ambiente != null )
		{
			model.addAttribute( "idAmbiente", ambiente.getIdAmbiente() );
			model.addAttribute( "nome", ambiente.getNome() );
		
			return "player-web/modal-atendimento";
		}
		else
			return "HTTPerror/404";
	}

}
