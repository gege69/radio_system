package br.com.radio.web;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public abstract class AbstractController {
	
	public static final String APPLICATION_JSON_CHARSET_UTF_8 = "application/json; charset=utf-8";
	
	public static final Set<String> mimeImagensSet = new HashSet<String>();
	
	static { 
		mimeImagensSet.add( "image/gif" );
		mimeImagensSet.add( "image/jpeg" );
		mimeImagensSet.add( "image/png" );
		mimeImagensSet.add( "image/jpg" );
	}
	
	protected int qtd = 6;
	
	public int getStartByPage(int page){
		return page * qtd;
	}
	
	public int getLimitByPage(int page){
		return (page * qtd) + (qtd - 1);
	}
	
	protected int getPageZeroBased( Integer pageNumber )
	{
		if ( pageNumber == null )
			pageNumber = 0;

		if ( pageNumber > 0 )
			pageNumber--;
		
		return pageNumber;
	}
	
	protected Pageable getPageable( Integer pagina, Integer limit )
	{
		return getPageable( pagina, limit, null, null );
	}
	
	
	protected Pageable getPageable( Integer pagina, Integer limit, String order, String field )
	{
		pagina = getPageZeroBased( pagina );
		
		if ( limit == null || limit.equals( 0 ) )
			limit = this.qtd;
		
		Pageable pageable = null;
		
		if ( StringUtils.isNotBlank( order ) && StringUtils.isNotBlank( field ) )
			pageable = new PageRequest( pagina, limit, Sort.Direction.fromStringOrNull( order ), field );
		else
			pageable = new PageRequest( pagina, limit );
		
		return pageable;
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
	
	
	protected String getErrorsAsJSONErroMessage(BindingResult result){
		
		JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
		
		for ( FieldError error: result.getFieldErrors() ){
			
			JsonObject obj = Json.createObjectBuilder()
					.add("field", error.getField())
					.add("message", error.getDefaultMessage())
					.build();
				
			jsonArrayBuilder.add(obj);
		}

		for ( ObjectError error : result.getGlobalErrors() )
		{
			JsonObject obj = Json.createObjectBuilder()
					.add("field", "alertArea")
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

	protected String getOkResponse(){
		
		JsonObject obj = Json.createObjectBuilder().add("ok", 1 ).build();
		String jsonResult = obj.toString();
		
		return jsonResult;
	}
	
	
}
