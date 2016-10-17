package br.com.radio.util;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

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
			return "";
		
		if ( StringUtils.isBlank(pattern) )
			pattern = "dd/MM/yyyy";
		
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		
		String value = null;
		
		try{
			value = sdf.format(date);
		}
		catch( Exception ex ){
			value = "";
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
	
	
	
	
	
	
	
	/**
     * Calls {@link #asLocalDate(Date, ZoneId)} with the system default time zone.
     */
    public static LocalDate asLocalDate(java.util.Date date) {
        return asLocalDate(date, ZoneId.systemDefault());
    }

    /**
     * Creates {@link LocalDate} from {@code java.util.Date} or it's subclasses. Null-safe.
     */
    public static LocalDate asLocalDate(java.util.Date date, ZoneId zone) {
        if (date == null)
            return null;

        if (date instanceof java.sql.Date)
            return ((java.sql.Date) date).toLocalDate();
        else
            return Instant.ofEpochMilli(date.getTime()).atZone(zone).toLocalDate();
    }

    /**
     * Calls {@link #asLocalDateTime(Date, ZoneId)} with the system default time zone.
     */
    public static LocalDateTime asLocalDateTime(java.util.Date date) {
        return asLocalDateTime(date, ZoneId.systemDefault());
    }

    /**
     * Creates {@link LocalDateTime} from {@code java.util.Date} or it's subclasses. Null-safe.
     */
    public static LocalDateTime asLocalDateTime(java.util.Date date, ZoneId zone) {
        if (date == null)
            return null;

        if (date instanceof java.sql.Timestamp)
            return ((java.sql.Timestamp) date).toLocalDateTime();
        else
            return Instant.ofEpochMilli(date.getTime()).atZone(zone).toLocalDateTime();
    }

    /**
     * Calls {@link #asUtilDate(Object, ZoneId)} with the system default time zone.
     */
    public static java.util.Date asUtilDate(Object date) {
        return asUtilDate(date, ZoneId.systemDefault());
    }

    /**
     * Creates a {@link java.util.Date} from various date objects. Is null-safe. Currently supports:<ul>
     * <li>{@link java.util.Date}
     * <li>{@link java.sql.Date}
     * <li>{@link java.sql.TimeStamp}
     * <li>{@link java.time.LocalDate}
     * <li>{@link java.time.LocalDateTime}
     * <li>{@link java.time.ZonedDateTime}
     * <li>{@link java.time.Instant}
     * </ul>
     * 
     * @param zone Time zone, used only if the input object is LocalDate or LocalDateTime.
     * 
     * @return {@link java.util.Date} (exactly this class, not a subclass, such as java.sql.Date)
     */
    public static java.util.Date asUtilDate(Object date, ZoneId zone) {
        if (date == null)
            return null;

        if (date instanceof java.sql.Date || date instanceof java.sql.Timestamp)
            return new java.util.Date(((java.util.Date) date).getTime());
        if (date instanceof java.util.Date)
            return (java.util.Date) date;
        if (date instanceof LocalDate)
            return java.util.Date.from(((LocalDate) date).atStartOfDay(zone).toInstant());
        if (date instanceof LocalDateTime)
            return java.util.Date.from(((LocalDateTime) date).atZone(zone).toInstant());
        if (date instanceof ZonedDateTime)
            return java.util.Date.from(((ZonedDateTime) date).toInstant());
        if (date instanceof Instant)
            return java.util.Date.from((Instant) date);

        throw new UnsupportedOperationException("Don't know hot to convert " + date.getClass().getName() + " to java.util.Date");
    }

    /**
     * Creates an {@link Instant} from {@code java.util.Date} or it's subclasses. Null-safe.
     */
    public static Instant asInstant(Date date) {
        if (date == null)
            return null;
        else
            return Instant.ofEpochMilli(date.getTime());
    }

    /**
     * Calls {@link #asZonedDateTime(Date, ZoneId)} with the system default time zone.
     */
    public static ZonedDateTime asZonedDateTime(Date date) {
        return asZonedDateTime(date, ZoneId.systemDefault());
    }

    /**
     * Creates {@link ZonedDateTime} from {@code java.util.Date} or it's subclasses. Null-safe.
     */
    public static ZonedDateTime asZonedDateTime(Date date, ZoneId zone) {
        if (date == null)
            return null;
        else
            return asInstant(date).atZone(zone);
    }

    
    
    public static Date asUtilMidnightDate( Object date )
    {
    	Date result = asUtilDate(date, ZoneId.systemDefault());

    	try
		{
			result = DateUtils.setHours( result, 0 );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			result = DateUtils.setHours( result, 1 );
		}

    	result = DateUtils.setMinutes( result, 0 );

    	result = DateUtils.setSeconds( result, 0 );
    	result = DateUtils.setMilliseconds( result, 0 );
    	
    	return result;
    }
    
    
    public static Date asUtilLastSecondDate( Object date )
    {
    	Date result = asUtilDate(date, ZoneId.systemDefault());

    	result = DateUtils.setHours( result, 23 );
    	result = DateUtils.setMinutes( result, 59 );
    	result = DateUtils.setSeconds( result, 59 );
    	result = DateUtils.setMilliseconds( result, 999 );
    	
    	return result;
    }
    
}

