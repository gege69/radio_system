package br.com.radio.json;

import java.io.IOException;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

import br.com.radio.model.Ambiente;
import br.com.radio.model.AmbienteEmail;

public class JSONAmbienteEmailDeserializer extends JsonDeserializer<AmbienteEmail> {
	
	@Override
	public AmbienteEmail deserialize( JsonParser jp, DeserializationContext ctxt ) throws IOException, JsonProcessingException
	{
		JsonNode jsonNode = jp.getCodec().readTree(jp);
		
		JSONConverter conv = new JSONConverter();
		
		Ambiente ambiente = new Ambiente();

		Long id = conv.toLong( jsonNode, "ambiente.id_ambiente_amb" );
		if ( id != null )
			ambiente.setId_ambiente_amb( id );
		
		AmbienteEmail email = new AmbienteEmail();
		
		email.setDs_email_aml( conv.toStr( jsonNode, "email.ds_email_aml" ) );
		
		email.setAmbiente( ambiente );
		
		return email;
	
	}
	

}
