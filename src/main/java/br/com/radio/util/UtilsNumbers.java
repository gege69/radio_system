package br.com.radio.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;

public class UtilsNumbers {
	
	private static NumberFormat nf = NumberFormat.getInstance( new Locale("pt", "BR") );
	
	public static Integer toInt(String value){

		if ( StringUtils.isBlank( value ) )
			return null;
		
		Integer i = null;

		try{
			i = Integer.parseInt(value);
		}
		catch (Exception ex){
			i = null;
		}

		return i;
	}

	
	public static String toStr( int valor )
	{
		String result = "";
		try{
			result = Integer.toString( valor );
		}
		catch (Exception ex){
			result = "";
		}
		
		return result;
	}
	
	

	
	
	public static Long toLong(String value){

		if ( StringUtils.isBlank( value ) )
			return null;
		
		Long i = null;
		
		try{
			i = Long.parseLong(value);
		}
		catch (Exception ex){
			i = null;
		}

		return i;
	}
	
	
	public static BigDecimal toDecimal(String value)
	{
		if ( StringUtils.isBlank( value ) )
			return null;
		
		BigDecimal b = null;
		
		try
		{
			b = new BigDecimal( nf.parse( value ).doubleValue() );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			b = null;
		}
				
		return b;
	}
	
	
	
}
