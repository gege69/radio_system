package br.com.radio.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.radio.json.JSONBootstrapGridWrapper;
import br.com.radio.model.Cliente;
import br.com.radio.model.Usuario;
import br.com.radio.repository.ClienteRepository;
import br.com.radio.service.AdministradorService;
import br.com.radio.service.UsuarioService;


@Controller
public class ClienteController extends AbstractController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ClienteRepository clienteRepo;
	
	@Autowired
	private AdministradorService adminService;
	
	
	@RequestMapping(value="/admin/clientes/searches", method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public String cadastro( ModelMap model, Principal principal )
	{
		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente() == null )
			return "HTTPerror/404";
		
		Long quantidade = clienteRepo.count();
		
		model.addAttribute( "qtdClientes", quantidade.intValue() );
		
		return "admin/cadastro-clientes";
	}
	
	
	@RequestMapping(value={ "/admin/clientes/new" }, method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public String novoClienteAdmin( ModelMap model, Principal principal )
	{
		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente() == null )
			return "HTTPerror/404";

		return "admin/editar-cliente";
	}

	@RequestMapping(value={ "/clientes/new" }, method=RequestMethod.GET)
	public String novoCliente( ModelMap model, Principal principal )
	{
		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente() == null )
			return "HTTPerror/404";

		return "ambiente/editar-cliente";
	}


	@RequestMapping(value={ "/admin/clientes/{idCliente}/view" }, method=RequestMethod.GET)
	public String editarClienteAdmin( @PathVariable Long idCliente, ModelMap model, Principal principal )
	{
		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente() == null )
			return "HTTPerror/404";
		
		if ( !isAutorizado( usuario, idCliente ) )
			return "HTTPerror/404";
		else
		{
			Cliente cliente = clienteRepo.findOne( idCliente );
			
			model.addAttribute( "idCliente", cliente.getIdCliente() );

			return "admin/editar-cliente";
		}
	}

	@RequestMapping(value={ "/clientes/{idCliente}/view" }, method=RequestMethod.GET)
	public String editarCliente( @PathVariable Long idCliente, ModelMap model, Principal principal )
	{
		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente() == null )
			return "HTTPerror/404";
		
		if ( !isAutorizado( usuario, idCliente ) )
			return "HTTPerror/404";
		else
		{
			Cliente cliente = clienteRepo.findOne( idCliente );
			
			model.addAttribute( "idCliente", cliente.getIdCliente() );

			return "ambiente/editar-cliente";
		}
	}
	
	
	@RequestMapping(value={ "/clientes/view" }, method=RequestMethod.GET)
	public String editarClienteLogado( ModelMap model, Principal principal )
	{
		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente() == null )
			return "HTTPerror/404";
		
		model.addAttribute( "idCliente", usuario.getCliente().getIdCliente() );

		return "ambiente/editar-cliente";
	}
	
	
	
	// Só pode listar outros clientes se for administrador
	@RequestMapping( value = { "/clientes", "/api/clientes" }, method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public @ResponseBody JSONBootstrapGridWrapper<Cliente> listClientes( @RequestParam(value="pageNumber", required=false) Integer pageNumber, 
																 @RequestParam(value="limit", required=false) Integer limit,
																 Principal principal )
	{
		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente().getIdCliente() == null )
			return null;
		
		Pageable pageable = getPageable( pageNumber, limit, "asc", "razaosocial" );
		
		Page<Cliente> clientePage = clienteRepo.findAll( pageable );
		
		JSONBootstrapGridWrapper<Cliente> jsonList = new JSONBootstrapGridWrapper<Cliente>( clientePage.getContent(), clientePage.getTotalElements() );

		return jsonList;
	}


	private boolean isAutorizado( Usuario usuario, Long idClienteRequest )
	{
		boolean podeEditarOutrosClientes = hasAuthority( "ADM_SISTEMA" );
		boolean clienteDiferente = !idClienteRequest.equals( usuario.getCliente().getIdCliente() );

		return !( clienteDiferente && !podeEditarOutrosClientes );
	}
	
	
	@RequestMapping( value = { "/clientes/{idCliente}", "/api/clientes/{idCliente}" }, method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody Cliente getCliente( @PathVariable Long idCliente, Principal principal  )
	{
		Usuario usuario = usuarioService.getUserByPrincipal( principal );

		if ( usuario == null || usuario.getCliente().getIdCliente() == null )
			return null;

		if ( isAutorizado( usuario, idCliente ) )
			return clienteRepo.findOne( idCliente );
		else
			return null;
	}
	
	
	
	@RequestMapping( value = { "/clientes", "/api/clientes" }, method = { RequestMethod.POST }, consumes = "application/json", produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody String saveCliente( @RequestBody @Valid Cliente cliente, BindingResult result, Principal principal )
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
				
				if ( cliente.getIdCliente() != null && cliente.getIdCliente() > 0 && !isAutorizado( usuario, cliente.getIdCliente() ) )
					throw new RuntimeException("Não é possível alterar o Cliente");
				
				cliente  = adminService.saveCliente( cliente );
				
				jsonResult = writeObjectAsString( cliente );
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

