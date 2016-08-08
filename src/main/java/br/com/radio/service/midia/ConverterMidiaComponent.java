package br.com.radio.service.midia;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.PostConstruct;

import net.openhft.hashing.LongHashFunction;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import br.com.radio.conversao.BitRateType;
import br.com.radio.conversao.ConverterParameters;
import br.com.radio.conversao.VariableBitRateOption;
import br.com.radio.conversao.WrapperLAME_MP3toOGG;
import br.com.radio.enumeration.ParametrosType;
import br.com.radio.model.Midia;
import br.com.radio.model.Parametro;
import br.com.radio.repository.MidiaRepository;
import br.com.radio.repository.ParametroRepository;

import com.google.common.collect.Sets;


/**
 * Vai guardar a fila de conversão o método que chama o wrapper vai consumindo a fila um por um... 
 * 
 * Ao deletar uma música remover também da fila
 * Para cada novo upload da música verificar se está na fila e remover e inserir novamente no final
 * 
 * Uma tarefa agendada de cinco em cinco minutos verifica se tem algo na fila e se o processo está rodando.... em caso de [TRUE, FALSE] para as duas perguntas inicia o processo de consumir a fila novamente 
 * 
 * @author pazin
 *
 */

@Component
public class ConverterMidiaComponent {
	
	@Autowired
	private MidiaRepository midiaRepo;

	@Autowired
	private ParametroRepository paramRepo;
	
	private final Logger logger = Logger.getLogger( ConverterMidiaComponent.class );
	
	private BlockingQueue<Midia> fila = new LinkedBlockingQueue<Midia>();
	
	// Guava
	private Set<Midia> midiasSet = Sets.newConcurrentHashSet();
	
	private AtomicBoolean processando = new AtomicBoolean( false );
	
	private ConverterParameters parametros;
	
	private Map<String,String> mapExtToMimeType = new HashMap<String,String>();
	private Map<String,String> mapMimeTypeToExt = new HashMap<String,String>();
	
	public Map<String, String> getMapExtToMimeType()
	{
		return mapExtToMimeType;
	}

	public Map<String, String> getMapMimeTypeToExt()
	{
		return mapMimeTypeToExt;
	}
	
	@PostConstruct
	private void init(){
		this.mapExtToMimeType.put( "ogg", "audio/ogg" );
		this.mapExtToMimeType.put( "mp3", "audio/mpeg" );

		this.mapMimeTypeToExt.put( "audio/ogg", "ogg" );
		this.mapMimeTypeToExt.put( "audio/mpeg", "mp3" );
		this.mapMimeTypeToExt.put( "audio/mp3", "mp3" );
		this.mapMimeTypeToExt.put( "audio/mpeg3", "mp3" );
		
		parametros = getConverterParameters();
	}
	
	public boolean estaNaFila(Midia midia){
		return midiasSet.contains( midia );
	}
	
	public Map<Midia, Boolean> estaoNaFila(List<Midia> musicas){
		Map<Midia, Boolean> result = new HashMap<Midia, Boolean>();
		
		for ( Midia m : musicas){
			result.put( m, midiasSet.contains( m ) );
		}

		return result;
	}
	
	
	/**
	 * Dá um refresh com os dados que existem no banco de dados...
	 */
	public void refreshConverterParameters(){
		this.parametros = getConverterParameters();
	}

	public ConverterParameters getConverterParameters(){
				
		Parametro bitrate = paramRepo.findByCodigo( ParametrosType.BITRATE_TYPE.name() );
		Parametro bitrateValor = paramRepo.findByCodigo( ParametrosType.VALOR_BITRATE.name() );
		
		ConverterParameters params = new ConverterParameters();

		if ( bitrate != null && StringUtils.isNotBlank( bitrate.getValor()) )
			params.setBitRate( BitRateType.valueOf( bitrate.getValor()) );
		else
			params.setBitRate( BitRateType.VARIABLE );  // default
		
		if ( bitrateValor != null && StringUtils.isNotBlank( bitrateValor.getValor() ) )
			params.setValorBitRate( bitrateValor.getValor() );
		else
			params.setValorBitRate( VariableBitRateOption.CINCO.getValor() );  // default
		
		return params;
	}

