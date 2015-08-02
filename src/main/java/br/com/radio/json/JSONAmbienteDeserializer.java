package br.com.radio.json;

import java.io.IOException;

import br.com.radio.model.Ambiente;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

@Deprecated
public class JSONAmbienteDeserializer extends JsonDeserializer<Ambiente> {
	
	@Override
	public Ambiente deserialize( JsonParser jp, DeserializationContext ctxt ) throws IOException, JsonProcessingException
	{
//		JsonNode jsonNode = jp.getCodec().readTree(jp);
//		
//		JSONConverter conv = new JSONConverter();
//		
		Ambiente ambiente = new Ambiente();
//
//		Long id = conv.toLong( jsonNode, "ambiente.id_ambiente_amb" );
//		if ( id != null )
//			ambiente.setId_ambiente_amb( id );
//		
//		ambiente.setNm_ambiente_amb( conv.toStr( jsonNode, "ambiente.nm_ambiente_amb" ) );
//		ambiente.setCd_login_amb( conv.toStr( jsonNode, "ambiente.cd_login_amb" ) );
//		ambiente.setCd_password_amb( conv.toStr( jsonNode, "ambiente.cd_password_amb" ) );
//		
//		ambiente.setCd_telefone1_amb( conv.toStr( jsonNode, "ambiente.cd_telefone1_amb" ) );
//		ambiente.setCd_telefone2_amb( conv.toStr( jsonNode, "ambiente.cd_telefone2_amb" ) );
//		ambiente.setDs_anotacoes_amb( conv.toStr( jsonNode, "ambiente.ds_anotacoes_amb" ) );
//		
//		ambiente.setNm_logradouro_aen( conv.toStr( jsonNode, "ambiente.nm_logradouro_aen" ) );
//		ambiente.setNm_bairro_aen( conv.toStr( jsonNode, "ambiente.nm_bairro_aen" ) );
//		ambiente.setNm_estado_aen( conv.toStr( jsonNode, "ambiente.nm_estado_aen" ) );
//		ambiente.setNm_cidade_aen( conv.toStr( jsonNode, "ambiente.nm_cidade_aen" ) );
//
//		if ( ambiente.getId_ambiente_amb() != null && ambiente.getId_ambiente_amb() > 0 )
//			ambiente.setDt_alteracao_amb( new Date() );
//		
		return ambiente;
	
	}
	

}
