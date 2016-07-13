package br.com.radio.enumeration;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

/**
 * Index começando em 1 
 * 
 * @author pazin
 *
 */
public enum DiaSemana {
	
	SEGUNDA( DayOfWeek.MONDAY.getValue() ),
	TERCA( DayOfWeek.TUESDAY.getValue() ),
	QUARTA( DayOfWeek.WEDNESDAY.getValue() ),
	QUINTA( DayOfWeek.THURSDAY.getValue() ),
	SEXTA( DayOfWeek.FRIDAY.getValue() ),
	SABADO( DayOfWeek.SATURDAY.getValue() ),
	DOMINGO( DayOfWeek.SUNDAY.getValue() );
	
	private Integer index;

	private DiaSemana( Integer index )
	{
		this.index = index;
	}

	public Integer getIndex()
	{
		return index;
	}

	public static DiaSemana getByIndex( Integer index )
	{
		DiaSemana dia = SEGUNDA;
		
		for ( DiaSemana d : DiaSemana.values() )
		{
			if ( d.index.equals( index ) )
			{
				dia = d;
				break;
			}
		}
		
		return dia;
	}
	
	public static List<DiaSemana> getListByIndex( Long[] arrayDias ){
		
		List<DiaSemana> result = new ArrayList<DiaSemana>();

		for ( Long idx : arrayDias ){
			DiaSemana dia = DiaSemana.getByIndex( idx.intValue() );
			
			if ( dia != null )
				result.add( dia );
		}
		
		return result;
	}
	

}
