package br.com.radio.conversao;

import java.io.IOException;

import org.apache.log4j.Logger;

import br.com.radio.model.Midia;

public class WrapperLAME_MP3toOGG {
	
	private final Logger logger = Logger.getLogger( WrapperLAME_MP3toOGG.class );
	
	private String commandTemplate = "/usr/bin/lame --mp3input -S %s %s %s %s.ogg";

	public boolean converte(Midia midia, ConverterParameters params ) {
		
		String comando = String.format( commandTemplate, params.getBitRate().getCommand(), midia.getFilepath(), midia.getFilepath() );
		
		int result;
		try
		{
			final Process p = Runtime.getRuntime().exec(comando);

			result = p.waitFor();
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
