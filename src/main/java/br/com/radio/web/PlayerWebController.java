package br.com.radio.web;

import java.security.Principal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
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

import br.com.radio.dto.CadastrarSenhaPlayerDTO;
import br.com.radio.dto.UsuarioAmbienteDTO;
import br.com.radio.model.Ambiente;
import br.com.radio.model.AmbienteConfiguracao;
import br.com.radio.programacaomusical.ProgramacaoMusicalService;
import br.com.radio.repository.AmbienteConfiguracaoRepository;
import br.com.radio.repository.AmbienteRepository;
import br.com.radio.service.UsuarioService;

@Controller
public class PlayerWebController extends AbstractController {
	
	private final Logger logger = Logger.getLogger(PlayerWebController.class);
	
	@Autowired
	private AmbienteRepository ambienteRepo;
	@Autowired
	private AmbienteConfiguracaoRepository ambienteConfigRepo;
	
	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private ProgramacaoMusicalService programacaoMusicalService;

	@Override
	protected Logger getLogger()
	{
		return this.logger;
	}

	
	@RequestMapping(value = "/player", method = RequestMethod.GET )
	public String player(Principal principal, HttpServletRequest request, ModelMap model ) 
	{
		UsuarioAmbienteDTO usuAmb = usuarioService.getUsuarioAmbienteByPrincipal( null, principal );
		
		Ambiente ambiente = usuAmb.getAmbiente();
		
		if ( ambiente == null )
			return "HTTPerror/404";
		
		if ( !ambiente.isAtivo() )
			return "player-web/inativo";
		
		model.addAttribute( "idAmbiente", ambiente.getIdAmbiente() );
		model.addAttribute( "nome", ambiente.getNome() );
		
		AmbienteConfiguracao configuracao = ambienteConfigRepo.findByAmbiente( ambiente );
		
		model.addAttribute( "configuracao", configuracao );
	
		return "player-web/player-web";
	}
	

	
	@RequestMapping( value = "/player/ambientes/{idAmbiente}/player/view" , method = RequestMethod.GET )
	@PreAuthorize("hasAuthority('PLAYER')")
	public String playerSimulador( @PathVariable Long idAmbiente, ModelMap model, HttpServletResponse response, Principal principal )
	{
		UsuarioAmbienteDTO usuAmb = usuarioService.getUsuarioAmbienteByPrincipal( idAmbiente, principal );
		
		Ambiente ambiente = usuAmb.getAmbiente();
		
		if ( ambiente == null )
			return "HTTPerror/404";
		
		if ( !ambiente.isAtivo() )
			return "player-web/inativo";

		model.addAttribute( "idAmbiente", ambiente.getIdAmbiente() );
		model.addAttribute( "nome", ambiente.getNome() );

		if ( usuAmb.isGerenciador() )
			model.addAttribute( "simulacao", true );
		
		AmbienteConfiguracao configuracao = ambienteConfigRepo.findByAmbiente( ambiente );
		
		model.addAttribute( "configuracao", configuracao );
	
		return "player-web/player-web";
	}
	
	
	
	@RequestMapping( value = "/player/ambientes/{idAmbiente}/player/chamadaveiculos/view", method = RequestMethod.GET )
	public String chamadaVeiculoModal( @PathVariable Long idAmbiente, ModelMap model, HttpServletResponse response, Principal principal )
	{
		UsuarioAmbienteDTO usuAmb = usuarioService.getUsuarioAmbienteByPrincipal( idAmbiente, principal );
		
		Ambiente ambiente = usuAmb.getAmbiente();
		
		if ( ambiente == null )
			return "HTTPerror/404";
		
		if ( !ambiente.isAtivo() )
			return "player-web/inativo";

		model.addAttribute( "idAmbiente", ambiente.getIdAmbiente() );
		model.addAttribute( "nome", ambiente.getNome() );
	
		return "player-web/modal-chamada-veiculos";
	}
	
	
	
