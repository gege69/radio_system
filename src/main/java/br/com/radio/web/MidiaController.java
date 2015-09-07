package br.com.radio.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.zip.Adler32;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

import javax.json.Json;
import javax.servlet.http.HttpServletResponse;

import net.openhft.hashing.LongHashFunction;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
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
import br.com.radio.repository.AmbienteRepository;
import br.com.radio.repository.CategoriaRepository;
import br.com.radio.repository.MidiaRepository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.Mp3File;

@Controller
public class MidiaController extends AbstractController {

	@Autowired
	private AmbienteRepository ambienteRepo;
	
	@Autowired
	private CategoriaRepository categoriaRepo;
	
	@Autowired
	private MidiaRepository midiaRepo;
	
	
	
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
				model.addAttribute( "id_categoria",  categoria.getId_categoria() );
			}
			
			return "ambiente/view-list-upload-midia";
		}
		else
			return "HTTPerror/404";
	}
	
	
	
	
	
	@RequestMapping( value = "/ambientes/{id_ambiente}/view-upload-midia", method = RequestMethod.GET )
	public String viewUpload( @PathVariable Long id_ambiente, ModelMap model, HttpServletResponse response )
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
	
	
	@RequestMapping( value = { "/ambientes/{id_ambiente}/midias-por-categoria/{id_categoria}/", 
							   "/api/ambientes/{id_ambiente}/midias-por-categoria/{id_categoria}/" }, 
					 method = RequestMethod.GET, 
					 produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody JSONBootstrapGridWrapper<Midia> listMidiaByCategoria(
			@PathVariable Long id_ambiente, 
			@PathVariable Long id_categoria,
			@RequestParam(value="pageNumber", required = false) Integer pageNumber,  
			@RequestParam("offset") int offset, 
			@RequestParam("limit") int limit, 
			@RequestParam("order") String order )
	{
		pageNumber = getPageZeroBased( pageNumber );
		
		Pageable pageable = new PageRequest( pageNumber, limit, Sort.Direction.fromStringOrNull( order ), "idMidia" );

		Page<Midia> midiaPage = midiaRepo.findAll( pageable );
		
		JSONBootstrapGridWrapper<Midia> jsonList = new JSONBootstrapGridWrapper<Midia>(midiaPage.getContent(), limit);

		return jsonList;
	}
	
	
	
	public static void main(String[] aaaa)
	{
		String x = "[\"teste\", \"doido\", \"opa\"]";
		
		try
		{
			File arquivo = new File( "/home/pazin/teste/doido.mp3" );

			FileInputStream fs = new FileInputStream( arquivo );
			
			byte[] bytes = IOUtils.toByteArray( fs );

			LongHashFunction l = LongHashFunction.xx_r39();
			
			long hashXX = l.hashBytes( bytes, 0, bytes.length );

			System.out.println( hashXX );
			
			FileUtils.moveFile( arquivo, new File( "/home/pazin/teste/" + Long.toString( hashXX ) + ".mp3" ) );
			
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}
		
		
	}
	
	
	
	@RequestMapping(value="/ambientes/{id_ambiente}/upload", method=RequestMethod.POST)
    public @ResponseBody String handleFileUpload(
    		@PathVariable Long id_ambiente,
    		@RequestParam("file") MultipartFile file, 
    		@RequestParam("categorias") String categorias)
	{
		if ( !file.isEmpty() )
		{
			try
			{
				String basePath = "/home/pazin/teste/";
				
				byte[] bytes = file.getBytes();

				LongHashFunction l = LongHashFunction.xx_r39();
				long hashXX = l.hashBytes( bytes, 0, bytes.length );

				String path = basePath + file.getOriginalFilename();
				
				File arquivo = new File( path );

				Integer size = IOUtils.copy( file.getInputStream(), new FileOutputStream( arquivo ) );
				
				Midia midia = new Midia();
				
				midia.setDataUpload( new Date() );
				midia.setNome( file.getOriginalFilename() );
				midia.setFilepath( path );
				midia.setFilehash( Long.toString( hashXX ) );
				midia.setMimetype( "audio/mpeg" );  // file.getContentType()
				midia.setFilesize( size );
				midia.setExtensao( FilenameUtils.getExtension( file.getOriginalFilename() ) );
				midia.setValido( true );
				midia.setCached( false );

				Mp3File mp3File = new Mp3File( arquivo );
				
				if ( mp3File.hasId3v2Tag() )
				{
					ID3v2 id3v2Tag;
					
					id3v2Tag = mp3File.getId3v2Tag();
					
					midia.setTitle( id3v2Tag.getTitle() );
					midia.setArtist( id3v2Tag.getArtist() );
					midia.setAlbum( id3v2Tag.getAlbum() );
					midia.setComment( id3v2Tag.getComment() );
					midia.setDatetag( id3v2Tag.getYear() );
					midia.setGenre( id3v2Tag.getGenreDescription() );
				}
				else if ( mp3File.hasId3v1Tag() )
				{
					ID3v1 id3v1Tag;
					
					id3v1Tag = mp3File.getId3v1Tag();
					
					midia.setTitle( id3v1Tag.getTitle() );
					midia.setArtist( id3v1Tag.getArtist() );
					midia.setAlbum( id3v1Tag.getAlbum() );
					midia.setComment( id3v1Tag.getComment() );
					midia.setDatetag( id3v1Tag.getYear() );
					midia.setGenre( id3v1Tag.getGenreDescription() );
				}

				

				if ( StringUtils.isNotBlank( categorias ) )
				{
					ObjectMapper mapper = new ObjectMapper();

//					@SuppressWarnings( "unchecked" )
//					List<String> lista = mapper.readValue( categorias, List.class );
//					
//					System.out.println( size + " " + lista );
//
//					if ( lista != null && lista.size() > 0 )
//					{
//						List<Categoria> categoriaList = categoriaRepo.findByCodigoIn( lista );
//						
//						midia.setCategorias( categoriaList );
//					}
				}
				midiaRepo.save( midia );
				
				return Json.createObjectBuilder().add("ok", 1 ).add( "msg", "arquivo gravado " + size ).build().toString();
			}
			catch ( Exception e )
			{
				return getSingleErrorAsJSONErroMessage( "alertArea", e.getMessage() );
			}
		}
		else
		{
			return getSingleErrorAsJSONErroMessage( "alertArea", "You failed the upload. The file was empty." );
		}
    }
	
	
	
	
	
}
