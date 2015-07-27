package br.com.radio.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

import br.com.radio.model.Ambiente;
import br.com.radio.model.AmbienteEmail;
import br.com.radio.model.AmbienteEndereco;

public class JSONAmbienteDeserializer extends JsonDeserializer<Ambiente> {
	
	@Override
	public Ambiente deserialize( JsonParser jp, DeserializationContext ctxt ) throws IOException, JsonProcessingException
	{
		JsonNode jsonNode = jp.getCodec().readTree(jp);
		
		JSONConverter conv = new JSONConverter();
		
		Ambiente ambiente = new Ambiente();

		Long id = conv.toLong( jsonNode, "ambiente.id_ambiente_amb" );
		if ( id != null )
			ambiente.setId_ambiente_amb( id );
		
		ambiente.setNm_ambiente_amb( conv.toStr( jsonNode, "ambiente.nm_ambiente_amb" ) );
		ambiente.setCd_login_amb( conv.toStr( jsonNode, "ambiente.cd_login_amb" ) );
		ambiente.setCd_password_amb( conv.toStr( jsonNode, "ambiente.cd_password_amb" ) );
		
		AmbienteEmail email = new AmbienteEmail();
		email.setDs_email_aml( conv.toStr( jsonNode, "ambiente.ds_email_aml" ) );
		
		List<AmbienteEmail> emails = new ArrayList<AmbienteEmail>();
		emails.add( email );
		ambiente.setEmails( emails );
		
		ambiente.setCd_telefone1_amb( conv.toStr( jsonNode, "ambiente.cd_telefone1_amb" ) );
		ambiente.setCd_telefone2_amb( conv.toStr( jsonNode, "ambiente.cd_telefone2_amb" ) );
		ambiente.setDs_anotacoes_amb( conv.toStr( jsonNode, "ambiente.ds_anotacoes_amb" ) );
		
		AmbienteEndereco ende = new AmbienteEndereco();
		ende.setNm_logradouro_aen( conv.toStr( jsonNode, "ambiente.nm_logradouro_aen" ) );
		ende.setNm_bairro_aen( conv.toStr( jsonNode, "ambiente.nm_bairro_aen" ) );
		ende.setNm_estado_aen( conv.toStr( jsonNode, "ambiente.nm_estado_aen" ) );
		ende.setNm_cidade_aen( conv.toStr( jsonNode, "ambiente.nm_cidade_aen" ) );

		List<AmbienteEndereco> enderecos = new ArrayList<AmbienteEndereco>();
		enderecos.add( ende );
		ambiente.setEnderecos( enderecos );
		
		if ( ambiente.getId_ambiente_amb() != null && ambiente.getId_ambiente_amb() > 0 )
			ambiente.setDt_alteracao_amb( new Date() );
		
		return ambiente;
	
	}
	

}
