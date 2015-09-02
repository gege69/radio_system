package br.com.radio.web;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import br.com.radio.model.Ambiente;
import br.com.radio.model.Categoria;
import br.com.radio.repository.AmbienteRepository;
import br.com.radio.repository.CategoriaRepository;

@Controller
public class MidiaController extends AbstractController {

	@Autowired
	private AmbienteRepository ambienteRepo;
	
	@Autowired
	private CategoriaRepository categoriaRepo;
	

	@RequestMapping( value = "/ambientes/{id_ambiente}/view-upload-midia", method = RequestMethod.GET )
	public String viewAmbiente( @PathVariable Long id_ambiente, ModelMap model, HttpServletResponse response )
	{
		Ambiente ambiente = ambienteRepo.findOne( id_ambiente );

		if ( ambiente != null )
		{
			model.addAttribute( "id_ambiente", ambiente.getId_ambiente() );
			model.addAttribute( "nome", ambiente.getNome() );
			
			return "ambiente/view-upload-midia";
		}
		else
			return "HTTPerror/404";
	}
	
	
	@RequestMapping(value="/ambientes/{id_ambiente}/upload/{id_categoria}", method=RequestMethod.POST)
    public @ResponseBody String handleFileUpload(@RequestParam("name") String name, @RequestParam("file") MultipartFile file)
	{
		if ( !file.isEmpty() )
		{
			try
			{
				byte[] bytes = file.getBytes();
				BufferedOutputStream stream = new BufferedOutputStream( new FileOutputStream( new File( name ) ) );
				stream.write( bytes );
				stream.close();
				return "You successfully uploaded " + name + "!";
			}
			catch ( Exception e )
			{
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		}
		else
		{
			return "You failed to upload " + name + " because the file was empty.";
		}
    }
	
	
}
