package br.com.radio.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import br.com.radio.model.Ambiente;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public abstract class AbstractController {
	
	public static final String APPLICATION_JSON_CHARSET_UTF_8 = "application/json; charset=utf-8";
	
	protected int qtd = 6;
	
	public int getStartByPage(int page){
		return page * qtd;
	}
	
	public int getLimitByPage(int page){
		return (page * qtd) + (qtd - 1);
	}
	
	protected <T> List<T> paginacao(List<T> modelList, int start ){
		
		List<T> result = null;
		
		result = paginacao( modelList, start, null );
		
		return result;
	}

	protected <T> List<T> paginacao(List<T> modelList, int start, Integer limit){
		
		List<T> modelTempList = new ArrayList<T>();
		
		if ( limit >= modelList.size() )
			limit = modelList.size() - 1;
		
		for ( int pos = start; pos <= limit; pos++ ){
			
			modelTempList.add(modelList.get(pos));
		}
		
		return modelTempList;
	}
	
	protected String writeObjectAsString(Object obj){
		
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonResult = null;
		
		try {
			
			jsonResult = mapper.writeValueAsString(obj);
			
		} catch (Exception ex) {
			
			jsonResult = null;
		}
		
		return jsonResult;
	}
	
	public static void main(String[] aaa)
	{
		String str = "{\"id_ambiente_amb\":1,\"nm_ambiente_amb\":\"Cacau Show Afonso Brás\",\"cd_telefone1_amb\":\"+5511982616180\",\"cd_telefone2_amb\":null,\"cd_email1_amb\":\"pazinfernando@gmail.com\",\"cd_email2_amb\":null,\"ds_anotacoes_amb\":\"asfasdfasdf\",\"nm_logradouro_amb\":\"Rua Vitorio Azalin 655\",\"cd_numero_amb\":null,\"nm_bairro_amb\":\"asdfasdf\",\"nm_cidade_amb\":\"São Paulo\",\"nm_estado_amb\":\"SP\",\"cd_login_amb\":\"afonso\",\"cd_password_amb\":\"123456\",\"fl_opcionais_amb\":false,\"fl_sincronizar_amb\":false,\"fl_download_amb\":false,\"dt_criacao_amb\":\"02/08/2015 21:03\",\"usuarioCriacao\":null,\"dt_alteracao_amb\":null,\"fusoHorario\":{\"id_fusohorario_fuh\":3},\"id\":1}";
		ObjectMapper mapper = new ObjectMapper();
		
		try
		{
			Ambiente a = mapper.readValue( str, Ambiente.class );
			
			System.out.println(a);
		}
		catch ( IOException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	protected String getErrorsAsJSONErroMessage(BindingResult result){
		
		JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
		
		for ( FieldError error: result.getFieldErrors() ){
			
			JsonObject obj = Json.createObjectBuilder()
					.add("field", error.getField())
					.add("message", error.getDefaultMessage())
					.build();
				
			jsonArrayBuilder.add(obj);
		}
		
		JsonObject jsonObject = Json.createObjectBuilder()
				.add("errors", jsonArrayBuilder)
				.build();
		
		return jsonObject.toString();
	}
	
	
	protected String getSingleErrorAsJSONErroMessage(String field, String message ){
		
		JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
		
		JsonObject obj = Json.createObjectBuilder()
				.add("field", field)
				.add("message", message)
				.build();
			
		jsonArrayBuilder.add(obj);
		
		JsonObject jsonObject = Json.createObjectBuilder()
				.add("errors", jsonArrayBuilder)
				.build();
		
		return jsonObject.toString();
	}
	
}