	@RequestMapping( value = "/player/ambientes/{idAmbiente}/player/chamadainst/view", method = RequestMethod.GET )
	public String chamadaInstantaneaModal( @PathVariable Long idAmbiente, ModelMap model, HttpServletResponse response, Principal principal )
	{
		UsuarioAmbienteDTO usuAmb = usuarioService.getUsuarioAmbienteByPrincipal( idAmbiente, principal );
		
		Ambiente ambiente = usuAmb.getAmbiente();

		if ( ambiente == null )
			return "HTTPerror/404";
		
		if ( !ambiente.isAtivo() )
			return "player-web/inativo";

		model.addAttribute( "idAmbiente", ambiente.getIdAmbiente() );
		model.addAttribute( "nome", ambiente.getNome() );
	
		return "player-web/modal-chamada-instantanea";
	}
	
	
	
	@RequestMapping( value = "/player/ambientes/{idAmbiente}/player/horoscopo/view", method = RequestMethod.GET )
	public String horoscopoModal( @PathVariable Long idAmbiente, ModelMap model, HttpServletResponse response, Principal principal )
	{
		UsuarioAmbienteDTO usuAmb = usuarioService.getUsuarioAmbienteByPrincipal( idAmbiente, principal );
		
		Ambiente ambiente = usuAmb.getAmbiente();

		if ( ambiente == null )
			return "HTTPerror/404";
		
		if ( !ambiente.isAtivo() )
			return "player-web/inativo";

		model.addAttribute( "idAmbiente", ambiente.getIdAmbiente() );
		model.addAttribute( "nome", ambiente.getNome() );
	
		return "player-web/modal-horoscopo";
	}
	
	
	@RequestMapping( value = "/player/ambientes/{idAmbiente}/player/configcomerciais/view", method = RequestMethod.GET )
	public String configComerciaisModal( @PathVariable Long idAmbiente, ModelMap model, HttpServletResponse response, Principal principal )
	{
		UsuarioAmbienteDTO usuAmb = usuarioService.getUsuarioAmbienteByPrincipal( idAmbiente, principal );
		
		Ambiente ambiente = usuAmb.getAmbiente();

		if ( ambiente == null )
			return "HTTPerror/404";
		
		if ( !ambiente.isAtivo() )
			return "player-web/inativo";

		model.addAttribute( "idAmbiente", ambiente.getIdAmbiente() );
		model.addAttribute( "nome", ambiente.getNome() );
	
		return "player-web/modal-config-comerciais";
	}
	
	
	@RequestMapping( value = "/player/ambientes/{idAmbiente}/player/configinst/view", method = RequestMethod.GET )
	public String configInstitucionaisModal( @PathVariable Long idAmbiente, ModelMap model, HttpServletResponse response, Principal principal )
	{
		UsuarioAmbienteDTO usuAmb = usuarioService.getUsuarioAmbienteByPrincipal( idAmbiente, principal );
		
		Ambiente ambiente = usuAmb.getAmbiente();
		
		if ( ambiente == null )
			return "HTTPerror/404";
		
		if ( !ambiente.isAtivo() )
			return "player-web/inativo";

		model.addAttribute( "idAmbiente", ambiente.getIdAmbiente() );
		model.addAttribute( "nome", ambiente.getNome() );
	
		return "player-web/modal-config-institucionais";
	}
	
	@RequestMapping( value = "/player/ambientes/{idAmbiente}/player/configprogrametes/view", method = RequestMethod.GET )
	public String configProgramentesModal( @PathVariable Long idAmbiente, ModelMap model, HttpServletResponse response, Principal principal )
	{
		UsuarioAmbienteDTO usuAmb = usuarioService.getUsuarioAmbienteByPrincipal( idAmbiente, principal );
		
		Ambiente ambiente = usuAmb.getAmbiente();

		if ( ambiente == null )
			return "HTTPerror/404";
		
		if ( !ambiente.isAtivo() )
			return "player-web/inativo";

		model.addAttribute( "idAmbiente", ambiente.getIdAmbiente() );
		model.addAttribute( "nome", ambiente.getNome() );
	
		return "player-web/modal-config-programetes";
	}
	
	
	@RequestMapping( value = "/player/ambientes/{idAmbiente}/player/generos/view", method = RequestMethod.GET )
	public String generosModal( @PathVariable Long idAmbiente, ModelMap model, HttpServletResponse response, Principal principal )
	{
		UsuarioAmbienteDTO usuAmb = usuarioService.getUsuarioAmbienteByPrincipal( idAmbiente, principal );
		
		Ambiente ambiente = usuAmb.getAmbiente();
		
		if ( ambiente == null )
			return "HTTPerror/404";
		
		if ( !ambiente.isAtivo() )
			return "player-web/inativo";

		model.addAttribute( "idAmbiente", ambiente.getIdAmbiente() );
		model.addAttribute( "nome", ambiente.getNome() );
	
		return "player-web/modal-generos";
	}
	

