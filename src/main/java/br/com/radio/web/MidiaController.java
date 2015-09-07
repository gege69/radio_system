package br.com.radio.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.json.Json;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.openhft.hashing.LongHashFunction;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.radio.business.MidiaBusiness;
import br.com.radio.json.JSONBootstrapGridWrapper;
import br.com.radio.model.Ambiente;
import br.com.radio.model.Categoria;
import br.com.radio.model.Midia;
import br.com.radio.repository.AmbienteRepository;
import br.com.radio.repository.CategoriaRepository;
import br.com.radio.repository.MidiaRepository;

@Controller
public class MidiaController extends AbstractController {

	@Autowired
	private AmbienteRepository ambienteRepo;
	
	@Autowired
	private CategoriaRepository categoriaRepo;
	
	@Autowired
	private MidiaRepository midiaRepo;
	
	@Autowired
	private MidiaBusiness midiaBusiness;
	
	
	
	@RequestMapping( value = "/ambientes/{id_ambiente}/view-list-upload-midia/{codigo}", method = RequestMethod.GET )
	public String viewAmbiente( @PathVariable Long id_ambiente, @PathVariable String codigo, ModelMap model, HttpServletResponse response )
	{
		Ambiente ambiente = ambienteRepo.findOne( id_ambiente );

		if ( ambiente != null )
		{
			model.addAttribute( "id_ambiente", ambiente.getId_ambiente() );
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
	
	
	
	// Depois fazer a versão AJAX desse upload
	@RequestMapping(value="/ambientes/{id_ambiente}/view-list-upload-midia/{codigo}", method=RequestMethod.POST)
    public String handleFileUpload(
    		@PathVariable Long id_ambiente,
    		@PathVariable String codigo,
    		@RequestParam("file") MultipartFile file, 
    		@RequestParam("categorias[]") Long[] categorias,
    		HttpServletRequest request, 
    		Model model )
	{
		
		Ambiente ambiente = ambienteRepo.findOne( id_ambiente );
		
		if ( ambiente != null )
		{
			model.addAttribute( "id_ambiente", ambiente.getId_ambiente() );
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
					Integer size = midiaBusiness.saveUpload( file, categorias );
					
					model.addAttribute( "success", "Arquivo enviado com sucesso" );
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

	
	
	
	@RequestMapping( value = { "/ambientes/{id_ambiente}/midias-por-categoria/{idCategoria}/", 
							   "/api/ambientes/{id_ambiente}/midias-por-categoria/{idCategoria}/" }, 
					 method = RequestMethod.GET, 
					 produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody JSONBootstrapGridWrapper<Midia> listMidiaByCategoria(
			@PathVariable Long id_ambiente, 
			@PathVariable Long idCategoria,
			@RequestParam(value="pageNumber", required = false) Integer pageNumber,  
			@RequestParam("offset") int offset, 
			@RequestParam("limit") int limit, 
			@RequestParam("order") String order )
	{
		pageNumber = getPageZeroBased( pageNumber );
		
		Pageable pageable = new PageRequest( pageNumber, limit, Sort.Direction.fromStringOrNull( order ), "idMidia" );

		Page<Midia> midiaPage = midiaRepo.findAll( pageable );
		
		JSONBootstrapGridWrapper<Midia> jsonList = new JSONBootstrapGridWrapper<Midia>(midiaPage.getContent(), midiaPage.getTotalElements() );

		return jsonList;
	}
	
	
	
	
	
	
}
