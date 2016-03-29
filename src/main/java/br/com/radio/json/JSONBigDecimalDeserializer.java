package br.com.radio.json;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class JSONBigDecimalDeserializer extends JsonDeserializer<BigDecimal> {

	@Override
    public BigDecimal deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext) throws IOException, JsonProcessingException {

        String value = jsonparser.getText();
        
        if ( StringUtils.isBlank( value ) )
        	return null;
        
		try
		{
			DecimalFormatSymbols symbols = new DecimalFormatSymbols();
			symbols.setGroupingSeparator('.');
			symbols.setDecimalSeparator(',');
			String pattern = "###,##0.0#";
			DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
			decimalFormat.setParseBigDecimal(true);

			BigDecimal bigDecimal = (BigDecimal) decimalFormat.parse( value ); 
			
			return bigDecimal;
		}
		catch ( ParseException e )
		{
			e.printStackTrace();
			throw new RuntimeException( e );
		}


    }

}