	@RequestMapping( value = "/player/ambientes/{idAmbiente}/player/blocos/view", method = RequestMethod.GET )
	public String blocosModal( @PathVariable Long idAmbiente, ModelMap model, HttpServletResponse response, Principal principal )
	{
		UsuarioAmbienteDTO usuAmb = usuarioService.getUsuarioAmbienteByPrincipal( idAmbiente, principal );
		
		Ambiente ambiente = usuAmb.getAmbiente();

		if ( ambiente == null )
			return "HTTPerror/404";
		
		if ( !ambiente.isAtivo() )
			return "player-web/inativo";

		model.addAttribute( "idAmbiente", ambiente.getIdAmbiente() );
		model.addAttribute( "nome", ambiente.getNome() );
	
		return "player-web/modal-blocos";
	}
	
	
	@RequestMapping( value = "/player/ambientes/{idAmbiente}/player/nobreak/view", method = RequestMethod.GET )
	public String nobreakModal( @PathVariable Long idAmbiente, ModelMap model, HttpServletResponse response, Principal principal )
	{
		UsuarioAmbienteDTO usuAmb = usuarioService.getUsuarioAmbienteByPrincipal( idAmbiente, principal );
		
		Ambiente ambiente = usuAmb.getAmbiente();
		
		if ( ambiente == null )
			return "HTTPerror/404";
		
		if ( !ambiente.isAtivo() )
			return "player-web/inativo";

		model.addAttribute( "idAmbiente", ambiente.getIdAmbiente() );
		model.addAttribute( "nome", ambiente.getNome() );
	
		return "player-web/modal-nobreak";
	}
	
	
	
	@RequestMapping( value = "/player/ambientes/{idAmbiente}/player/downloads/view", method = RequestMethod.GET )
	public String downloadsModal( @PathVariable Long idAmbiente, ModelMap model, HttpServletResponse response, Principal principal )
	{
		UsuarioAmbienteDTO usuAmb = usuarioService.getUsuarioAmbienteByPrincipal( idAmbiente, principal );
		
		Ambiente ambiente = usuAmb.getAmbiente();

		if ( ambiente == null )
			return "HTTPerror/404";
		
		if ( !ambiente.isAtivo() )
			return "player-web/inativo";

		model.addAttribute( "idAmbiente", ambiente.getIdAmbiente() );
		model.addAttribute( "nome", ambiente.getNome() );
	
		return "player-web/modal-downloads";
	}

	
	@RequestMapping( value = "/player/ambientes/{idAmbiente}/player/relatorios/view", method = RequestMethod.GET )
	public String relatoriosModal( @PathVariable Long idAmbiente, ModelMap model, HttpServletResponse response, Principal principal )
	{
		UsuarioAmbienteDTO usuAmb = usuarioService.getUsuarioAmbienteByPrincipal( idAmbiente, principal );
		
		Ambiente ambiente = usuAmb.getAmbiente();
		
		if ( ambiente == null )
			return "HTTPerror/404";
		
		if ( !ambiente.isAtivo() )
			return "player-web/inativo";

		model.addAttribute( "idAmbiente", ambiente.getIdAmbiente() );
		model.addAttribute( "nome", ambiente.getNome() );
	
		return "player-web/modal-relatorios";
	}

	
	
