package br.com.radio.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.persistence.EntityManager;

import net.openhft.hashing.LongHashFunction;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.hibernate.sql.JoinType;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import br.com.radio.dto.MusicTags;
import br.com.radio.dto.midia.MidiaFilter;
import br.com.radio.dto.midia.RelatorioMidiaGeneroVO;
import br.com.radio.enumeration.StatusAmbiente;
import br.com.radio.model.AlfanumericoMidia;
import br.com.radio.model.Ambiente;
import br.com.radio.model.AmbienteGenero;
import br.com.radio.model.AudioOpcional;
import br.com.radio.model.Categoria;
import br.com.radio.model.Cliente;
import br.com.radio.model.Genero;
import br.com.radio.model.Midia;
import br.com.radio.model.MidiaAmbiente;
import br.com.radio.model.MidiaGenero;
import br.com.radio.model.MidiaOpcional;
import br.com.radio.model.Parametro;
import br.com.radio.repository.AlfanumericoMidiaRepository;
import br.com.radio.repository.AmbienteGeneroRepository;
import br.com.radio.repository.AmbienteRepository;
import br.com.radio.repository.AudioOpcionalRepository;
import br.com.radio.repository.CategoriaRepository;
import br.com.radio.repository.ClienteRepository;
import br.com.radio.repository.GeneroRepository;
import br.com.radio.repository.MidiaAmbienteRepository;
import br.com.radio.repository.MidiaCategoriaRepository;
import br.com.radio.repository.MidiaGeneroRepository;
import br.com.radio.repository.MidiaOpcionalRepository;
import br.com.radio.repository.MidiaRepository;
import br.com.radio.repository.ParametroRepository;
import br.com.radio.service.vo.GravaMidiaParameter;

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

@Component
public class MidiaService {
	
	private static final Logger logger = Logger.getLogger(MidiaService.class);

	@Autowired
	private MidiaRepository midiaRepo;
	
	@Autowired
	private MidiaAmbienteRepository midiaAmbienteRepo;

	@Autowired
	private MidiaGeneroRepository midiaGeneroRepo;
	
	@Autowired
	private MidiaCategoriaRepository midiaCategoriaRepo;
	
	@Autowired
	private CategoriaRepository categoriaRepo;

	@Autowired
	private ParametroRepository parametroRepo;
	
	@Autowired
	private AmbienteGeneroRepository ambienteGeneroRepo;
	
	@Autowired
	private AmbienteRepository ambienteRepo;
	
	@Autowired
	private AlfanumericoMidiaRepository alfaMidiaRepo;
	
	@Autowired
	private GeneroRepository generoRepo;
	
	@Autowired
	private ClienteRepository clienteRepo;

	@Autowired
	private AudioOpcionalRepository opcionalRepo;
	
	@Autowired
	private MidiaOpcionalRepository midiaOpcionalRepo;

	@Autowired
	private EntityManager entityManager;
	
	private Map<String,String> mapExtToMimeType = new HashMap<String,String>();
	private Map<String,String> mapMimeTypeToExt = new HashMap<String,String>();

	public MidiaService()
	{
		super();
		this.mapExtToMimeType.put( "ogg", "audio/ogg" );
		this.mapExtToMimeType.put( "mp3", "audio/mpeg" );

		this.mapMimeTypeToExt.put( "audio/ogg", "ogg" );
		this.mapMimeTypeToExt.put( "audio/mpeg", "mp3" );
		this.mapMimeTypeToExt.put( "audio/mp3", "mp3" );
		this.mapMimeTypeToExt.put( "audio/mpeg3", "mp3" );
	}


	
	
	
	
