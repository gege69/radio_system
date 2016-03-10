package br.com.radio.web;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import br.com.radio.json.JSONBootstrapGridWrapper;
import br.com.radio.json.JSONListWrapper;
import br.com.radio.model.Categoria;
import br.com.radio.model.Cliente;
import br.com.radio.model.Genero;
import br.com.radio.model.Midia;
import br.com.radio.model.Usuario;
import br.com.radio.repository.CategoriaRepository;
import br.com.radio.repository.ClienteRepository;
import br.com.radio.repository.GeneroRepository;
import br.com.radio.repository.MidiaRepository;
import br.com.radio.repository.PerfilRepository;
import br.com.radio.service.AdministradorService;
import br.com.radio.service.MidiaService;
import br.com.radio.service.UsuarioService;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;


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
	
	@Autowired
	private MidiaRepository midiaRepo;

	@Autowired
	private MidiaService midiaService;

	@Autowired
	private CategoriaRepository categoriaRepo;
	
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
	
	
	
	@RequestMapping(value="/admin/upload-painel/view", method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public String uploadPainel( ModelMap model, Principal principal )
	{
		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente() == null )
			return "HTTPerror/404";
		
		return "admin/upload-painel";
	}
	
	
	
	
	@RequestMapping(value="/admin/upload-chamadas-veiculos/view", method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public String uploadChamadasVeiculos( ModelMap model, Principal principal )
	{
		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente() == null )
			return "HTTPerror/404";
		
		return "admin/upload-chamadas-veiculos";
	}
	
	
	@RequestMapping(value="/admin/upload-letras/view", method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public String uploadLetras( ModelMap model, Principal principal )
	{
		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente() == null )
			return "HTTPerror/404";
		
		return "admin/upload-chamadas-veiculos-alfanum";
	}
	
	
	@RequestMapping(value="/admin/upload-musica/view", method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public String uploadMusica( ModelMap model, Principal principal )
	{
		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente() == null )
			return "HTTPerror/404";
		
		return "admin/upload-musica";
	}
	
	
	@RequestMapping(value="/admin/upload-horoscopo/view", method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public String uploadHoroscopo( ModelMap model, Principal principal )
	{
		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente() == null )
			return "HTTPerror/404";
		
		return "admin/upload-horoscopo";
	}
	


	@RequestMapping( value = { "/admin/midias", "/api/admin/midias" }, method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody JSONBootstrapGridWrapper<Midia> listMidiaByCategoria(
																	@RequestParam(value="codigo", required = false) String codigo,  
																	@RequestParam(value="pageNumber", required = false) Integer pageNumber,  
																	@RequestParam(value="limit", required = false) Integer limit, 
																	@RequestParam(value="order", required = false) String order )
	{
		Pageable pageable = getPageable( pageNumber, limit, order, "nome" ); 
		
		List<Categoria> categorias = new ArrayList<Categoria>();

		if ( StringUtils.isNotBlank( codigo ) )
			categorias.add( categoriaRepo.findByCodigo( codigo ) );
		else
		{
			String[] cats = new String[] { Categoria.VEIC_PLACA_LETRA, Categoria.VEIC_PLACA_NUMERO };
			categorias = categoriaRepo.findByCodigoIn( Arrays.asList( cats ) );
		}
		
		if ( categorias == null )
			throw new RuntimeException("Categoria não encontrada");
		
		Page<Midia> page = midiaRepo.findByCategoriasInAndValidoTrue( pageable, categorias );
		
		JSONBootstrapGridWrapper<Midia> jsonList = new JSONBootstrapGridWrapper<>( page.getContent(), page.getTotalElements() );

		return jsonList;
	}
	
	
	
	
	@RequestMapping( value = { "/admin/upload-chamadas-veiculos", "/api/admin/upload-chamadas-veiculos" }, method = { RequestMethod.POST },  produces = APPLICATION_JSON_CHARSET_UTF_8 )
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public @ResponseBody String saveUploadChamadasVeiculos( @RequestParam("file") MultipartFile file, 
															@RequestParam("codigo") String codigo, 
															@RequestParam(value="alfanumerico", required=false) String alfaNumerico,
															Principal principal )
	{
		String jsonResult = null;
		
		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente().getIdCliente() == null )
			throw new RuntimeException("Usuário não encontrado");
		
		try
		{
			Midia midia = midiaService.saveUploadChamadaVeiculo( file, codigo, usuario.getCliente(), alfaNumerico );
			
			JsonObjectBuilder builder = Json.createObjectBuilder();
			JsonObjectBuilder builder2 = Json.createObjectBuilder();
			jsonResult = builder.add("files", builder2.add( "name", midia.getNome() ) ).build().toString();
		}
		catch ( FileNotFoundException e )
		{
			e.printStackTrace();
			throw new RuntimeException( e.getMessage() );
		}
		catch ( UnsupportedTagException e )
		{
			e.printStackTrace();
			throw new RuntimeException( e.getMessage() );
		}
		catch ( InvalidDataException e )
		{
			e.printStackTrace();
			throw new RuntimeException( e.getMessage() );
		}
		catch ( IOException e )
		{
			e.printStackTrace();
			throw new RuntimeException( e.getMessage() );
		}

		return jsonResult;
	}	
	
	@RequestMapping( value = { "/admin/chamada-veiculos", "/api/admin/chamada-veiculos" }, method = { RequestMethod.POST }, consumes = "application/json", produces = APPLICATION_JSON_CHARSET_UTF_8 )
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public @ResponseBody String saveNomeUploadChamadaVeiculo( @RequestBody Midia midiaVO, BindingResult result, Principal principal )
	{
		String jsonResult = null;
	
		try
		{
			Usuario usuario = usuarioService.getUserByPrincipal( principal );
			
			if ( usuario == null || usuario.getCliente().getIdCliente() == null )
				throw new RuntimeException("Usuário não encontrado");
			
			midiaService.alteraNomeMidia( midiaVO );
			
			jsonResult = writeOkResponse();
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			jsonResult = writeSingleErrorAsJSONErroMessage( "alertArea", e.getMessage() );
		}

		return jsonResult;
	}
	
	
	@RequestMapping(value= { "/admin/chamada-veiculos/{idMidia}", "/api/admin/chamada-veiculos/{idMidia}" }, method=RequestMethod.DELETE)
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public ResponseEntity<String> deleteChamadaVeiculo( @PathVariable Long idMidia, Principal principal, Model model )
	{
		String jsonResult = "";

		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente() == null )
			return new ResponseEntity<String>( writeSingleErrorAsJSONErroMessage( "alertArea", "Usuário não encontrado ou Cliente não encontrada." ), HttpStatus.INTERNAL_SERVER_ERROR );
		
		try
		{
			midiaService.deleteMidiaSePossivel( idMidia );
			jsonResult = writeOkResponse();
		}
		catch ( Exception e )
		{
			e.printStackTrace();

			jsonResult = writeSingleErrorAsJSONErroMessage( "alertArea", e.getMessage() );
			return new ResponseEntity<String>( jsonResult, HttpStatus.INTERNAL_SERVER_ERROR );
		}

		return new ResponseEntity<String>( jsonResult, HttpStatus.OK );

    }	


	@RequestMapping(value = { "/admin/midia/{idMidia}", "/api/admin/midia/{idMidia}" }, method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public @ResponseBody ResponseEntity<FileSystemResource> downloadMidia(@PathVariable Long idMidia, Principal principal) {
		
		Midia midia = midiaRepo.findOne( idMidia );
		
		if ( midia == null )
			return ResponseEntity.unprocessableEntity().body( null );
		
		File arquivo = new File( midia.getFilepath() );
		
		FileSystemResource fsr = new FileSystemResource( arquivo );

		String name = Integer.valueOf( midia.getNome().hashCode() ).toString();
		
		return ResponseEntity.ok()
				.header( "Content-Disposition", "attachment; filename=\""+ name +"\"" )
				.contentLength( midia.getFilesize() )
				.contentType( MediaType.parseMediaType( midia.getMimetype() ) )
				.body( fsr );
	}

	
	
	@RequestMapping(value={ "/admin/tocat-chamadas-veiculos/view" }, method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public String tocarChamadasVeiculos( ModelMap model, Principal principal )
	{
		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente() == null )
			return "HTTPerror/404";

		return "admin/editar-cliente";
	}
	
	
	
}