	@RequestMapping( value = "/player/ambientes/{idAmbiente}/player/atendimento/view", method = RequestMethod.GET )
	public String atendimentoModal( @PathVariable Long idAmbiente, ModelMap model, HttpServletResponse response, Principal principal )
	{
		UsuarioAmbienteDTO usuAmb = usuarioService.getUsuarioAmbienteByPrincipal( idAmbiente, principal );
		
		Ambiente ambiente = usuAmb.getAmbiente();

		if ( ambiente == null )
			return "HTTPerror/404";
		
		if ( !ambiente.isAtivo() )
			return "player-web/inativo";

		model.addAttribute( "idAmbiente", ambiente.getIdAmbiente() );
		model.addAttribute( "nome", ambiente.getNome() );
	
		return "player-web/modal-conversas";
	}
	
	
	@RequestMapping( value = "/player/ambientes/{idAmbiente}/player/conversas/view", method = RequestMethod.GET )
	public String conversasModal( @PathVariable Long idAmbiente, ModelMap model, HttpServletResponse response, Principal principal )
	{
		UsuarioAmbienteDTO usuAmb = usuarioService.getUsuarioAmbienteByPrincipal( idAmbiente, principal );
		
		Ambiente ambiente = usuAmb.getAmbiente();

		if ( ambiente == null )
			return "HTTPerror/404";
		
		if ( !ambiente.isAtivo() )
			return "player-web/inativo";

		model.addAttribute( "idAmbiente", ambiente.getIdAmbiente() );
		model.addAttribute( "nome", ambiente.getNome() );
	
		return "mensagens/template-conversas";
	}
	
	


	@RequestMapping( value = { 	"/ambientes/{idAmbiente}/programacoes/autenticar", "/api/ambientes/{idAmbiente}/programacoes/autenticar" }, method = { RequestMethod.POST }, consumes = "application/json", produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody String autenticarProgramacaoTotal( @PathVariable Long idAmbiente, @RequestBody Map<String, String> json, BindingResult result, HttpServletRequest request )
	{
		String jsonResult = "";
		
		if ( result.hasErrors() || json.get( "password" ) == null )
			jsonResult = writeErrorsAsJSONErroMessage( result );
		else
		{
			Ambiente ambiente = null;
			try
			{
				ambiente = ambienteRepo.findOne( idAmbiente );
				boolean ok = programacaoMusicalService.autenticarProgramacaoTotal( ambiente, json.get("password") );
				
				if ( !ok )
					throw new RuntimeException("Senha inválida");
					
				jsonResult = writeOkResponse();
			}
			catch ( Exception e )
			{
				imprimeLogErroAmbiente( "Autenticar alterar Programação Total", ambiente, request, e );

				jsonResult = writeSingleErrorAsJSONErroMessage( "alertArea", e.getMessage() );
			}
		}
		
		return jsonResult;
	}	
	

	@RequestMapping( value = { 	"/ambientes/{idAmbiente}/programacoes/autenticar/new", "/api/ambientes/{idAmbiente}/programacoes/autenticar/new" }, method = { RequestMethod.POST }, consumes = "application/json", produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody String cadastrarAutenticacaoProgramacaoTotal( @PathVariable Long idAmbiente, @RequestBody @Valid CadastrarSenhaPlayerDTO cadastrarSenhaDTO, BindingResult result, HttpServletRequest request )
	{
		String jsonResult = "";
		
		if ( result.hasErrors() )
			jsonResult = writeErrorsAsJSONErroMessage( result );
		else
		{
			Ambiente ambiente = null;
			try
			{
				ambiente = ambienteRepo.findOne( idAmbiente );
				programacaoMusicalService.cadastrarSenhaProgramacaoTotal( ambiente, cadastrarSenhaDTO );
				
				jsonResult = writeOkResponse();
			}
			catch ( Exception e )
			{
				imprimeLogErroAmbiente( "Autenticar alterar Programação Total", ambiente, request, e );

				jsonResult = writeSingleErrorAsJSONErroMessage( "alertArea", e.getMessage() );
			}
		}
		
		return jsonResult;
	}	
	
}
