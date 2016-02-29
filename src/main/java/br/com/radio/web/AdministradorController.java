package br.com.radio.web;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import br.com.radio.json.JSONBootstrapGridWrapper;
import br.com.radio.json.JSONListWrapper;
import br.com.radio.model.Cliente;
import br.com.radio.model.Genero;
import br.com.radio.model.Usuario;
import br.com.radio.repository.ClienteRepository;
import br.com.radio.repository.GeneroRepository;
import br.com.radio.repository.PerfilRepository;
import br.com.radio.service.AdministradorService;
import br.com.radio.service.UsuarioService;


@Controller
public class AdministradorController extends AbstractController {
	
	@Autowired
	private PerfilRepository perfilRepo;

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ClienteRepository clienteRepo;
	
	@Autowired
	private AdministradorService adminService;
	
	@Autowired
	private GeneroRepository generoRepo;
	
	
	@RequestMapping(value="/admin/painel", method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public String principal( ModelMap model, Principal principal )
	{
		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente() == null )
			return "HTTPerror/404";
		
		return "admin/painel";
	}


	@RequestMapping(value="/admin/clientes/view", method=RequestMethod.GET)
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
	public String novoCliente( ModelMap model, Principal principal )
	{
		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente() == null )
			return "HTTPerror/404";

		return "admin/editar-cliente";
	}


	@RequestMapping(value={ "/admin/clientes/{idCliente}/view" }, method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public String editarCliente( @PathVariable Long idCliente, ModelMap model, Principal principal )
	{
		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente() == null )
			return "HTTPerror/404";
		
		Cliente cliente = clienteRepo.findOne( idCliente );
		
		model.addAttribute( "idCliente", cliente.getIdCliente() );

		return "admin/editar-cliente";
	}
	
	
	@RequestMapping( value = { "/admin/clientes", "/api/admin/clientes" }, method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
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
	
	
	@RequestMapping( value = { "/admin/clientes/{idCliente}", "/api/admin/clientes/{idCliente}" }, method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public @ResponseBody Cliente getCliente( @PathVariable Long idCliente )
	{
		Cliente cliente = clienteRepo.findOne( idCliente );

		return cliente;
	}
	
	
	
	@RequestMapping( value = { "/admin/clientes", "/api/admin/clientes" }, method = { RequestMethod.POST }, consumes = "application/json", produces = APPLICATION_JSON_CHARSET_UTF_8 )
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
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
	


	@RequestMapping(value="/admin/generos/view", method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public String generos( ModelMap model, Principal principal )
	{
		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente() == null )
			return "HTTPerror/404";
		
		Long quantidade = generoRepo.count();
		
		model.addAttribute( "qtdGeneros", quantidade.intValue() );
		
		return "admin/cadastro-generos";
	}
	

	
	
	@RequestMapping( value = { 	"/admin/generos", "/api/admin/generos" }, 
						method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public @ResponseBody JSONListWrapper<Genero> getGeneros(@RequestParam(value="pageNumber", required=false) Integer pageNumber, 
															@RequestParam(value="limit", required=false) Integer limit )
	{
		Pageable pageable = getPageable( pageNumber, limit, "asc", "nome" );

		Page<Genero> generos = generoRepo.findAllByOrderByNome( pageable );
		
		JSONListWrapper<Genero> jsonList = new JSONListWrapper<Genero>( generos.getContent(), generos.getTotalElements() );
		
		return jsonList;
	}
	
	
	@RequestMapping( value = { "/admin/generos/{idGenero}", "/api/admin/generos/{idGenero}" }, method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public @ResponseBody Genero getGeneros( @PathVariable Long idGenero )
	{
		Genero genero = generoRepo.findOne( idGenero );

		return genero;
	}
	
	
	@RequestMapping( value = { "/admin/generos", "/api/admin/generos" }, method = { RequestMethod.POST }, consumes = "application/json", produces = APPLICATION_JSON_CHARSET_UTF_8 )
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public @ResponseBody String saveGenero( @RequestBody @Valid Genero genero, BindingResult result, Principal principal )
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
				
				generoRepo.save( genero );

				jsonResult = writeObjectAsString( genero );
			}
			catch ( Exception e )
			{
				e.printStackTrace();
				jsonResult = writeSingleErrorAsJSONErroMessage( "alertArea", e.getMessage() );
			}
		}

		return jsonResult;
	}
	
	
	@RequestMapping(value={ "/admin/generos/new" }, method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public String novoGenero( ModelMap model, Principal principal )
	{
		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente() == null )
			return "HTTPerror/404";

		return "admin/editar-genero";
	}


	@RequestMapping(value={ "/admin/generos/{idGenero}/view" }, method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public String editarGenero( @PathVariable Long idGenero, ModelMap model, Principal principal )
	{
		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente() == null )
			return "HTTPerror/404";
		
		Genero genero = generoRepo.findOne( idGenero );
		
		model.addAttribute( "idGenero", genero.getIdGenero() );

		return "admin/editar-genero";
	}	
}

