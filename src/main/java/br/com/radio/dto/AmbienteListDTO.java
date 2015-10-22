package br.com.radio.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import br.com.radio.model.Ambiente;

public class AmbienteListDTO implements Serializable {

	private static final long serialVersionUID = 2390163286977944387L;
	
	private List<Ambiente> lista;

	public List<Ambiente> getLista()
	{
		return lista;
	}

	public void setLista( List<Ambiente> lista )
	{
		this.lista = lista;
	}
	
	
	public static void main(String[] aaaa)
	{
		
		
		LocalDateTime inicio = LocalDateTime.now();

		inicio = inicio.plusMinutes( 40 );
		
		System.out.println(inicio);
		
		inicio = inicio.plusMinutes( 1000 );
		
		System.out.println(inicio);

		
//		ThreadLocalRandom rnd = ThreadLocalRandom.current();
//		
//		double ds = rnd.nextDouble(0.12);
//		double ds2 = rnd.nextDouble(0.12);
//		double ds3 = rnd.nextDouble(0.12);
//		double ds4 = rnd.nextDouble(0.12);
//		
//		int result = rnd.nextInt(2);
//		
//		int result2 = rnd.nextInt(2);
//		int result3 = rnd.nextInt(2);
//		int result4= rnd.nextInt(2);
//
//		System.out.println( ds );
//		System.out.println( ds2 );
//		System.out.println( ds3 );
//		System.out.println( ds4 );
//		
//		System.out.println( result );
//		System.out.println( result2 );
//		System.out.println( result3 );
//		System.out.println( result4 );
	}
	
}
