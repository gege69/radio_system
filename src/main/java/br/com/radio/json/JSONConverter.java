package br.com.radio.json;

import java.math.BigDecimal;
import java.util.Date;

import org.codehaus.jackson.JsonNode;
import org.springframework.stereotype.Component;

import br.com.radio.util.UtilsDates;
import br.com.radio.util.UtilsNumbers;
import br.com.radio.util.UtilsStr;

@Component
public class JSONConverter {

	public BigDecimal toDecimal( JsonNode jsonNode, String nomeCampo )
	{
		JsonNode field = jsonNode.get( nomeCampo );

		BigDecimal result = null;
		
		if ( field != null )
			result = UtilsNumbers.toDecimal( field.asText() );
		
		return result;
	}
	
	public Long toLong( JsonNode jsonNode, String nomeCampo )
	{
		JsonNode field = jsonNode.get( nomeCampo );

		Long result = null;
		
		if ( field != null )
			result = UtilsNumbers.toLong( field.asText() );
		
		return result;
	}

	public String toStr( JsonNode jsonNode, String nomeCampo )
	{
		JsonNode field = jsonNode.get( nomeCampo );

		String result = null;
		
		if ( field != null )
			result = field.asText();
		
		return result;
	}
	
	public Date toDate( JsonNode jsonNode, String nomeCampo )
	{
		JsonNode field = jsonNode.get( nomeCampo );

		Date result = null;
		
		if ( field != null )
			result = UtilsDates.toDate( field.asText() );
		
		return result;
	}
	
	public Boolean toBool( JsonNode jsonNode, String nomeCampo )
	{
		JsonNode field = jsonNode.get( nomeCampo );

		Boolean result = false;
		
		if ( field != null )
			result = UtilsStr.boolStr( field.asText() );
		
		return result;
	}
	
}