	@Transactional
	public void getNewMusicFromFileSystem()
	{
		try
		{
			Parametro parametro = parametroRepo.findByCodigo( "NEW_MIDIA_PATH" );
			String newPath = parametro.getValor();

			File diretorio = new File(newPath);
			
			Collection<File> arquivos = FileUtils.listFiles( diretorio, new String[]{ "ogg", "mp3" }, true );

			Genero genero = generoRepo.findOne( 1L );
			Genero genero2 = generoRepo.findOne( 2L );
			
			List<Genero> generos =  new ArrayList<Genero>(); ///generoRepo.findFirst10By();  //findAll();
			generos.add( genero );
			generos.add( genero2 );
			
			Random rand = new Random(); 

			Cliente cliente = clienteRepo.findOne( 1L );
			
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
				
				iteracoes++;
				
				System.out.println(f.getName() + " | " + f.length());
				logger.info(f.getName() + " | " + f.length());

				String hash = "";
				
				String contentType = mapExtToMimeType.get( FilenameUtils.getExtension( f.getName() ) );
				
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
					logger.error( "erro", e );
				}
				

				fis = new FileInputStream( f );
				
				GravaMidiaParameter parametros = new GravaMidiaParameter( f.getName(), cliente, new Long[] { categoria.getIdCategoria() }, hash, contentType, "", null );
				
				Midia midia = gravaMidia( fis, parametros );

				fis.close();
				
				if ( StringUtils.isBlank( midia.getArtist() ) )
					midia.setArtist( pasta );
				
				midiaRepo.saveAndFlush( midia );

				List<Ambiente> ambientes = ambienteRepo.findAll();
				
				for ( Ambiente ambiente : ambientes )
					associaTodasMidiasParaAmbiente( ambiente );
				
				if ( !StringUtils.equals( grupoArtista, midia.getArtist() ) )
				{
					grupoArtista = midia.getArtist();
					troca = true;
				}

//				if ( troca )
//				{
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
//				}
//				else
//				{
//					for ( MidiaGenero mg : midiaGeneros )
//					{
//						mg.setIdMediagen( null );
//						mg.setMidia( midia );
//					}
//				}

				
				midiaGeneroRepo.save( midiaGeneros );
			}
			
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			logger.error( "erro", e );
		}
		
	}

	
	public void syncMusicFileSystem()
	{
		try
		{
			Cliente cliente = clienteRepo.findOne( 1l );

			Map<String,String> mapasMimeType = new HashMap<String,String>();
			mapasMimeType.put( "ogg", "audio/ogg" );
			mapasMimeType.put( "mp3", "audio/mpeg" );
			mapasMimeType.put( "", "audio/mpeg" );
			
			Parametro parametro = parametroRepo.findByCodigo( "BASE_MIDIA_PATH" );
			String basePath = parametro.getValor();
			
			logger.info( basePath );

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

			String contenttype = "";
			
			for ( File f : arquivos )
			{
				
				
				boolean troca = false;
				
				String pasta = StringUtils.substring( f.getParent(), f.getParent().lastIndexOf( "/" ) + 1 );
				
				logger.info( pasta );

				iteracoes++;
				
				logger.info(f.getName() + " | " + f.length());

				String hash = "";
				
				contenttype = mapasMimeType.get( FilenameUtils.getExtension( f.getName() ) );
				
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
					
					logger.error( "error", e );
				}
				
				Long size = f.length();
				
				Midia midia = gravaMidia( null, new GravaMidiaParameter( f.getName(), cliente, new Long[] { categoria.getIdCategoria() }, hash, contenttype, "", size.intValue() ) );
				
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
			logger.error( "erro", e );
			e.printStackTrace();
		}
	}
	


	@Transactional
	public void saveUploadMulti( MultipartFile multiPartFile, Long[] categorias, Cliente cliente, Long[] ambientes, Date dataInicioValidade, Date dataFimValidade ) throws IOException, FileNotFoundException, UnsupportedTagException, InvalidDataException
	{
		if ( categorias == null || categorias.length <= 0 )
			throw new RuntimeException("Nenhuma categoria foi definida para a Mídia. Escolha pelo menos uma categoria.");
		
		if ( ambientes == null || ambientes.length <= 0 )
			throw new RuntimeException("Nenhum ambiente selecionado para receber a Mídia. Escolha pelo menos um ambiente.");
		
		byte[] bytes = multiPartFile.getBytes(); 
		
		String hash = geraHashDoArquivo( bytes );

		GravaMidiaParameter parametros = new GravaMidiaParameter( multiPartFile.getOriginalFilename(), cliente, categorias, hash, multiPartFile.getContentType(), null, null, dataInicioValidade, dataFimValidade );
		parametros.validar();
		
		Midia midia = gravaMidia( multiPartFile.getInputStream(), parametros );

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
	
	
	/**
	 * Esse método é exclusivo para músicas pois também é passada uma lista de gêneros e a música precisa ser associada para todos os ambientes do cliente.
	 * 
	 * @param multiPartFile
	 * @param codigoCategoria
	 * @param cliente
	 * @param descricao
	 * @param arrayGeneros
	 * @return
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws UnsupportedTagException
	 * @throws InvalidDataException
	 */
	@Transactional
	public Midia saveUploadMusica( MultipartFile multiPartFile, String codigoCategoria, Cliente cliente, String descricao, Long[] arrayGeneros ) throws IOException, FileNotFoundException, UnsupportedTagException, InvalidDataException
	{
		Categoria categoria = categoriaRepo.findByCodigo( codigoCategoria );
		
		if ( categoria == null )
			throw new RuntimeException("Nenhuma categoria foi definida para a Mídia. Escolha pelo menos uma categoria.");
		
		byte[] bytes = multiPartFile.getBytes();
		
		String hash = geraHashDoArquivo( bytes );
		
		GravaMidiaParameter parametros = new GravaMidiaParameter( multiPartFile.getOriginalFilename(), cliente, new Long[] { categoria.getIdCategoria() }, hash, multiPartFile.getContentType(), descricao, null );
		
		Midia midia = gravaMidia( multiPartFile.getInputStream(), parametros );
		
		associaGenerosParaMusica( midia, arrayGeneros );
		
		associaMidiaParaTodosAmbientes( midia );
		
		return midia;
	}	



	public Midia saveUploadChamadaVeiculo( MultipartFile multiPartFile, String codigoCategoria, Cliente cliente, String descricao ) throws IOException, FileNotFoundException, UnsupportedTagException, InvalidDataException
	{
		Categoria categoria = null;

		if ( StringUtils.isNotBlank( codigoCategoria ) )
			categoria = categoriaRepo.findByCodigo( codigoCategoria );
		
		// Caso a descricao esteja preenchido pode ser o upload de Alfanumérico.... pode passar pela validação
		if ( categoria == null && StringUtils.isBlank( descricao ) )
			throw new RuntimeException("Nenhuma categoria foi definida para a Mídia. Escolha pelo menos uma categoria.");
		
		Midia result = null;
		
		SaveUploadParameter parametros = new SaveUploadParameter( multiPartFile, categoria, cliente, descricao );
		
		if ( categoria == null || categoria.getCodigo().equals( Categoria.VEIC_PLACA_LETRA ) || categoria.getCodigo().equals( Categoria.VEIC_PLACA_NUMERO )  )
			result = saveUploadChamadaVeiculoAlfaNumerico( parametros );
		else
			result = saveUploadChamadaVeiculoOutros( parametros );
		
		return result;
	}
	


	@Transactional
	public Midia saveUploadChamadaVeiculoOutros( SaveUploadParameter saveUploadParametros  ) throws IOException, FileNotFoundException, UnsupportedTagException, InvalidDataException
	{
		String descricao = saveUploadParametros.getDescricao();
		byte[] bytes = saveUploadParametros.getMultiPartFile().getBytes();
		
		String hash = geraHashDoArquivo( bytes );
		
		Midia midia = midiaRepo.findByFilehash( hash );
		
//		if ( midia != null )
//		{ 
//			List<Categoria> outras = midia.getCategorias().stream().filter( cat -> !cat.equals( categoria ) ).collect( Collectors.toList() );
//
//			if ( outras != null && outras.size() > 0 )
//			{
//				StringBuilder sb = new StringBuilder();
//				outras.forEach( c -> sb.append( c.getDescricao() ) );
//
//				throw new RuntimeException( "Essa chamada já existe em outra categoria ( " + outras.toString() + " )." );
//			}
//		}

		if ( StringUtils.isBlank( descricao ) )
			descricao = saveUploadParametros.getMultiPartFile().getOriginalFilename();

		GravaMidiaParameter gravaMidiaParametros = new GravaMidiaParameter( saveUploadParametros.getMultiPartFile().getOriginalFilename(), saveUploadParametros.getCliente(), new Long[] { saveUploadParametros.getCategoria().getIdCategoria() }, hash, saveUploadParametros.getMultiPartFile().getContentType(), descricao, null );
		
		midia = gravaMidia( saveUploadParametros.getMultiPartFile().getInputStream(), gravaMidiaParametros );
		
		associaMidiaParaTodosAmbientes( midia );
		
		return midia;
	}	


	
	
	@Transactional
	public Midia saveUploadChamadaVeiculoAlfaNumerico( SaveUploadParameter saveUploadParametros ) throws IOException, FileNotFoundException, UnsupportedTagException, InvalidDataException
	{
		String alfanumerico = StringUtils.trim( saveUploadParametros.getDescricao() );
		
		if ( StringUtils.isBlank( alfanumerico ) )
			throw new RuntimeException( "A Letra (ou número) correspondente ao áudio é obrigatório ( Ex: 'A' ou '19' )" );
		
		if ( !StringUtils.isAlphanumeric( alfanumerico ) )
			throw new RuntimeException( "O caractére preenchido é inválido.");
		
		boolean numerico = StringUtils.isNumeric( alfanumerico );
		
		if ( numerico && StringUtils.length( alfanumerico ) != 2 )
			throw new RuntimeException( "Os números da placa precisam ser gravados em pares de dois dígitos ( Ex: 19, 25, 37, etc )");
		
		if ( !numerico && StringUtils.length( alfanumerico ) > 1 )
			throw new RuntimeException( "As letras que formam a placa precisam ser gravadas separadamente ( Ex: A, B, F, etc )");
		
		Categoria categoria = saveUploadParametros.getCategoria();

		if ( numerico )
			categoria = categoriaRepo.findByCodigo( Categoria.VEIC_PLACA_NUMERO );
		else
		{
			categoria = categoriaRepo.findByCodigo( Categoria.VEIC_PLACA_LETRA );
			alfanumerico = StringUtils.upperCase( alfanumerico );
		}
		
		byte[] bytes = saveUploadParametros.getMultiPartFile().getBytes();
		
		String hash = geraHashDoArquivo( bytes );

		GravaMidiaParameter parametros = new GravaMidiaParameter( saveUploadParametros.getMultiPartFile().getOriginalFilename(), saveUploadParametros.getCliente(), new Long[] { categoria.getIdCategoria() }, hash, saveUploadParametros.getMultiPartFile().getContentType(), alfanumerico, null );
		
		Midia midia = gravaMidia( saveUploadParametros.getMultiPartFile().getInputStream(), parametros );
		
		// pode ter um bug aqui... uma midia "deletada" em uma categoria pode ser reativada em outra... e ficar com as duas categorias.  :TODO

		associaMidiaParaTodosAmbientes( midia );
		
		AlfanumericoMidia alfa = alfaMidiaRepo.findByAlfanumerico( alfanumerico );

		if ( alfa != null )
		{
			Midia outraMidia = alfa.getMidia();
			
			if ( !outraMidia.equals( midia ) )
			{
				// por enquanto ele deleta a midia....
				deleteMidiaSePossivel( outraMidia.getIdMidia() );
			}
			
			alfa.setMidia( midia );
			alfa.setAlfanumerico( alfanumerico );
		}
		else
			alfa = new AlfanumericoMidia( alfanumerico, midia );
		
		alfaMidiaRepo.save( alfa );
			
		return midia;
	}	
	

	@Transactional
	public Midia saveUploadOpcional( MultipartFile multiPartFile, Cliente cliente, String descricao, Long idOpcional ) throws IOException, FileNotFoundException, UnsupportedTagException, InvalidDataException
	{
		if ( idOpcional == null || idOpcional <= 0 )
			throw new RuntimeException("É necessário determinar o Nome do Opcional");
		
		Categoria categoria = categoriaRepo.findByCodigo( Categoria.OPCIONAL );
		
		AudioOpcional opcional = opcionalRepo.findOne( idOpcional );
		
		if ( opcional == null )
			throw new RuntimeException("Opcional não cadastrado.");
		
		byte[] bytes = multiPartFile.getBytes();
		
		String hash = geraHashDoArquivo( bytes );

		GravaMidiaParameter parametros = new GravaMidiaParameter( multiPartFile.getOriginalFilename(), cliente, new Long[] {categoria.getIdCategoria()}, hash, multiPartFile.getContentType(), descricao, null );
		parametros.validar();
		
		Midia midia = gravaMidia( multiPartFile.getInputStream(), parametros );
		
		associaMidiaParaTodosAmbientes( midia );

		MidiaOpcional mo = new MidiaOpcional();
		
		mo.setMidia( midia );
		mo.setOpcional( opcional );
		
		midiaOpcionalRepo.save( mo );
			
		return midia;
	}	



	private void associaGenerosParaMusica( Midia midia, Long[] arrayGeneros )
	{
		List<Genero> generos = generoRepo.findByIdGeneroIn( Arrays.asList( arrayGeneros ) );
		
		if ( generos == null || generos.size() == 0 )
			throw new RuntimeException("Nenhum gênero passado corresponde aos gêneros cadastrados no banco de dados" );
		
		for ( Genero genero : generos )
		{
			MidiaGenero md = new MidiaGenero( genero, midia );
			
			midiaGeneroRepo.save( md );
		}
	}
	

	
	public Midia saveUpload( MultipartFile multiPartFile, String codigoCategoria, Cliente cliente, Ambiente ambiente, String descricao ) throws IOException, FileNotFoundException, UnsupportedTagException, InvalidDataException
	{
		Categoria categoria = categoriaRepo.findByCodigo( codigoCategoria );
		
		if ( categoria == null )
			throw new RuntimeException("Nenhuma categoria foi definida para a Mídia. Escolha pelo menos uma categoria.");
		
		return saveUpload( multiPartFile, new Long[] { categoria.getIdCategoria() }, cliente, ambiente, descricao, null, null );
	}
	

	@Transactional
	public Midia saveUpload( MultipartFile multiPartFile, Long[] categorias, Cliente cliente, Ambiente ambiente, String descricao, Date dataInicioValidade, Date dataFimValidade ) throws IOException, FileNotFoundException, UnsupportedTagException, InvalidDataException
	{
		if ( categorias == null || categorias.length <= 0 )
			throw new RuntimeException("Nenhuma categoria foi definida para a Mídia. Escolha pelo menos uma categoria.");
		
		byte[] bytes = multiPartFile.getBytes();
		
		String hash = geraHashDoArquivo( bytes );
		
		GravaMidiaParameter parametros = new GravaMidiaParameter( multiPartFile.getOriginalFilename(), cliente, categorias, hash, multiPartFile.getContentType(), descricao, null, dataInicioValidade, dataFimValidade );
		parametros.validar();
		
		Midia midia = gravaMidia( multiPartFile.getInputStream(), parametros );
		
		associaMidiaEAmbiente( ambiente, midia );
		
		return midia;
	}
	

	public Midia gravaMidia( InputStream is , GravaMidiaParameter parametros  ) throws IOException, FileNotFoundException, UnsupportedTagException, InvalidDataException
	{
		List<Categoria> categoriaList = null;
		File arquivo = null;
		Integer size = 0;
		
		String extensao = this.mapMimeTypeToExt.get( parametros.getContentType() );
		
		if ( extensao == null )
			throw new RuntimeException( "Tipo de arquivo inválido. Só são aceitos arquivos de Música MP3 ou OGG." );
		
		
		Midia midia = null;
		try
		{
			String path = getDefaultPath( parametros.getHash(), parametros.getContentType() );

			midia = midiaRepo.findByFilehash( parametros.getHash() );
		
			boolean preencher = false;

			if (  midia == null || ( midia != null && midia.getValido() == false ) )
			{
				preencher = true;
				
				if ( midia == null )
					midia = new Midia();
			}
			
			if ( preencher )
			{
				arquivo = new File( path );
				
				// Talvez não precise mover o arquivo... se o filesize estiver preenchido é um sinal disso
				if ( is != null )
					size = IOUtils.copy( is, new FileOutputStream( arquivo ) );
				else
					size = parametros.getFileSize();
				
				midia.setDataUpload( new Date() );
				midia.setNome( parametros.getOriginalName() );
				midia.setFilepath( path );
				midia.setFilehash( parametros.getHash() );
				midia.setMimetype( parametros.getContentType() ); 
				midia.setFilesize( size );
				midia.setExtensao( FilenameUtils.getExtension( parametros.getOriginalName() ) );
				midia.setValido( true );
				midia.setCached( false );

				preencheTags( parametros.getContentType(), arquivo, midia );
			}
			
			midia.setDescricao( parametros.getDescricao() );
			midia.setDataInicioValidade( parametros.getDataInicioValidade() );
			midia.setDataFimValidade( parametros.getDataFimValidade() );

			if ( parametros.getCategorias() != null && parametros.getCategorias().length > 0 )
			{
				categoriaList = categoriaRepo.findByIdCategoriaIn( parametros.getCategorias() );
				
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



	public Midia alteraNomeMidia( Midia midiaVO )
	{
		Midia midia = midiaRepo.findOne( midiaVO.getIdMidia() );
		
		if ( midia == null )
			throw new RuntimeException( "Mídia não encontrada");

		if ( StringUtils.isBlank( midiaVO.getNome() ) )
			throw new RuntimeException("Nome não pode ser branco");
		
		boolean isChamadaVeiculo = midia.getCategorias().stream().anyMatch( cat -> "veic".equals( StringUtils.left( cat.getCodigo(), 4 ) ) );
		
		if ( isChamadaVeiculo )
		{
			Long qtd = midiaRepo.countByNome( midiaVO.getNome() );
			
			if ( qtd != null && qtd > 1 )
				throw new RuntimeException( "Já existe uma chamada de veículo com esse nome. Por favor escolha outro.");
		}
		
		midia.setNome( midiaVO.getNome() );
		
		if ( StringUtils.isNotBlank( midiaVO.getDescricao() ) )
			midia.setDescricao( midiaVO.getDescricao() );
		
		midiaRepo.save( midia );

		return midia;
	}





	public Midia alteraMusica( Midia midiaVO )
	{
		Midia midia = midiaRepo.findOne( midiaVO.getIdMidia() );
		
		if ( midia == null )
			throw new RuntimeException( "Mídia não encontrada");

		if ( StringUtils.isBlank( midiaVO.getNome() ) )
			throw new RuntimeException("Nome não pode ser branco");
		
		midia.setNome( midiaVO.getNome() );
		
		if ( StringUtils.isNotBlank( midiaVO.getDescricao() ) )
			midia.setDescricao( midiaVO.getDescricao() );
		
		if ( midiaVO.getGeneros() != null )
			midia.setGeneros( midiaVO.getGeneros() );
		
		midiaRepo.save( midia );

		return midia;
	}



	private String getDefaultPath( String hash, String contentType )
	{
		String basePath = "";
		
		Parametro parametro = parametroRepo.findByCodigo( "BASE_MIDIA_PATH" );
		basePath = parametro.getValor();

		String path = basePath + "md_" + hash;

		if ( "audio/ogg".equals( contentType ) )
			path = path + ".ogg";
		return path;
	}



	private void validaContentType( String contentType ){
		
		
	}


	private void preencheTags( String contentType, File arquivo, Midia midia ) throws IOException, UnsupportedTagException, InvalidDataException
	{
		MusicTags tags = null;
		
		if ( "audio/ogg".equals( contentType ) )
			tags = obtemInformacoesOGG( arquivo );
		else if ( "audio/mpeg".equals( contentType ) || "audio/mp3".equals( contentType ) )
			tags = obtemInformacoesMP3( arquivo );
		
		if ( tags != null )
			tags.copyToMidia( midia );
	}


	public Integer associaTodasMidiasParaAmbiente( Ambiente ambiente )
	{
		int linhas = midiaAmbienteRepo.insertMidiasDefaultAmbiente( ambiente.getIdAmbiente() );
		
		return linhas;
	}
	
	
	private void associaMidiaEAmbiente( Ambiente ambiente, Midia midia )
	{
		Long qtd = midiaAmbienteRepo.countByAmbienteAndMidia( ambiente, midia );

		if ( qtd == null || qtd == 0 )
			midiaAmbienteRepo.saveAndFlush( new MidiaAmbiente( ambiente, midia, new Date() ) );
	}


	private void associaMidiaParaTodosAmbientesDoCliente( Cliente cliente, Midia midia )
	{
		List<Ambiente> ambientes = ambienteRepo.findByCliente( cliente );
		
		for ( Ambiente ambiente : ambientes )
		{
			Long qtd = midiaAmbienteRepo.countByAmbienteAndMidia( ambiente, midia );

			if ( qtd == null || qtd == 0 )
				midiaAmbienteRepo.saveAndFlush( new MidiaAmbiente( ambiente, midia, new Date() ) );
		}
	}

	private void associaMidiaParaTodosAmbientes( Midia midia )
	{
		List<Ambiente> ambientes = ambienteRepo.findByStatus( StatusAmbiente.ATIVO );
		
		for ( Ambiente ambiente : ambientes )
		{
			Long qtd = midiaAmbienteRepo.countByAmbienteAndMidia( ambiente, midia );

			if ( qtd == null || qtd == 0 )
				midiaAmbienteRepo.saveAndFlush( new MidiaAmbiente( ambiente, midia, new Date() ) );
		}
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
				Logger.getLogger("org.jaudiotagger").setLevel(Level.OFF);
				
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
	



	
	
	
	/**
	 * Por convenção o início do metodo como "filtra" implica que ele é utilizado pela tela (por isso também ele retorna um Page)
	 * Métodos que são usados por business serão iniciados por "find" e retornam List.
	 * 
	 * @param pageable
	 * @param filter
	 * @return
	 */
	@SuppressWarnings( "unchecked" )
	@Transactional
	public Page<Midia> filtraMidiasCategorias( Pageable pageable, MidiaFilter filter ){
		
		Session session = entityManager.unwrap( Session.class );
		
		Criteria critCount = createCriteriaMidiasCategorias( filter, session );
		critCount.setProjection( Projections.rowCount() );
		Long total = (Long)critCount.uniqueResult();

		Criteria crit = createCriteriaMidiasCategorias( filter, session );
		
		if ( pageable != null ){
			crit.setMaxResults( pageable.getPageSize() );
			crit.setFirstResult( pageable.getPageNumber() );
		}
		
		List<Midia> listMidia = crit.list();

		buildMidiaViewCategoria( listMidia );
		
		PageImpl<Midia> paginaMidias = new PageImpl<Midia>( listMidia, pageable, total );
		
		return paginaMidias;
	}
	

	
	@SuppressWarnings( "unchecked" )
	@Transactional
	public List<Midia> findMidiasCategorias( MidiaFilter filter ){
		
		Session session = entityManager.unwrap( Session.class );
		
		Criteria crit = createCriteriaMidiasCategorias( filter, session );
		
		List<Midia> result = crit.list();
		
		return result;
	}


	private Criteria createCriteriaMidiasCategorias( MidiaFilter filter, Session session )
	{
		Criteria crit = session.createCriteria( Midia.class );
		
		boolean isIdCategoria = ( filter.getCategoria() != null && filter.getCategoria().getIdCategoria() != null && filter.getCategoria().getIdCategoria() > 0 );
		boolean isListaCategorias = ( filter.getCategoriaIds() != null && filter.getCategoriaIds().size() > 0 );
		boolean isCodigoCategoria = ( filter.getCategoria() != null && filter.getCategoria().getIdCategoria() == null && StringUtils.isNotBlank( filter.getCategoria().getCodigo() ) );
		
		if ( isIdCategoria || isListaCategorias || isCodigoCategoria ){
			crit.createAlias( "categorias", "c" );
			
			if ( isIdCategoria )
				crit.add( Restrictions.eq( "c.idCategoria", filter.getCategoria().getIdCategoria() ) );
			else if ( isListaCategorias )
				crit.add( Restrictions.in( "c.idCategoria", filter.getCategoriaIds() ) );
			else if ( isCodigoCategoria )
				crit.add( Restrictions.eq( "c.codigo", filter.getCategoria().getCodigo() ) );
		}

		if ( filter.getAmbiente() != null ){
			crit.createAlias( "ambientes", "a" );
			crit.add( Restrictions.eq( "a.idAmbiente", filter.getAmbiente().getIdAmbiente() ) );
		}
		crit.add( Restrictions.eq( "valido", true ) );

		if ( filter.hasSearch() )
			crit.add( Restrictions.disjunction( Restrictions.ilike( "nome", filter.getPreparedSearch() ), Restrictions.ilike( "descricao", filter.getPreparedSearch() ) ) );
		
		if ( filter.isVerificaValidade() ){
			
			Conjunction conj = Restrictions.conjunction();
			
			Disjunction disjuntionInicio = Restrictions.disjunction();
			
			disjuntionInicio.add( Restrictions.isNull( "dataInicioValidade" ) );
			disjuntionInicio.add( Restrictions.sqlRestriction( "date_trunc( 'day', {alias}.datainiciovalidade ) <= date_trunc( 'day', now())" ) );
			
			Disjunction disjuntionFim = Restrictions.disjunction();
			
			disjuntionFim.add( Restrictions.isNull( "dataFimValidade" ) );
			disjuntionFim.add( Restrictions.sqlRestriction( "date_trunc( 'day', {alias}.datafimvalidade ) >= date_trunc( 'day', now())" ) );
			
			conj.add( disjuntionInicio );
			conj.add( disjuntionFim );
			
			crit.add( conj );
		}

		return crit;
	}



	private void buildMidiaViewCategoria( List<Midia> midiaList )
	{
		midiaList.stream().forEach( m -> {
			m.getCategorias().forEach( cat -> {
				m.getMidiaView().put( cat.getCodigo(), "true" );
			});
		});
	}

	

	@Transactional
	public boolean deleteMidiaSePossivel( Long idMidia )
	{
		boolean result = false;
		
		Midia midia = midiaRepo.findOne( idMidia );
		
		if ( midia == null )
			throw new RuntimeException( "Mídia não encontrada" );
		
		List<MidiaAmbiente> associacoesAmbientes = midiaAmbienteRepo.findByMidia( midia );

		for ( MidiaAmbiente ma : associacoesAmbientes )
		{
			midiaAmbienteRepo.delete( ma );
			midiaAmbienteRepo.flush();
		}
		
		midia = midiaRepo.findOne( idMidia );

		String filePath = midia.getFilepath();

		midia.setValido( false );
		
		midiaRepo.save( midia );
		
		// aqui tenho que tentar destruir o arquivo fisico no disco
		FileUtils.deleteQuietly( new File( filePath ) );
		
		return result;
	}

	


	@Transactional
	public boolean deleteGenero( Long idGenero )
	{
		boolean result = false;
		
		Genero genero = generoRepo.findOne( idGenero );
		
		if ( genero == null )
			throw new RuntimeException("Gênero não encontrado");
		
		List<AmbienteGenero> ambienteGenerosList = ambienteGeneroRepo.findByGenero( genero );
		
		ambienteGeneroRepo.delete( ambienteGenerosList );
		
		List<MidiaGenero> midiaGenerosList = midiaGeneroRepo.findByGenero( genero );

		midiaGeneroRepo.delete( midiaGenerosList );
		
		generoRepo.delete( genero );

		
		return result;
	}

	
	@Transactional
	public void deletaMidiasSemGenero(){
//		
//		Session session = entityManager.unwrap( Session.class );
//
//		Categoria categoriaOpcional = categoriaRepo.findByCodigo( Categoria.OPCIONAL );
//		
//		Criteria critCount = createCriteriaMidiaOpcional( ambiente, opcional, session, categoriaOpcional );
//		critCount.setProjection( Projections.rowCount() );
//		Long total = (Long)critCount.uniqueResult();
	}
	
	


	@Transactional
	public boolean deleteMidiaOpcionalSePossivel( Long idMidia )
	{
		boolean result = false;
		
		Midia midia = midiaRepo.findOne( idMidia );
		
		if ( midia == null )
			throw new RuntimeException( "Mídia não encontrada" );
		
		List<MidiaAmbiente> associacoesAmbientes = midiaAmbienteRepo.findByMidia( midia );

		for ( MidiaAmbiente ma : associacoesAmbientes )
		{
			midiaAmbienteRepo.delete( ma );
			midiaAmbienteRepo.flush();
		}

		List<MidiaOpcional> midiaOpcionais = midiaOpcionalRepo.findByMidia( midia );
		
		for ( MidiaOpcional mo : midiaOpcionais )
		{
			midiaOpcionalRepo.delete( mo );
			midiaOpcionalRepo.flush();
		}
		
		midia = midiaRepo.findOne( idMidia );

		String filePath = midia.getFilepath();

		midia.setValido( false );
		
		midiaRepo.save( midia );
		
		// aqui tenho que tentar destruir o arquivo fisico no disco
		FileUtils.deleteQuietly( new File( filePath ) );
		
		return result;
	}



	public String getResumoGenerosDaMidia( Midia midia ){
		
		int tamanho = 38;
		
		StringBuilder sb = new StringBuilder();
		
		List<Genero> generos = midia.getGeneros();
		
		for ( Genero gen : generos ){
			
			if ( sb.length() > 0 )
				sb.append( ", " );

			sb.append( gen.getNome() );
			
			if ( StringUtils.length( sb.toString() ) >= tamanho )
				break;
		}
		
		String result = sb.toString();
		
		if ( StringUtils.length( result ) >= tamanho )
			result = StringUtils.substring( result, 0, tamanho ) + "...";
		
		return result;
	}


	@Transactional	
	public List<Midia> getMidiasOpcionais( Ambiente ambiente, AudioOpcional opcional ){
		
		Page<Midia> resultPage = getMidiasOpcionais( ambiente, opcional, null );

		return resultPage.getContent();
	}
	

	@SuppressWarnings( "unchecked" )
	@Transactional
	public Page<Midia> getMidiasOpcionais( Ambiente ambiente, AudioOpcional opcional, Pageable pageable ){
		
		Session session = entityManager.unwrap( Session.class );

		Categoria categoriaOpcional = categoriaRepo.findByCodigo( Categoria.OPCIONAL );
		
		Criteria critCount = createCriteriaMidiaOpcional( ambiente, opcional, session, categoriaOpcional );
		critCount.setProjection( Projections.rowCount() );
		Long total = (Long)critCount.uniqueResult();

		Criteria crit = createCriteriaMidiaOpcional( ambiente, opcional, session, categoriaOpcional );
		
		if ( pageable != null ){
			crit.setMaxResults( pageable.getPageSize() );
			crit.setFirstResult( pageable.getPageNumber() );
		}
		
		List<MidiaOpcional> listMidiaOpcional = crit.list();
		
		List<Midia> listMidias = new ArrayList<Midia>();

		listMidiaOpcional.forEach( mo -> {
			listMidias.add( mo.getMidia() );
		});
		
		PageImpl<Midia> paginaMidiasOpcionais = new PageImpl<Midia>( listMidias, pageable, total );
		
		return paginaMidiasOpcionais;
	}



	private Criteria createCriteriaMidiaOpcional( Ambiente ambiente, AudioOpcional opcional, Session session, Categoria categoriaOpcional )
	{
		Criteria crit = session.createCriteria( MidiaOpcional.class );
		crit.createAlias( "midia", "m", JoinType.INNER_JOIN );
		crit.createAlias( "m.categorias", "c", JoinType.INNER_JOIN );

		if ( ambiente != null ){
			crit.createAlias( "m.ambientes", "a", JoinType.INNER_JOIN );
			crit.add( Restrictions.eq( "a.idAmbiente", ambiente.getIdAmbiente() ) );
		}

		crit.add( Restrictions.eq( "c.idCategoria", categoriaOpcional.getIdCategoria() ) );
		
		if ( opcional != null )
			crit.add( Restrictions.eq( "opcional", opcional ) );

		return crit;
	}

	
	public List<RelatorioMidiaGeneroVO> findRelatorioGeneros(){
		return midiaGeneroRepo.findRelatorioGeneros();
	}

	
}
