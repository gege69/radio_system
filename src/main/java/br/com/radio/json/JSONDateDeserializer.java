package br.com.radio.json;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class JSONDateDeserializer extends JsonDeserializer<Date> {

	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	
	private SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
	
	@Override
    public Date deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext) throws IOException, JsonProcessingException {

        String date = jsonparser.getText();
        
        if ( StringUtils.isBlank( date ) )
        	return null;
        
		try
		{
			if ( StringUtils.length( date ) > 10 )
				return sdf.parse( date );
			else
				return sdf2.parse( date );
		}
		catch ( ParseException e )
		{
			e.printStackTrace();
			throw new RuntimeException( e );
		}

    }

}
