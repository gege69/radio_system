package br.com.radio.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import net.openhft.hashing.LongHashFunction;

import org.apache.commons.io.FileUtils;
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
import br.com.radio.model.Genero;
import br.com.radio.model.Midia;
import br.com.radio.model.MidiaAmbiente;
import br.com.radio.model.MidiaGenero;
import br.com.radio.model.Parametro;
import br.com.radio.repository.AmbienteRepository;
import br.com.radio.repository.CategoriaRepository;
import br.com.radio.repository.GeneroRepository;
import br.com.radio.repository.MidiaAmbienteRepository;
import br.com.radio.repository.MidiaGeneroRepository;
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

	@Autowired
	private MidiaGeneroRepository midiaGeneroRepo;
	
	@Autowired
	private GeneroRepository generoRepo;
	
	@PersistenceContext
	protected EntityManager em;
	
	
	@Transactional
	public void saveUploadMulti( MultipartFile multiPartFile, Long[] categorias, Empresa empresa, Long[] ambientes ) throws IOException, FileNotFoundException, UnsupportedTagException, InvalidDataException
	{
		if ( categorias == null || categorias.length <= 0 )
			throw new RuntimeException("Nenhuma categoria foi definida para a Mídia. Escolha pelo menos uma categoria.");
		
		if ( ambientes == null || ambientes.length <= 0 )
			throw new RuntimeException("Nenhum ambiente selecionado para receber a Mídia. Escolha pelo menos um ambiente.");
		
		byte[] bytes = multiPartFile.getBytes(); 
		
		String hash = geraHashDoArquivo( bytes );
		
		Midia midia = gravaMidia( multiPartFile.getInputStream(), multiPartFile.getOriginalFilename(), empresa, categorias, hash, null );

		boolean aoMenosUm = false;
		for ( Long id : ambientes )
		{
			Ambiente ambiente = ambienteRepo.findOne( id );
			
			if ( ambiente != null )
			{
				aoMenosUm = true;
				associaMidiaEAmbiente( ambiente, midia );
			}
		}
		
		if ( !aoMenosUm )
			throw new RuntimeException( "Nenhum ambiente encontrado para associação." );
		
	}
	
	
	
	
	
	public void getFromFileSystem()
	{
		try
		{
			File diretorio = new File("/home/pazin/musicas/");
			
			Collection<File> arquivos = FileUtils.listFiles( diretorio, new String[]{ "mp3" }, true );
			
			List<Genero> generos = generoRepo.findAll();
			
			Random rand = new Random(); 

			Ambiente ambiente = ambienteRepo.findOne( 1L );
			
			Integer iteracoes = 0;
			
			Categoria categoria = categoriaRepo.findByCodigo( Categoria.MUSICA );
			
			List<MidiaGenero> midiaGeneros = new ArrayList<MidiaGenero>();
			
			String grupoArtista = null;
			
			int index = 0;
			int max = 0;
			
			for ( File f : arquivos )
			{
				boolean troca = false;
				
				String pasta = StringUtils.substring( f.getParent(), f.getParent().lastIndexOf( "/" ) + 1 );
				
				System.out.println( pasta );
				
				iteracoes++;
				
				System.out.println(f.getName() + " | " + f.length());

				String hash = "";
				
				FileInputStream fis = null;
				try
				{
					fis = new FileInputStream( f );
					
					byte[] bytes = IOUtils.toByteArray( fis );
					
					hash = geraHashDoArquivo( bytes );
					
					fis.close();
				}
				catch ( Exception e )
				{
					e.printStackTrace();
				}
				

				fis = new FileInputStream( f );
				
				Midia midia = gravaMidia( fis, f.getName(), ambiente.getEmpresa(), new Long[] { categoria.getIdCategoria() }, hash, "" );
				
				fis.close();
				
				if ( StringUtils.isBlank( midia.getArtist() ) )
					midia.setArtist( pasta );
				
				midiaRepo.saveAndFlush( midia );
				
				associaMidiaEAmbiente( ambiente, midia );
				
				
				if ( !StringUtils.equals( grupoArtista, midia.getArtist() ) )
				{
					grupoArtista = midia.getArtist();
					troca = true;
				}

				if ( troca )
				{
					midiaGeneros.clear();
					
					max = rand.nextInt(2) + 1;

					for ( int i = 0; i < max ; i++ )
					{
						MidiaGenero mg = new MidiaGenero();
						
						mg.setMidia(  midia );
						
						index = rand.nextInt( generos.size() );
						
						mg.setGenero( generos.get( index ) );
						
						midiaGeneros.add( mg );
					}
				}
				else
				{
					for ( MidiaGenero mg : midiaGeneros )
					{
						mg.setIdMediagen( null );
						mg.setMidia( midia );
					}
				}

				
				midiaGeneroRepo.save( midiaGeneros );
			}
			
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
		
	}
	
	
	
	public Midia saveUpload( MultipartFile multiPartFile, String codigoCategoria, Empresa empresa, Ambiente ambiente, String descricao ) throws IOException, FileNotFoundException, UnsupportedTagException, InvalidDataException
	{
		Categoria categoria = categoriaRepo.findByCodigo( codigoCategoria );
		
		if ( categoria == null )
			throw new RuntimeException("Nenhuma categoria foi definida para a Mídia. Escolha pelo menos uma categoria.");
		
		return saveUpload( multiPartFile, new Long[] { categoria.getIdCategoria() }, empresa, ambiente, descricao );
	}
	

	
	public Midia saveUpload( MultipartFile multiPartFile, Long[] categorias, Empresa empresa, Ambiente ambiente ) throws IOException, FileNotFoundException, UnsupportedTagException, InvalidDataException
	{
		return saveUpload( multiPartFile, categorias, empresa, ambiente, null );
	}
	
	
	@Transactional
	public Midia saveUpload( MultipartFile multiPartFile, Long[] categorias, Empresa empresa, Ambiente ambiente, String descricao ) throws IOException, FileNotFoundException, UnsupportedTagException, InvalidDataException
	{
		if ( categorias == null || categorias.length <= 0 )
			throw new RuntimeException("Nenhuma categoria foi definida para a Mídia. Escolha pelo menos uma categoria.");
		
		byte[] bytes = multiPartFile.getBytes();
		
		String hash = geraHashDoArquivo( bytes );
		
		Midia midia = gravaMidia( multiPartFile.getInputStream(), multiPartFile.getOriginalFilename(), empresa, categorias, hash, descricao );
		
		associaMidiaEAmbiente( ambiente, midia );
		
		return midia;
	}


	public Midia gravaMidia( InputStream is, String originalName, Empresa empresa, Long[] categorias, String hash, String descricao ) throws IOException, FileNotFoundException, UnsupportedTagException, InvalidDataException
	{
		List<Categoria> categoriaList = null;
		File arquivo = null;
		Integer size = 0;
		
		Midia midia = null;
		try
		{
			String basePath = "";
			
			Parametro parametro = parametroRepo.findByCodigoAndEmpresa( "BASE_MIDIA_PATH", empresa );
			basePath = parametro.getValor();

			
			midia = midiaRepo.findByFilehash( hash );
			
			if ( midia == null )
			{
				String path = basePath + hash;
				
				arquivo = new File( path );

				size = IOUtils.copy( is, new FileOutputStream( arquivo ) );
				
				midia = new Midia();
				
				midia.setDataUpload( new Date() );
				midia.setNome( originalName );
				midia.setFilepath( path );
				midia.setFilehash( hash );
				midia.setMimetype( "audio/mpeg" );  // file.getContentType()
				midia.setFilesize( size );
				midia.setExtensao( FilenameUtils.getExtension( originalName ) );
				midia.setValido( true );
				midia.setCached( false );
				
				preencheInformacoesMP3( midia, arquivo );
			}
			
			midia.setDescricao( descricao );

			if ( categorias != null && categorias.length > 0 )
			{
				categoriaList = categoriaRepo.findByIdCategoriaIn( categorias );
				
				midia.setCategorias( categoriaList );
			}
			
			midiaRepo.save( midia );
		}
		catch ( Exception e )
		{
			if ( ( arquivo != null && arquivo.exists() ) 
				 &&
				 ( midia == null || midia.getIdMidia() == null || midia.getIdMidia().equals( 0L ) ) )
				arquivo.delete();
			
			throw e;
		}
		
		return midia;
	}


	private void associaMidiaEAmbiente( Ambiente ambiente, Midia midia )
	{
		MidiaAmbiente assocMidiaAmbiente = midiaAmbienteRepo.findByAmbienteAndMidia( ambiente, midia );

		if ( assocMidiaAmbiente == null )
		{
			assocMidiaAmbiente = new MidiaAmbiente( ambiente, midia, new Date() );
			midiaAmbienteRepo.saveAndFlush( assocMidiaAmbiente );
		}
	}


	private String geraHashDoArquivo( byte[] bytes ) throws IOException
	{
		LongHashFunction l = LongHashFunction.xx_r39();
		long hashXX = l.hashBytes( bytes, 0, bytes.length );

		String hash = Long.toString( hashXX );

		return hash;
	}

	private void preencheInformacoesMP3( Midia midia, File arquivo ) throws IOException, UnsupportedTagException, InvalidDataException
	{
		if ( arquivo.length() > 0 )
		{
			Mp3File mp3File = new Mp3File( arquivo );

			midia.setDuracao( Long.valueOf( mp3File.getLengthInSeconds() ).intValue() );
			
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
