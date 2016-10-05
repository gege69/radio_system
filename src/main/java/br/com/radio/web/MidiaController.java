package br.com.radio.web;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import br.com.radio.dto.midia.MidiaFilter;
import br.com.radio.json.JSONListWrapper;
import br.com.radio.model.Ambiente;
import br.com.radio.model.Categoria;
import br.com.radio.model.Midia;
import br.com.radio.model.Usuario;
import br.com.radio.repository.AmbienteRepository;
import br.com.radio.repository.CategoriaRepository;
import br.com.radio.repository.FuncionalidadeRepository;
import br.com.radio.repository.MidiaRepository;
import br.com.radio.service.UsuarioService;
import br.com.radio.service.midia.MidiaService;

@Controller
public class MidiaController extends AbstractController {
	
	private final Logger logger = Logger.getLogger( MidiaController.class );

	// DAOs =====================
	@Autowired
	private AmbienteRepository ambienteRepo;

	@Autowired
	private FuncionalidadeRepository funcionalidadeRepo;	

	@Autowired
	private CategoriaRepository categoriaRepo;
	
	@Autowired
	private MidiaRepository midiaRepo;
	// DAOs =====================
	
	
	// Services =================
	@Autowired
	private MidiaService midiaService;
	
	@Autowired
	private UsuarioService usuarioService;
	// Services =================
	
	@RequestMapping( value = "/ambientes/{idAmbiente}/view-upload-midia/{codigo}", method = RequestMethod.GET )
	public String viewUpload( @PathVariable Long idAmbiente, @PathVariable String codigo, ModelMap model, HttpServletResponse response )
	{
		Ambiente ambiente = ambienteRepo.findOne( idAmbiente );

		if ( ambiente != null )
		{
			model.addAttribute( "idAmbiente", ambiente.getIdAmbiente() );
			model.addAttribute( "nome", ambiente.getNome() );
			model.addAttribute( "func", funcionalidadeRepo.findByCodigo(codigo) );	
			
			Categoria categoria = categoriaRepo.findByCodigo( codigo );
			
			if ( categoria != null )
			{
				model.addAttribute( "idCategoria",  categoria.getIdCategoria() );
				model.addAttribute( "nomeCategoria",  categoria.getNome() );
				model.addAttribute( "codigo",  categoria.getCodigo() );
			}
			
			return "ambiente/upload-midia";
		}
		else
			return "HTTPerror/404";
	}


	@Override
	protected Logger getLogger()
	{
		return this.logger;
	}


	@RequestMapping( value = "/ambientes/{idAmbiente}/view-chamada-funcionarios", method = RequestMethod.GET )
	public String viewChamadaFuncionarios( @PathVariable Long idAmbiente, ModelMap model, HttpServletResponse response )
	{
		Ambiente ambiente = ambienteRepo.findOne( idAmbiente );

		if ( ambiente != null )
		{
			model.addAttribute( "idAmbiente", ambiente.getIdAmbiente() );
			model.addAttribute( "nome", ambiente.getNome() );
			model.addAttribute( "func", funcionalidadeRepo.findByCodigo("chamada_func") );	
			
			return "ambiente/chamada-funcionarios";
		}
		else
			return "HTTPerror/404";
	}
	

	
	@RequestMapping(value="/ambientes/{idAmbiente}/view-chamada-funcionarios", method=RequestMethod.POST)
    public String uploadChamadaFuncionarioTela(
    		@PathVariable Long idAmbiente,
    		@RequestParam("codigo") String codigo,
    		@RequestParam("file") MultipartFile file, 
    		@RequestParam("descricao") String descricao,
    		Principal principal, 
    		Model model,
    		HttpServletRequest request)
	{

		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente() == null )
			return "HTTPerror/404";
		
		Ambiente ambiente = ambienteRepo.findOne( idAmbiente );
		
		if ( ambiente != null )
		{
			model.addAttribute( "idAmbiente", ambiente.getIdAmbiente() );
			model.addAttribute( "nome", ambiente.getNome() );
			model.addAttribute( "func", funcionalidadeRepo.findByCodigo("chamada_func"));	

			if ( !file.isEmpty() )
			{
				try
				{
					midiaService.saveUpload( file, codigo, usuario.getCliente(), ambiente, descricao );
					
					model.addAttribute( "success", String.format( "Arquivo \"%s\" enviado com sucesso", file.getOriginalFilename() ) );
				}
				catch ( Exception e )
				{
					imprimeLogErroAmbiente( "Upload Chamada Veículo", ambiente, request, e );
					model.addAttribute( "error", e.getMessage() );
				}
			}
			else
			{
				model.addAttribute( "error", "O arquivo está vazio" );
			}
		}

