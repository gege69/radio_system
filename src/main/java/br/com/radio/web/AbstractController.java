package br.com.radio.web;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.HandlerMapping;

import br.com.radio.model.Ambiente;
import br.com.radio.model.Usuario;

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

	protected abstract Logger getLogger();
	

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleError(HttpServletRequest req, Exception exception) {
		
		imprimeLogErro( "", req, exception );

		String jsonResult = writeSingleErrorAsJSONErroMessage( "erro", exception.getMessage() );
		
		return respondeErro500( jsonResult );
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
	
	
	protected String writeErrorsAsJSONErroMessage(BindingResult result){
		
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
	
	
	protected String writeSingleErrorAsJSONErroMessage(String field, String message ){

		if ( StringUtils.isBlank( message ) )
			message = "Erro no sistema (null)";

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


	protected String writeOkResponse(){
		
		JsonObject obj = Json.createObjectBuilder().add("ok", 1 ).build();
		String jsonResult = obj.toString();
		
		return jsonResult;
	}
	

	@SuppressWarnings( "unchecked" )
	protected boolean hasAuthority(String authName)
	{
		List<SimpleGrantedAuthority> authoritiesList = (List<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		
		boolean result = authoritiesList.stream().anyMatch( a -> a != null && a.getAuthority().equals( authName ) );
		
		return result;
	}
	

	protected boolean isAutorizado( Usuario usuario, Long idClienteRequest )
	{
		boolean podeEditarOutrosClientes = hasAuthority( "ADM_SISTEMA" );
		boolean clienteDiferente = !idClienteRequest.equals( usuario.getCliente().getIdCliente() );

		return !( clienteDiferente && !podeEditarOutrosClientes );
	}
	
	protected void imprimeLogErroAmbiente( Long idAmbiente, HttpServletRequest request, Throwable e ){
		this.imprimeLogErroAmbiente( "", idAmbiente, request, e );
	}

	protected void imprimeLogErroAmbiente( String mensagem, Long idAmbiente, HttpServletRequest request, Throwable e ){

		if ( idAmbiente != null && idAmbiente != null)
			mensagem = mensagem + String.format(" Id Ambiente %d", idAmbiente);

		this.imprimeLogErro( mensagem, request, e );
	}

	protected void imprimeLogErroAmbiente( Ambiente ambiente, HttpServletRequest request, Throwable e ){
		this.imprimeLogErroAmbiente( "", ambiente, request, e );
	}

	protected void imprimeLogErroAmbiente( String mensagem, Ambiente ambiente, HttpServletRequest request, Throwable e ){

		if ( ambiente != null && ambiente.getNome() != null)
			mensagem = mensagem + String.format(" Ambiente %s", ambiente.getNome());

		this.imprimeLogErro( mensagem, request, e );
	}
	
	protected void imprimeLogErro( String mensagem, HttpServletRequest request, Throwable e ){
		String pattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
		Logger logger = getLogger();
		logger.error( String.format( "Erro %s | %s", pattern, mensagem ), e );
	}


	protected <T> ResponseEntity<T> respondeErro500( T resultado ){
		return new ResponseEntity<T>(resultado, HttpStatus.INTERNAL_SERVER_ERROR );
	}

	protected <T> ResponseEntity<T> respondeOk200( T resultado ){
		return ResponseEntity.ok(resultado);
	}

}
