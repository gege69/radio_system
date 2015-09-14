package br.com.radio.web;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import br.com.radio.json.JSONBootstrapGridWrapper;
import br.com.radio.model.Ambiente;
import br.com.radio.model.Categoria;
import br.com.radio.model.Midia;
import br.com.radio.model.Usuario;
import br.com.radio.repository.AmbienteRepository;
import br.com.radio.repository.CategoriaRepository;
import br.com.radio.repository.MidiaRepository;
import br.com.radio.service.MidiaService;
import br.com.radio.service.UsuarioService;

@Controller
public class MidiaController extends AbstractController {

	// DAOs =====================
	@Autowired
	private AmbienteRepository ambienteRepo;
	
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
	
	
	@RequestMapping( value = "/ambientes/{idAmbiente}/view-list-upload-midia/{codigo}", method = RequestMethod.GET )
	public String viewListUpload( @PathVariable Long idAmbiente, @PathVariable String codigo, ModelMap model, HttpServletResponse response )
	{
		Ambiente ambiente = ambienteRepo.findOne( idAmbiente );

		if ( ambiente != null )
		{
			model.addAttribute( "idAmbiente", ambiente.getIdAmbiente() );
			model.addAttribute( "nome", ambiente.getNome() );
			
			Categoria categoria = categoriaRepo.findByCodigo( codigo );
			
			if ( categoria != null )
			{
				model.addAttribute( "idCategoria",  categoria.getIdCategoria() );
				model.addAttribute( "nomeCategoria",  categoria.getNome() );
				model.addAttribute( "codigo",  categoria.getCodigo() );
			}
			
			return "ambiente/view-list-upload-midia";
		}
		else
			return "HTTPerror/404";
	}
	
	
	
	// AJAX NÃO É SUPORTADO POR IE8... FAZER UMA VERSÃO PARA A API QUE NÃO REDIRECIONA PRA TELA...
	@RequestMapping(value="/ambientes/{idAmbiente}/view-list-upload-midia/{codigo}", method=RequestMethod.POST)
    public String uploadSync(
    		@PathVariable Long idAmbiente,
    		@PathVariable String codigo,
    		@RequestParam("file") MultipartFile file, 
    		@RequestParam("categorias[]") Long[] categorias,
    		Principal principal, 
    		Model model )
	{

		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getEmpresa() == null )
			return "HTTPerror/404";
		
		Ambiente ambiente = ambienteRepo.findOne( idAmbiente );
		
		if ( ambiente != null )
		{
			model.addAttribute( "idAmbiente", ambiente.getIdAmbiente() );
			model.addAttribute( "nome", ambiente.getNome() );

			Categoria categoria = categoriaRepo.findByCodigo( codigo );
			
			if ( categoria != null )
			{
				model.addAttribute( "idCategoria",  categoria.getIdCategoria() );
				model.addAttribute( "nomeCategoria",  categoria.getNome() );
				model.addAttribute( "codigo",  categoria.getCodigo() );
			}
			
			if ( !file.isEmpty() )
			{
				try
				{
					midiaService.saveUpload( file, categorias, usuario.getEmpresa(), ambiente );
					
					model.addAttribute( "success", String.format( "Arquivo \"%s\" enviado com sucesso", file.getOriginalFilename() ) );
				}
				catch ( Exception e )
				{
					e.printStackTrace();
					
					model.addAttribute( "error", e.getMessage() );
				}
			}
			else
			{
				model.addAttribute( "error", "O arquivo está vazio" );
			}
		}