	    return "ambiente/chamada-funcionarios";
    }
	
	

	
	// Filtra pelo ID da categoria
	@RequestMapping( value = { "/ambientes/{idAmbiente}/midias-por-categoria/{idCategoria}/", 
							   "/api/ambientes/{idAmbiente}/midias-por-categoria/{idCategoria}/" }, 
					 method = RequestMethod.GET, 
					 produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody JSONListWrapper<Midia> listMidiaByCategoria(
																	@PathVariable Long idAmbiente, 
																	@PathVariable Long idCategoria,
																	@RequestParam(value="search", required=false) String search, 
																	@RequestParam(value="pageNumber", required = false) Integer pageNumber,  
																	@RequestParam(value="limit", required = false) Integer limit)
	{
		Pageable pageable = getPageable( pageNumber, limit, "desc", "dataUpload" ); 
		
		MidiaFilter filter = MidiaFilter.create()
								.setIdAmbiente( idAmbiente )
								.setIdCategoria( idCategoria )
								.setSearch( search );
		
		Page<Midia> page = midiaService.filtraMidiasCategorias( pageable, filter );
		
		List<Midia> midias = page.getContent();
		
		JSONListWrapper<Midia> jsonList = new JSONListWrapper<>( midias, page.getTotalElements() );

		return jsonList;
	}



	
	
	// Filtra pela string de Código da Categoria
	@RequestMapping( value = { "/ambientes/{idAmbiente}/midias-por-categoria", 
	   						   "/api/ambientes/{idAmbiente}/midias-por-categoria" }, 
	   			     method = RequestMethod.GET, 
	   			     produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody JSONListWrapper<Midia> listMidiaByCategoriaString(
												@PathVariable Long idAmbiente, 
												@RequestParam("codigo") String codigo,
											    @RequestParam(value="search", required=false) String search, 
												@RequestParam(value="pageNumber", required = false) Integer pageNumber,  
												@RequestParam(value="limit", required = false) Integer limit )
	{
		Pageable pageable = getPageable( pageNumber, limit, "desc", "dataUpload" ); 

		MidiaFilter filter = MidiaFilter.create()
				.setIdAmbiente( idAmbiente )
				.setCodigoCategoria( codigo )
				.setSearch( search );

		Page<Midia> page = midiaService.filtraMidiasCategorias( pageable, filter );

		JSONListWrapper<Midia> jsonList = new JSONListWrapper<>( page.getContent(), page.getTotalElements() );

		return jsonList;
	}

	
	
	
	
	@RequestMapping( value = { "/ambientes/{idAmbiente}/midias/searches/", "/api/ambientes/{idAmbiente}/midias/searches/" }, 
					 method = RequestMethod.GET, 
					 produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody JSONListWrapper<Midia> listMidiaSearches(
																	@PathVariable Long idAmbiente,
																	@RequestParam(value="categorias[]", required=false) Long[] categorias,
																	@RequestParam(value="pageNumber", required = false) Integer pageNumber,  
																	@RequestParam(value="search", required=false) String search, 
																	@RequestParam(value="limit", required = false) Integer limit)
	{
		Ambiente ambiente = ambienteRepo.findOne( idAmbiente );
		
		JSONListWrapper<Midia> jsonList = null;
		
		if ( ambiente != null )
		{
			pageNumber = getPageZeroBased( pageNumber );
			
			Pageable pageable = getPageable( pageNumber, limit, "desc", "dataUpload" ); 
			
			MidiaFilter filter = MidiaFilter.create()
					.setIdAmbiente( idAmbiente )
					.setCategoriaIds( categorias )
					.setSearch( search );

			Page<Midia> page = midiaService.filtraMidiasCategorias( pageable, filter );

			jsonList = new JSONListWrapper<>( page.getContent(), page.getTotalElements() );
		}
		
		return jsonList;
	}
	
	
	
	@RequestMapping( value = "/arquivos", method = RequestMethod.GET)
	public @ResponseBody String testeArquivos()
	{
		midiaService.getNewMusicFromFileSystem();
		
		return writeOkResponse();
	}
	
	
	
	@RequestMapping( value = { "/ambientes/{idAmbiente}/midias/{codigo}/validade", 
							   "/api/ambientes/{idAmbiente}/midias/{codigo}/validade" }, 
					 method = RequestMethod.GET, 
					 produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody JSONListWrapper<Midia> listChamadaInstValidade( @PathVariable Long idAmbiente, @PathVariable String codigo )
	{
		Pageable pageable = getPageable( 0, 1000, "asc", "descricao" ); 

		MidiaFilter filter = MidiaFilter.create()
								.setIdAmbiente( idAmbiente )
								.setCodigoCategoria(codigo)
								.setVerificaValidade( true );
		
		Page<Midia> page = midiaService.filtraMidiasCategorias( pageable, filter );

		JSONListWrapper<Midia> jsonList = new JSONListWrapper<>( page.getContent(), page.getTotalElements() );

		return jsonList;
	}


	
}
