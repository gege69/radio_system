package br.com.radio.conversao;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;

import br.com.radio.model.Midia;

public class WrapperLAME_MP3toOGG {
	
	private final Logger logger = Logger.getLogger( WrapperLAME_MP3toOGG.class );
	
	private String commandTemplate = "/usr/bin/lame --mp3input -S %s %s %s %s.ogg";

	public boolean converte(Midia midia, ConverterParameters params ) {
		
		String commandBitRate = params.getBitRate().getCommand();
		String valor = params.getValorBitRate();
		String filePath = midia.getFilepath();
		
		String comando = String.format( commandTemplate, commandBitRate, valor, filePath, filePath );
		
		int result;
		try
		{
			logger.info( comando );

			final Process p = Runtime.getRuntime().exec(comando);

			result = p.waitFor();
			
			logger.info("Processado. Result : " + result);
		}
		catch ( IOException e )
		{
			logger.error( "Erro ao converter IO.", e );
			result = 2;
		}
		catch ( InterruptedException e )
		{
			logger.error( "Erro ao converter Interrupted.", e );
			result = 3;
		}

		return result == 0;
	}
	
}
