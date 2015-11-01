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
	
	
	
	/**
	 * Generate a CRON expression is a string comprising 6 or 7 fields separated by white space.
	 *
	 * @param seconds    mandatory = yes. allowed values = {@code  0-59    * / , -}
	 * @param minutes    mandatory = yes. allowed values = {@code  0-59    * / , -}
	 * @param hours      mandatory = yes. allowed values = {@code 0-23   * / , -}
	 * @param dayOfMonth mandatory = yes. allowed values = {@code 1-31  * / , - ? L W}
	 * @param month      mandatory = yes. allowed values = {@code 1-12 or JAN-DEC    * / , -}
	 * @param dayOfWeek  mandatory = yes. allowed values = {@code 0-6 or SUN-SAT * / , - ? L #}
	 * @param year       mandatory = no. allowed values = {@code 1970–2099    * / , -}
	 * @return a CRON Formatted String.
	 */
	public static String generateCronExpression(final String seconds, final String minutes, final String hours,
	                                             final String dayOfMonth,
	                                             final String month, final String dayOfWeek, final String year)
	{
	  return String.format("%1$s %2$s %3$s %4$s %5$s %6$s %7$s", seconds, minutes, hours, dayOfMonth, month, dayOfWeek, year);
	}
	
	
	
	
	public static void main(String[] aaa )
	{
		System.out.println( generateCronExpression( "55", "0", "19", "*", "OCT", "*", "*" ) );
	}
	
	
	
	public static boolean isAlphaNumeric(String s)
	{
		String pattern = "^[a-zA-Z0-9]*$";
		if ( s.matches( pattern ) )
			return true;
		
		return false;   
	}
}
