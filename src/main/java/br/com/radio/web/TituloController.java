package br.com.radio.web;

import java.security.Principal;

import javax.validation.Valid;

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

import br.com.radio.model.Titulo;
import br.com.radio.model.Usuario;
import br.com.radio.repository.TituloRepository;
import br.com.radio.service.ClienteService;
import br.com.radio.service.UsuarioService;



@Controller
public class TituloController extends AbstractController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private TituloRepository tituloRepo;
	
	@Autowired
	private ClienteService clienteService;


	@RequestMapping(value={ "/admin/titulos/new" }, method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public String novoTituloAdmin( 
								@RequestParam(value="idCliente", required=false) Long idCliente, 
								ModelMap model, 
								Principal principal )
	{
		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente() == null )
			return "HTTPerror/404";
	
		if ( idCliente != null ){
			model.addAttribute( "urlVoltarCadastro", "/admin/clientes/"+idCliente+"/view" );
			model.addAttribute( "idCliente", idCliente );
			model.addAttribute( "nomeCliente", usuario.getCliente().getNomefantasia() );
		}
		
		// se não vier idCliente pode voltar para um possível cadastro de títulos geral

		model.addAttribute( "nomePainel", "Painel de Admin" );
		model.addAttribute( "urlVoltarPainel", "/admin/painel" );

		return "titulo/editar-titulo";
	}

	@RequestMapping(value={ "/titulos/new" }, method=RequestMethod.GET)
	public String novoTitulo( 
						@RequestParam(value="idCliente", required=false) Long idCliente, 
						ModelMap model, 
						Principal principal )
	{
		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente() == null )
			return "HTTPerror/404";

		if ( idCliente != null ){
			model.addAttribute( "urlVoltarCadastro", "/clientes/view" );
			model.addAttribute( "idCliente", idCliente );
			model.addAttribute( "nomeCliente", usuario.getCliente().getNomefantasia() );
		}

		model.addAttribute( "nomePainel", "Painel Gerencial" );
		model.addAttribute( "urlVoltarPainel", "/principal" );
		
		return "titulo/editar-titulo";
	}


		//  FALTA FAZER O EDITAR....



	@RequestMapping( value = { "/titulos/{idTitulo}", "/api/titulos/{idTitulo}" }, method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody Titulo getTitulo( @PathVariable Long idTitulo, Principal principal  )
	{
		Usuario usuario = usuarioService.getUserByPrincipal( principal );

		if ( usuario == null || usuario.getCliente().getIdCliente() == null )
			return null;
	
		Titulo titulo = tituloRepo.findOne( idTitulo );

		if ( isAutorizado( usuario, titulo.getCliente().getIdCliente() ) )
			return titulo;
		else
			return null;
	}


	
	
	@RequestMapping( value = { "/titulos", "/api/titulos" }, method = { RequestMethod.POST }, consumes = "application/json", produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody String saveTitulo( @RequestBody @Valid Titulo tituloVO, BindingResult result, Principal principal )
	{
		String jsonResult = null;
		
		if ( result.hasErrors() ){
			
			jsonResult = writeErrorsAsJSONErroMessage(result);	
		}
		else
		{
			try
			{
				Usuario usuario = usuarioService.getUserByPrincipal( principal );
				
				if ( usuario == null || usuario.getCliente().getIdCliente() == null )
					throw new RuntimeException("Usuário não encontrado");
				
				if ( isAutorizado( usuario, usuario.getCliente().getIdCliente() ) )
				{
					Titulo tit = clienteService.saveTitulo( usuario, tituloVO );
					
					jsonResult = writeObjectAsString( tit );
				}
				else
					throw new RuntimeException("Não autorizado a gravar Títulos para esse Cliente");

			}
			catch ( Exception e )
			{
				e.printStackTrace();
				jsonResult = writeSingleErrorAsJSONErroMessage( "alertArea", e.getMessage() );
			}
		}

		return jsonResult;
	}
	


}