	    return "ambiente/view-list-upload-midia";
    }

	
	
	
	
	// AJAX NÃO É SUPORTADO POR IE8 ... FAZER UMA VERSÃO QUE NÃO REDIRECIONA PRA TELA...
	@RequestMapping(value="/view-upload-multi", method=RequestMethod.POST)
    public String uploadMultiSync(
    		@RequestParam("file") MultipartFile file,
    		@RequestParam("ambientes[]") Long[] ambientes,    		
    		@RequestParam("categorias[]") Long[] categorias,
    		Principal principal, 
    		Model model )
	{

		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getEmpresa() == null )
			return "HTTPerror/404";
		
		if ( !file.isEmpty() )
		{
			try
			{
				midiaService.saveUploadMulti( file, categorias, usuario.getEmpresa(), ambientes );
				
				model.addAttribute( "success", String.format( "Arquivo \"%s\" enviado com sucesso", file.getOriginalFilename() ) );
			}
			catch ( Exception e )
			{
				e.printStackTrace();
				
				model.addAttribute( "error", e.getMessage() );
			}
		}
		else
		{
			model.addAttribute( "error", "O arquivo está vazio" );
		}

	    return "gerenciador/view-upload-multi";
    }
	
	

	
	@RequestMapping( value = { "/ambientes/{idAmbiente}/midias-por-categoria/{idCategoria}/", 
							   "/api/ambientes/{idAmbiente}/midias-por-categoria/{idCategoria}/" }, 
					 method = RequestMethod.GET, 
					 produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody JSONBootstrapGridWrapper<Midia> listMidiaByCategoria(
																	@PathVariable Long idAmbiente, 
																	@PathVariable Long idCategoria,
																	@RequestParam(value="pageNumber", required = false) Integer pageNumber,  
																	@RequestParam(value="limit", required = false) Integer limit, 
																	@RequestParam(value="order", required = false) String order )
	{
		Pageable pageable = getPageable( pageNumber, limit, order, "idMidia" ); 
		
		Page<Midia> midiaPage = null;

		Ambiente ambiente = ambienteRepo.findOne( idAmbiente );
		
		if ( idCategoria != null && idCategoria > 0 )
			midiaPage = midiaRepo.findByAmbientesAndCategorias( pageable, ambiente, new Categoria( idCategoria, "" ) );
		else
			midiaPage = midiaRepo.findByAmbientes( pageable, ambiente );
		
		List<Midia> midiaList = midiaPage.getContent();
		
		midiaList.stream().forEach( m -> {
			m.getCategorias().forEach( cat -> {
				m.getCategoriasView().put( cat.getCodigo(), true );
			});
		});
		
		JSONBootstrapGridWrapper<Midia> jsonList = new JSONBootstrapGridWrapper<Midia>(midiaList, midiaPage.getTotalElements() );

		return jsonList;
	}
	
	
	
	
	@RequestMapping( value = "/ambientes/{idAmbiente}/view-pesquisa-midia", method = RequestMethod.GET )
	public String viewPesquisaMidia( @PathVariable Long idAmbiente, ModelMap model, HttpServletResponse response )
	{
		Ambiente ambiente = ambienteRepo.findOne( idAmbiente );

		if ( ambiente != null )
		{
			model.addAttribute( "idAmbiente", ambiente.getIdAmbiente() );
			model.addAttribute( "nome", ambiente.getNome() );
			
			return "ambiente/view-pesquisa-midia";
		}
		else
			return "HTTPerror/404";
	}
	
	
	
	@RequestMapping( value = { "/ambientes/{idAmbiente}/midias/searches/", 
							   "/api/ambientes/{idAmbiente}/midias/searches/" }, 
					 method = RequestMethod.GET, 
					 produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody JSONBootstrapGridWrapper<Midia> listMidiaSearches(
																	@PathVariable Long idAmbiente,
																	@RequestParam(value="nome", required= false) String nome,
																	@RequestParam(value="categorias[]", required=false) Long[] categorias,
																	@RequestParam(value="pageNumber", required = false) Integer pageNumber,  
																	@RequestParam(value="limit", required = false) Integer limit, 
																	@RequestParam(value="order", required = false) String order )
	{
		Ambiente ambiente = ambienteRepo.findOne( idAmbiente );
		
		JSONBootstrapGridWrapper<Midia> jsonList = null;
		
		if ( ambiente != null )
		{
			pageNumber = getPageZeroBased( pageNumber );
			
			Pageable pageable = new PageRequest( pageNumber, limit, Sort.Direction.fromStringOrNull( order ), "idMidia" );
			
			Page<Midia> midiaPage = null;

			if ( categorias != null && categorias.length > 0 )
				midiaPage = midiaRepo.findByAmbientesAndNomeContainingAndCategoriasIn( ambiente, "%"+nome+"%", Categoria.listByIds( categorias ), pageable );
			else
				midiaPage = midiaRepo.findByAmbientesAndNomeContaining( ambiente, "%"+nome+"%", pageable );
			
			List<Midia> midiaList = midiaPage.getContent();
			
			midiaList.stream().forEach( m -> {
				m.getCategorias().forEach( cat -> {
					m.getCategoriasView().put( cat.getCodigo(), true );
				});
			});
			
			jsonList = new JSONBootstrapGridWrapper<Midia>(midiaList, midiaPage.getTotalElements() );
		}
		
		return jsonList;
	}
	
	
	
	
}
