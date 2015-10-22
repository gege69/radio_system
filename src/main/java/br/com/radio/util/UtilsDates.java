package br.com.radio.util;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
	
	public static boolean isOverlapping( Date start1, Date end1, Date start2, Date end2 )
	{
		return start1.before( end2 ) && start2.before( end1 );
	}
	
	public static Date fromLocalDateTime( LocalDateTime in )
	{
		Date out = Date.from( in.atZone( ZoneId.systemDefault() ).toInstant() );
		
		return out;
	}
	
	public static LocalDateTime fromDate( Date in )
	{
		LocalDateTime out = LocalDateTime.ofInstant( in.toInstant(), ZoneId.systemDefault() );

		return out;
	}

}
