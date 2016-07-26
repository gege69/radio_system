package br.com.radio.json;

import java.io.IOException;

import br.com.radio.conversao.VariableBitRateOption;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class VariableBitRateOptionSerializer extends JsonSerializer<VariableBitRateOption> {

	@Override
	public void serialize( VariableBitRateOption value, JsonGenerator gen, SerializerProvider serializers ) throws IOException, JsonProcessingException
	{
		gen.writeStartObject();
        gen.writeFieldName("valor");
        gen.writeString(value.getValor());
        gen.writeFieldName("descricao");
        gen.writeString(value.getDescricao());
        gen.writeEndObject();
	}

}
