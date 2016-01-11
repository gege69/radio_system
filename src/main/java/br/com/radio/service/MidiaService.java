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
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.com.radio.dto.MusicTags;
import br.com.radio.json.JSONBootstrapGridWrapper;
import br.com.radio.model.Ambiente;
import br.com.radio.model.Categoria;
import br.com.radio.model.Cliente;
import br.com.radio.model.Genero;
import br.com.radio.model.Midia;
import br.com.radio.model.MidiaAmbiente;
import br.com.radio.model.MidiaGenero;
import br.com.radio.model.Parametro;
import br.com.radio.repository.AmbienteRepository;
import br.com.radio.repository.CategoriaRepository;
import br.com.radio.repository.ClienteRepository;
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
	
	@Autowired
	private ClienteRepository clienteRepo;
	
	@PersistenceContext
	protected EntityManager em;
	
	
	@Transactional
	public void saveUploadMulti( MultipartFile multiPartFile, Long[] categorias, Cliente cliente, Long[] ambientes ) throws IOException, FileNotFoundException, UnsupportedTagException, InvalidDataException
	{
		if ( categorias == null || categorias.length <= 0 )
			throw new RuntimeException("Nenhuma categoria foi definida para a Mídia. Escolha pelo menos uma categoria.");
		
		if ( ambientes == null || ambientes.length <= 0 )
			throw new RuntimeException("Nenhum ambiente selecionado para receber a Mídia. Escolha pelo menos um ambiente.");
		
		byte[] bytes = multiPartFile.getBytes(); 
		
		String hash = geraHashDoArquivo( bytes );
		
		Midia midia = gravaMidia( multiPartFile.getInputStream(), multiPartFile.getOriginalFilename(), cliente, categorias, hash, multiPartFile.getContentType(), null );

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
	
	
	
	
	
	public void getNewMusicFromFileSystem()
	{
		try
		{
			File diretorio = new File("/home/pazin/musicas/Exceto Sertanejas/AC-DC/");
//			File diretorio = new File("/home/pazin/ogg/");
			
//			String contentType = "audio/ogg";
			String contentType = "audio/mpeg";
			
			Collection<File> arquivos = FileUtils.listFiles( diretorio, new String[]{ "mp3" }, true );

			Genero genero = generoRepo.findOne( 1L );
			Genero genero2 = generoRepo.findOne( 2L );
			
			List<Genero> generos =  new ArrayList<Genero>(); ///generoRepo.findFirst10By();  //findAll();
			generos.add( genero );
			generos.add( genero2 );
			
			Random rand = new Random(); 

			Ambiente ambiente = ambienteRepo.findOne( 1L );
			
			if ( ambiente == null )
				throw new RuntimeException("Ambiente não cadastrado ainda....");
			
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
				
				Midia midia = gravaMidia( fis, f.getName(), ambiente.getCliente(), new Long[] { categoria.getIdCategoria() }, hash, "", contentType );
				
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

	
	public void syncMusicFileSystem()
	{
		try
		{
			Cliente cliente = clienteRepo.findOne( 1l );

			String contenttype = "audio/mpeg";
			
			Parametro parametro = parametroRepo.findByCodigo( "BASE_MIDIA_PATH" );
			String basePath = parametro.getValor();
			
			File diretorio = new File( basePath );
			
			Collection<File> arquivos = FileUtils.listFiles( diretorio, null, true );
			
			List<Genero> generos = generoRepo.findFirst10By();  //findAll();
			
			Random rand = new Random(); 

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
				
				Long size = f.length();
				
				Midia midia = gravaMidia( null, f.getName(), cliente, new Long[] { categoria.getIdCategoria() }, hash, "", contenttype, size.intValue() );
				
				if ( StringUtils.isBlank( midia.getArtist() ) )
					midia.setArtist( pasta );
				
				midiaRepo.saveAndFlush( midia );

				Long countGenero = midiaGeneroRepo.countByMidia( midia );
				
				if ( countGenero == null || countGenero <= 0 )
				{
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
			
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
	}
	
	
	
	
	public Midia saveUpload( MultipartFile multiPartFile, String codigoCategoria, Cliente cliente, Ambiente ambiente, String descricao ) throws IOException, FileNotFoundException, UnsupportedTagException, InvalidDataException
	{
		Categoria categoria = categoriaRepo.findByCodigo( codigoCategoria );
		
		if ( categoria == null )
			throw new RuntimeException("Nenhuma categoria foi definida para a Mídia. Escolha pelo menos uma categoria.");
		
		return saveUpload( multiPartFile, new Long[] { categoria.getIdCategoria() }, cliente, ambiente, descricao );
	}
	

	
	public Midia saveUpload( MultipartFile multiPartFile, Long[] categorias, Cliente cliente, Ambiente ambiente ) throws IOException, FileNotFoundException, UnsupportedTagException, InvalidDataException
	{
		return saveUpload( multiPartFile, categorias, cliente, ambiente, null );
	}
	
	
	@Transactional
	public Midia saveUpload( MultipartFile multiPartFile, Long[] categorias, Cliente cliente, Ambiente ambiente, String descricao ) throws IOException, FileNotFoundException, UnsupportedTagException, InvalidDataException
	{
		if ( categorias == null || categorias.length <= 0 )
			throw new RuntimeException("Nenhuma categoria foi definida para a Mídia. Escolha pelo menos uma categoria.");
		
		byte[] bytes = multiPartFile.getBytes();
		
		String hash = geraHashDoArquivo( bytes );
		
		Midia midia = gravaMidia( multiPartFile.getInputStream(), multiPartFile.getOriginalFilename(), cliente, categorias, hash, multiPartFile.getContentType(), descricao );
		
		associaMidiaEAmbiente( ambiente, midia );
		
		return midia;
	}

	
	public Midia gravaMidia( InputStream is, String originalName, Cliente cliente, Long[] categorias, String hash, String contentType, String descricao ) throws IOException, FileNotFoundException, UnsupportedTagException, InvalidDataException
	{
		return gravaMidia( is, originalName, cliente, categorias, hash, descricao, contentType, null );
	}
	

	public Midia gravaMidia( InputStream is, String originalName, Cliente cliente, Long[] categorias, String hash, String contentType, String descricao, Integer fileSize ) throws IOException, FileNotFoundException, UnsupportedTagException, InvalidDataException
	{
		List<Categoria> categoriaList = null;
		File arquivo = null;
		Integer size = 0;
		
		Midia midia = null;
		try
		{
			String basePath = "";
			
			Parametro parametro = parametroRepo.findByCodigo( "BASE_MIDIA_PATH" );
			basePath = parametro.getValor();

			midia = midiaRepo.findByFilehash( hash );
			
			if ( midia == null )
			{
				String path = basePath + hash;

				if ( "audio/ogg".equals( contentType ) )
					path = path + ".ogg";
				
				arquivo = new File( path );
				
				// Talvez não precise mover o arquivo... se o filesize estiver preenchido é um sinal disso
				if ( is != null )
					size = IOUtils.copy( is, new FileOutputStream( arquivo ) );
				else
					size = fileSize;
				
				midia = new Midia();
				
				midia.setDataUpload( new Date() );
				midia.setNome( originalName );
				midia.setFilepath( path );
				midia.setFilehash( hash );
				midia.setMimetype( contentType ); 
				midia.setFilesize( size );
				midia.setExtensao( FilenameUtils.getExtension( originalName ) );
				midia.setValido( true );
				midia.setCached( false );

				preencheTags( contentType, arquivo, midia );
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
			
			throw new RuntimeException( e.getMessage(), e );
		}
		
		return midia;
	}





	private void preencheTags( String contentType, File arquivo, Midia midia ) throws IOException, UnsupportedTagException, InvalidDataException
	{
		MusicTags tags = null;
		
		if ( "audio/ogg".equals( contentType ) )
			tags = obtemInformacoesOGG( arquivo );
		else if ( "audio/mpeg".equals( contentType ) )
			tags = obtemInformacoesMP3( arquivo );
		
		if ( tags != null )
			tags.copyToMidia( midia );
	}


	public Integer associaTodasMidiasParaAmbiente( Ambiente ambiente )
	{
		int linhas = midiaAmbienteRepo.insertMusicasAmbiente( ambiente.getIdAmbiente() );
		
		return linhas;
	}
	
	
	private void associaMidiaEAmbiente( Ambiente ambiente, Midia midia )
	{
		Long qtd = midiaAmbienteRepo.countByAmbienteAndMidia( ambiente, midia );

		if ( qtd == null || qtd == 0 )
			midiaAmbienteRepo.saveAndFlush( new MidiaAmbiente( ambiente, midia, new Date() ) );
	}


	private String geraHashDoArquivo( byte[] bytes ) throws IOException
	{
		LongHashFunction l = LongHashFunction.xx_r39();
		long hashXX = l.hashBytes( bytes, 0, bytes.length );

		String hash = Long.toString( hashXX );

		return hash;
	}

	
	private MusicTags obtemInformacoesOGG( File arquivo ) 
	{
		MusicTags tags = new MusicTags();
		
		if ( arquivo.length() > 0 )
		{
			try
			{
				AudioFile f = AudioFileIO.read( arquivo );
				Tag tag = f.getTag();
				
				tags.trackLength = f.getAudioHeader().getTrackLength();

				tags.title = tag.getFirst( FieldKey.TITLE );
				tags.artist = tag.getFirst( FieldKey.ARTIST );
				tags.album = tag.getFirst( FieldKey.ALBUM );
				tags.comment = tag.getFirst( FieldKey.COMMENT );
				tags.date = tag.getFirst( FieldKey.YEAR );
				tags.genre = tag.getFirst( FieldKey.GENRE );
			}
			catch ( Exception e )
			{
				e.printStackTrace();
			}
		}
		
		return tags;
	}
	
	private MusicTags obtemInformacoesMP3( File arquivo ) throws IOException, UnsupportedTagException, InvalidDataException
	{
		MusicTags tags = new MusicTags();
		
		if ( arquivo.length() > 0 )
		{
			Mp3File mp3File = new Mp3File( arquivo );

			tags.trackLength = Long.valueOf( mp3File.getLengthInSeconds() ).intValue();
			
			if ( mp3File.hasId3v2Tag() )
			{
				ID3v2 id3v2Tag;
				
				id3v2Tag = mp3File.getId3v2Tag();
				
				tags.title = id3v2Tag.getTitle();
				tags.artist = id3v2Tag.getArtist();
				tags.album = id3v2Tag.getAlbum();
				tags.comment = id3v2Tag.getComment();
				tags.date = id3v2Tag.getYear();
				tags.genre = id3v2Tag.getGenreDescription();
			}
			else if ( mp3File.hasId3v1Tag() )
			{
				ID3v1 id3v1Tag;
				
				id3v1Tag = mp3File.getId3v1Tag();
				
				tags.title = id3v1Tag.getTitle();
				tags.artist = id3v1Tag.getArtist();
				tags.album = id3v1Tag.getAlbum();
				tags.comment = id3v1Tag.getComment();
				tags.date = id3v1Tag.getYear();
				tags.genre = id3v1Tag.getGenreDescription();
			}
		}
		
		return tags;
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

	
	
	public List<Midia> getMidiasAtivasPorAmbienteCategoria( Ambiente ambiente, Categoria categoria )
	{
		List<Midia> result = midiaRepo.findByAmbientesAndCategorias( ambiente, categoria );
		
		return result;
	}
	

	
	
	
	
	
}
