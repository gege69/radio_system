package br.com.radio.web;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
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

import br.com.radio.dto.cliente.ClienteRelatorioDTO;
import br.com.radio.dto.cliente.ClienteResumoFinanceiroDTO;
import br.com.radio.json.JSONBootstrapGridWrapper;
import br.com.radio.model.Cliente;
import br.com.radio.model.CondicaoComercial;
import br.com.radio.model.TipoTaxa;
import br.com.radio.model.Titulo;
import br.com.radio.model.Usuario;
import br.com.radio.repository.ClienteRepository;
import br.com.radio.repository.CondicaoComercialRepository;
import br.com.radio.repository.TipoTaxaRepository;
import br.com.radio.repository.TituloRepository;
import br.com.radio.service.ClienteService;
import br.com.radio.service.UsuarioService;
import br.com.radio.util.UtilsStr;


@Controller
public class ClienteController extends AbstractController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ClienteRepository clienteRepo;
	
	@Autowired
	private CondicaoComercialRepository ccRepo;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private TituloRepository tituloRepo;
	
	@Autowired
	private TipoTaxaRepository tipoTaxaRepo;
	
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
	
	
	
	@RequestMapping( value = { "/clientes", "/api/clientes" }, method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public @ResponseBody JSONBootstrapGridWrapper<Cliente> listClientes( 
																 @RequestParam(value="search", required=false) String search, 
																 @RequestParam(value="pageNumber", required=false) Integer pageNumber, 
																 @RequestParam(value="limit", required=false) Integer limit,
																 Principal principal )
	{
		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente().getIdCliente() == null )
			return null;
		
		Pageable pageable = getPageable( pageNumber, limit, "asc", "razaosocial" );
		
		Page<Cliente> clientePage = null;
		
		if ( StringUtils.isBlank( UtilsStr.notNull( search ) ) )
			clientePage = clienteRepo.findAll( pageable );
		else
		{
			String razaosocial = "%" + search + "%";
			String nomefantasia = "%" + search + "%";
			String cnpj = "%" + search + "%";

			clientePage = clienteRepo.findByRazaosocialContainingIgnoreCaseOrNomefantasiaContainingIgnoreCaseOrCnpjContaining( pageable, razaosocial, nomefantasia, cnpj );
		}
		
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
				
				cliente  = clienteService.saveCliente( cliente );
				
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
	
	
	
	@RequestMapping( value = { "/clientes/{idCliente}/condicoescomerciais", "/api/clientes/{idCliente}/condicoescomerciais" }, method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody JSONBootstrapGridWrapper<CondicaoComercial> listCondicoesComerciais( @PathVariable Long idCliente,
																@RequestParam(value="pageNumber", required=false) Integer pageNumber, 
																@RequestParam(value="limit", required=false) Integer limit,
																Principal principal )
	{
		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente().getIdCliente() == null )
			return null;

		if ( !isAutorizado( usuario, idCliente ) )
			return null;
		
		Cliente cliente = clienteRepo.findOne( idCliente );
		
		Pageable pageable = getPageable( pageNumber, limit, "desc", "dataAlteracao" );
		
		Page<CondicaoComercial> ccPage = ccRepo.findByCliente( pageable, cliente );
		
		List<CondicaoComercial> list = ccPage.getContent();
		
		
		JSONBootstrapGridWrapper<CondicaoComercial> jsonList = new JSONBootstrapGridWrapper<CondicaoComercial>( list, ccPage.getTotalElements() );

		return jsonList;
	}

	
	
	@RequestMapping( value = { "/clientes/{idCliente}/condicoescomerciais", "/api/clientes/{idCliente}/condicoescomerciais" }, method = { RequestMethod.POST }, consumes = "application/json", produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody String saveCondicaoComercial( @RequestBody @Valid CondicaoComercial condicaoComercialVO, BindingResult result, Principal principal )
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
				
				clienteService.saveCondicaoComercial( usuario, condicaoComercialVO );

				jsonResult = writeObjectAsString( condicaoComercialVO );
			}
			catch ( Exception e )
			{
				e.printStackTrace();
				jsonResult = writeSingleErrorAsJSONErroMessage( "alertArea", e.getMessage() );
			}
		}

		return jsonResult;
	}
	


	@RequestMapping( value = { "/clientes/{idCliente}/condicoescomerciais/{idCondcom}", "/api/clientes/{idCliente}/condicoescomerciais/{idCondcom}" }, method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody CondicaoComercial getCondicaoComercial( @PathVariable Long idCliente, @PathVariable Long idCondcom, Principal principal  )
	{
		Usuario usuario = usuarioService.getUserByPrincipal( principal );

		if ( usuario == null || usuario.getCliente().getIdCliente() == null )
			return null;

		if ( isAutorizado( usuario, idCliente ) ){
			
			CondicaoComercial cc = ccRepo.findOne( idCondcom );
			return cc;
		}
		else
			return null;
	}



	@RequestMapping( value = { "/clientes/{idCliente}/resumo", "/api/clientes/{idCliente}/resumo" }, method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody ClienteResumoFinanceiroDTO getResumo( @PathVariable Long idCliente, Principal principal  )
	{
		Usuario usuario = usuarioService.getUserByPrincipal( principal );

		if ( usuario == null || usuario.getCliente().getIdCliente() == null )
			return null;
		
		Cliente cliente = clienteRepo.findOne( idCliente );

		if ( isAutorizado( usuario, idCliente ) )
		{
			ClienteResumoFinanceiroDTO result = clienteService.getResumoFinanceiro( cliente );
			
			return result;
		}
		else
			return null;
	}
	
	
	
	@RequestMapping( value = { "/clientes/{idCliente}/titulos", "/api/clientes/{idCliente}/titulos" }, method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody JSONBootstrapGridWrapper<Titulo> listTitulos( 
																 @RequestParam(value="pageNumber", required=false) Integer pageNumber, 
																 @RequestParam(value="limit", required=false) Integer limit,
																 Principal principal )
	{
		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente().getIdCliente() == null )
			return null;
		
		Pageable pageable = getPageable( pageNumber, limit, "desc", "dataVencimento" );
		
		Page<Titulo> titulosPage = tituloRepo.findAll( pageable );
		
		JSONBootstrapGridWrapper<Titulo> jsonList = new JSONBootstrapGridWrapper<Titulo>( titulosPage.getContent(), titulosPage.getTotalElements() );

		return jsonList;
	}
	

	
	@RequestMapping(value="/admin/relatorio-clientes", method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public String relatorioClientes( ModelMap model, Principal principal )
	{
		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente() == null )
			return "HTTPerror/404";
		
		Long quantidade = clienteRepo.count();
		
		model.addAttribute( "qtdClientes", quantidade.intValue() );
		
		return "admin/relatorio-clientes";
	}



	
	@RequestMapping( value = { "/admin/clientes/searches/relatorio", "/api/clientes/searches/relatorio" }, method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public @ResponseBody JSONBootstrapGridWrapper<ClienteRelatorioDTO> relatorioClientes( 
																 @RequestParam(value="search", required=false) String search, 
																 @RequestParam(value="pageNumber", required=false) Integer pageNumber, 
																 @RequestParam(value="limit", required=false) Integer limit,
																 Principal principal )
	{
		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente().getIdCliente() == null )
			return null;
		
		Pageable pageable = getPageable( pageNumber, limit, "asc", "razaosocial" );
		
		Page<ClienteRelatorioDTO> clienteRelatorioPage = clienteService.getRelatorioCliente( pageable, search );
		
		JSONBootstrapGridWrapper<ClienteRelatorioDTO> jsonList = new JSONBootstrapGridWrapper<ClienteRelatorioDTO>( clienteRelatorioPage.getContent(), clienteRelatorioPage.getTotalElements() );

		return jsonList;
	}



	@RequestMapping( value = { "/clientes/{idCliente}/tipotaxas", "/api/clientes/{idCliente}/tipotaxas" }, method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody JSONBootstrapGridWrapper<TipoTaxa> listTipoTaxas( 
															     @PathVariable Long idCliente, 
																 @RequestParam(value="pageNumber", required=false) Integer pageNumber, 
																 @RequestParam(value="limit", required=false) Integer limit,
																 Principal principal )
	{
		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente().getIdCliente() == null )
			return null;
			
		if ( isAutorizado( usuario, idCliente ) )
		{
			Pageable pageable = getPageable( pageNumber, limit, "asc", "descricao" );

			Page<TipoTaxa> tipotaxaPage = tipoTaxaRepo.findByAtivoTrue( pageable );
			
			JSONBootstrapGridWrapper<TipoTaxa> jsonList = new JSONBootstrapGridWrapper<TipoTaxa>( tipotaxaPage.getContent(), tipotaxaPage.getTotalElements() );

			return jsonList;
		}
		else
			return null;

	}

}

