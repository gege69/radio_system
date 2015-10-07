package br.com.radio.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import net.openhft.hashing.LongHashFunction;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.com.radio.json.JSONBootstrapGridWrapper;
import br.com.radio.model.Ambiente;
import br.com.radio.model.Categoria;
import br.com.radio.model.Empresa;
import br.com.radio.model.Midia;
import br.com.radio.model.MidiaAmbiente;
import br.com.radio.model.Parametro;
import br.com.radio.repository.AmbienteRepository;
import br.com.radio.repository.CategoriaRepository;
import br.com.radio.repository.MidiaAmbienteRepository;
import br.com.radio.repository.MidiaRepository;
import br.com.radio.repository.ParametroRepository;

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

@Component
public class MidiaService {

	@Autowired
	private MidiaRepository midiaRepo;
	
	@Autowired
	private CategoriaRepository categoriaRepo;

	@Autowired
	private ParametroRepository parametroRepo;
	
	@Autowired
	private AmbienteRepository ambienteRepo;
	
	@Autowired
	private MidiaAmbienteRepository midiaAmbienteRepo;
	
	
	
	public void saveUploadMulti( MultipartFile file, Long[] categorias, Empresa empresa, Long[] ambientes ) throws IOException, FileNotFoundException, UnsupportedTagException, InvalidDataException
	{
		if ( categorias == null || categorias.length <= 0 )
			throw new RuntimeException("Nenhuma categoria foi definida para a Mídia. Escolha pelo menos uma categoria.");
		
		if ( ambientes == null || ambientes.length <= 0 )
			throw new RuntimeException("Nenhum ambiente selecionado para receber a Mídia. Escolha pelo menos um ambiente.");
		
		String hash = geraHashDoArquivo( file );
		
		Midia midia = salvaMidia( file, empresa, categorias, hash, null );

//		boolean aoMenosUm = false;
		for ( Long id : ambientes )
		{
			Ambiente ambiente = ambienteRepo.findOne( id );
			
			if ( ambiente != null )
			{
//				aoMenosUm = true;
				associaMidiaEAmbiente( ambiente, midia );
			}
		}
		
//		if ( !aoMenosUm )
//			throw new RuntimeException( "Nenhum ambiente encontrado para associação." );
		
	}
	
	
	public void saveUpload( MultipartFile file, String codigoCategoria, Empresa empresa, Ambiente ambiente, String descricao ) throws IOException, FileNotFoundException, UnsupportedTagException, InvalidDataException
	{
		Categoria categoria = categoriaRepo.findByCodigo( codigoCategoria );
		
		if ( categoria == null )
			throw new RuntimeException("Nenhuma categoria foi definida para a Mídia. Escolha pelo menos uma categoria.");
		
		saveUpload( file, new Long[] { categoria.getIdCategoria() }, empresa, ambiente, descricao );
	}
	

	
	public void saveUpload( MultipartFile file, Long[] categorias, Empresa empresa, Ambiente ambiente ) throws IOException, FileNotFoundException, UnsupportedTagException, InvalidDataException
	{
		saveUpload( file, categorias, empresa, ambiente, null );
	}
	
	
	public void saveUpload( MultipartFile file, Long[] categorias, Empresa empresa, Ambiente ambiente, String descricao ) throws IOException, FileNotFoundException, UnsupportedTagException, InvalidDataException
	{
		if ( categorias == null || categorias.length <= 0 )
			throw new RuntimeException("Nenhuma categoria foi definida para a Mídia. Escolha pelo menos uma categoria.");
		
		String hash = geraHashDoArquivo( file );
		
		Midia midia = salvaMidia( file, empresa, categorias, hash, descricao );
		
		associaMidiaEAmbiente( ambiente, midia );
		
	}


	private Midia salvaMidia( MultipartFile file, Empresa empresa, Long[] categorias, String hash, String descricao ) throws IOException, FileNotFoundException, UnsupportedTagException, InvalidDataException
	{
		List<Categoria> categoriaList = null;
		File arquivo = null;
		Integer size = 0;
		
		String basePath = "";
		
		Parametro parametro = parametroRepo.findByCodigoAndEmpresa( "BASE_MIDIA_PATH", empresa );
		basePath = parametro.getValor();

		
		Midia midia = midiaRepo.findByFilehash( hash );
		
		if ( midia == null )
		{
			String path = basePath + hash;
			
			arquivo = new File( path );

			size = IOUtils.copy( file.getInputStream(), new FileOutputStream( arquivo ) );
			
			midia = new Midia();
			
			midia.setDataUpload( new Date() );
			midia.setNome( file.getOriginalFilename() );
			midia.setFilepath( path );
			midia.setFilehash( hash );
			midia.setMimetype( "audio/mpeg" );  // file.getContentType()
			midia.setFilesize( size );
			midia.setExtensao( FilenameUtils.getExtension( file.getOriginalFilename() ) );
			midia.setValido( true );
			midia.setCached( false );
		}
		else
		{
			size = midia.getFilesize();
			
			arquivo = new File( midia.getFilepath() );
		}
		
		midia.setDescricao( descricao );

		atualizaIDTags( midia, arquivo );

		if ( categorias != null && categorias.length > 0 )
		{
			categoriaList = categoriaRepo.findByIdCategoriaIn( categorias );
			
			midia.setCategorias( categoriaList );
		}
		
		midiaRepo.save( midia );
		return midia;
	}


	private void associaMidiaEAmbiente( Ambiente ambiente, Midia midia )
	{
		MidiaAmbiente assocMidiaAmbiente = midiaAmbienteRepo.findByAmbienteAndMidia( ambiente, midia );

		if ( assocMidiaAmbiente == null )
		{
			assocMidiaAmbiente = new MidiaAmbiente( ambiente, midia, new Date() );
			midiaAmbienteRepo.save( assocMidiaAmbiente );
		}
	}


	private String geraHashDoArquivo( MultipartFile file ) throws IOException
	{
		byte[] bytes = file.getBytes();
		
		LongHashFunction l = LongHashFunction.xx_r39();
		long hashXX = l.hashBytes( bytes, 0, bytes.length );

		String hash = Long.toString( hashXX );

		return hash;
	}

	private void atualizaIDTags( Midia midia, File arquivo ) throws IOException, UnsupportedTagException, InvalidDataException
	{
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
		
	}
	
	
	
	public JSONBootstrapGridWrapper<Midia> filtraMidia( Long idAmbiente, Long idCategoria, String codigoCategoria, Pageable pageable )
	{
		Page<Midia> midiaPage = null;

		Ambiente ambiente = ambienteRepo.findOne( idAmbiente );
		
		if ( idCategoria != null && idCategoria > 0 )
			midiaPage = midiaRepo.findByAmbientesAndCategorias( pageable, ambiente, new Categoria( idCategoria, "" ) );
		else if ( StringUtils.isNotBlank( codigoCategoria ) )
			midiaPage = midiaRepo.findByAmbientesAndCategorias_codigo( pageable, ambiente, codigoCategoria );
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
	
	
}
