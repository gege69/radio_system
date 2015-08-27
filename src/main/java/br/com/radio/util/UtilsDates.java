package br.com.radio.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class UtilsDates {

	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public static Date toDate(String value){

		Date date = null;

		if ( StringUtils.isNotBlank(value) ){

			try{
				date = sdf.parse(value);
			}
			catch ( Exception ex ){
				date = null;
			}	
		}

		return date;
	}
	
	public static String format(Date date, String pattern){
		
		if ( date == null )
			return null;
		
		if ( StringUtils.isBlank(pattern) )
			pattern = "dd/MM/yyyy";
		
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		
		String value = null;
		
		try{
			value = sdf.format(date);
		}
		catch( Exception ex ){
			value = null;
		}
		
		return value;
	}
	
	public static String format(Date date){
		
		return format(date, null);
	}

}