	public void removerMidiaFila(Midia midia) {
		
		if (estaNaFila( midia ))
			return;
		
		fila.remove(  midia );
		midiasSet.remove( midia );
		logger.info( String.format("Removido da Fila [%s]", midia.getNome() ) );
	}

	
	public void addMidiaFila(Midia midia) {
		
		if (estaNaFila( midia ))
			return;
		
		try
		{
			fila.put( midia );
			midiasSet.add( midia );
			logger.info( String.format("Adicionado na Fila [%s]", midia.getNome() ) );
		}
		catch ( InterruptedException e )
		{
			logger.error( "Não foi possível adicionar na fila de Conversão", e );
			e.printStackTrace();
		}
	}

	public void addMidiasFila(List<Midia> midiaList){
		for ( Midia m : midiaList ) {
			this.addMidiaFila( m );
		}
	}

	public ConverterParameters getParametros()
	{
		return parametros;
	}

	public void setParametros( ConverterParameters parametros )
	{
		this.parametros = parametros;
	}
	
	
	public boolean isProcessando(){
		return processando.get();
	}

	@Async
	public void processaFila(){
		
		if ( processando.get() ){
			logger.info( "Já iniciado... SAINDO" );
			return;
		}
		else {
			try
			{
				if ( parametros == null )
					throw new RuntimeException("Parametros de Configuração estão null");
				
				logger.info( "Processando = true" );
				processando.set( true );
				
				processar();
				logger.info( "passou do processar" );
			}
			catch ( Exception e )
			{
				logger.error( "Erro na fila de conversão", e );
			}
			finally
			{
				processando.set( false );
				logger.info( "Processando = false" );
			}
		}
	}
	
	private void processar() throws BeansException, InterruptedException, IOException{
		
		WrapperLAME_MP3toOGG wrapper = new WrapperLAME_MP3toOGG();
		
		logger.info("Vai fazer o take");
		for ( Midia midia = fila.take(); midia != null; midia = fila.take() ){			

			ConverterParameters parametrosCopy = new ConverterParameters(); 
			BeanUtils.copyProperties( this.parametros, parametrosCopy );

			try
			{
				midia = midiaRepo.findOne( midia.getIdMidia() );
				
				if ( midia != null && midia.getValido() ){
						
					boolean result = wrapper.converte( midia, parametrosCopy );

					if ( result == true ){
						midia.setFilehashOriginal( midia.getFilehash() );
						midia.setFilesizeOriginal( midia.getFilesize() );
						
						String novoFilepath = String.format( "%s.ogg", midia.getFilepath() );
						File novaMidia = new File(novoFilepath);
						
						midia.setFilesize( Long.valueOf( novaMidia.length() ).intValue() );
						
						FileInputStream fis = new FileInputStream( novaMidia );
						byte[] bytes = IOUtils.toByteArray( fis );

						// Hash do novo OGG
						String hash = geraHashDoArquivo(bytes);
						
						midia.setFilehash( hash );
						
						String velhoFilePath = midia.getFilepath();
						
						midia.setFilepath( novoFilepath );
						
						midia.setMimetype( mapExtToMimeType.get( "ogg" ) );
						midia.setExtensao( "ogg" );

						File arquivoVelho = new File(velhoFilePath);
						arquivoVelho.delete();

						midiaRepo.save( midia );
					}
				}
				
			}
			finally
			{
				midiasSet.remove( midia );
			}
		}

		logger.info( "Fim do loop" );
	}


	public String geraHashDoArquivo( byte[] bytes ) throws IOException
	{
		LongHashFunction l = LongHashFunction.xx_r39();
		long hashXX = l.hashBytes( bytes, 0, bytes.length );

		String hash = Long.toString( hashXX );

		return hash;
	}

	public AtomicBoolean getProcessando()
	{
		return processando;
	}

	public void setProcessando( AtomicBoolean processando )
	{
		this.processando = processando;
	}

}
