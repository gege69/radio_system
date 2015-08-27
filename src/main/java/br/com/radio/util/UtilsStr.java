package br.com.radio.util;

import javax.json.Json;
import javax.json.JsonObject;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

public class UtilsStr {

	
	public static String notNull( String str )
	{
		if ( str == null )
			return "";
		else if ( "null".equalsIgnoreCase(str) )
			return "";
		else
			return str;
	}
	
	
	public static Boolean boolStr( String str )
	{
		if ( StringUtils.isBlank( str ) )
			return false;
		else
			return Boolean.valueOf( str );
		
	}
	
	
	/**
	 * Essa rotina vai criar um hash (o mais único possível) dos textos que forem passados por parâmetro para poder detectar alterações.
	 * 
	 * Tambeḿ serve para detectar uma versão do texto no caso de upload de registros antigos. 
	 *  
	 * @param args
	 * @return
	 */
	public static String hash( String... args )
	{
		String acc = "";
		
		if ( args == null || args.length == 0 )
			throw new RuntimeException("Não é possível fazer hash de valor nenhum...");
		
		for ( String str : args )
			acc += StringUtils.trim( notNull( str ) );
		
		String result = DigestUtils.shaHex(acc);
		
		return result;
	}

	
	public static String notBlank( String str )
	{
		if ( str == null || "".equals( str ) )
			return null;
		else
			return str;
				
	}
	
	
	public static String fastJSON( String field, String msg )
	{
		JsonObject obj = Json.createObjectBuilder()
				.add(field, msg)
				.build();
		
		return obj.toString();
	}
	
	
	
	
	
	
	
	
	public static void main(String[] aaa )
	{
		String result = hash( "I", "ANIMAIS VIVOS E PRODUTOS DO REINO ANIMAL", "Notas. 1 - Na presenta Seção qualquer referência a um gênero particular ou a uma espécia particular de animal aplica-se também, salvo disposições em contrário, aos animais jovens desse gênero ou dessa espécie.");
		
		System.out.println( result );

		result = hash( "I", "ANIMAIS VIVOS E PRODUTOS DO REINO ANIMAL", "Notas 1 - Na presenta Seção qualquer referência a um gênero particular ou a uma espécia particular de animal aplica-se também, salvo disposições em contrário, aos animais jovens desse gênero ou dessa espécie."); 
		
		System.out.println( result );
	}
}
