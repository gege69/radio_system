package br.com.radio.util;

import java.io.PrintWriter;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Essa classe serve para monitorar a performance do programa atrav�s de ganchos.
 * 
 * @author pazin
 *
 */
public class PerfUtils {

	private static class TimeUnit 
	{
		public Instant instante;
		public String label;

		public TimeUnit( Instant instante, String label )
		{
			super();
			this.instante = instante;
			this.label = label;
		}
	}
	
	private static Boolean ligado = true;
	
	private static Deque<TimeUnit> stack = new ArrayDeque<TimeUnit>();
	
	private static StringBuilder sb = new StringBuilder();

	public static void startTime( String msg )
	{
		if ( !ligado )
			return;
		
		for ( int i = 0 ; i < stack.size(); i++ )
			sb.append( "\t" );
		
		stack.push( new TimeUnit( Instant.now(), msg ) );
		
		sb.append( msg ).append( System.lineSeparator() );
	}

	public static void finishTime()
	{
		if ( !ligado )
			return;
		
		Instant agora = Instant.now();
		
		TimeUnit unit = stack.pop();
		
		for ( int i = 0 ; i < stack.size(); i++ )
			sb.append( "\t" );
		
		Long tempo2 = ChronoUnit.MILLIS.between( unit.instante, agora );

		Double duracao = tempo2 * 0.001;
		
		sb.append( unit.label ).append( " - " ).append( String.format("%.3f",duracao) ).append( " seg" ).append( System.lineSeparator() );
	}
	
	public static void reset()
	{
		sb = new StringBuilder();
		stack.clear();
		ligado = true;
	}

	public static void dump()
	{
		if ( !ligado )
			return;
		// parametrizar de acordo com o ambiente...
		PrintWriter out = null;
		try
		{
			out = new PrintWriter("c:/tmp/performance/perf_"+ System.currentTimeMillis() +".txt");
			
			if ( out != null)
			{
				out.println( sb.toString() );
				
				out.close();
			}
		}
		catch ( Exception e )
		{
			if ( out != null )
				out.close();
//			e.printStackTrace();   
		}
		
	}
	
	public static void desliga()
	{
		ligado = false;
	}

	
	public static void liga()
	{
		ligado = true;
	}
	
	/**
	 *  EXEMPLO DE USO
	 * 
	 * @param a
	 */
	public static void main(String[] a)
	{
		
		reset();
		
		try
		{
			startTime( "Metodo Main" );
			metodoLento();
			
			startTime( "submetodo" );
			metodoRapido();
			finishTime();
			
			startTime( "for" );
			for ( int i = 0; i < 4; i++)
			{
				startTime( "outro sub" );
				metodoMaisOuMenos();
				finishTime();
				
				startTime( "bloco dentro do for" );
				metodoRapido();
				metodoMaisOuMenos();
				finishTime();
			}
			finishTime();
			
			startTime( "finalizacao" );
			metodoComposto();
			finishTime();
			
			finishTime();
			
			
			System.out.println( sb.toString() );
			
			dump();

			//RESULTADO : 
			
			/*
				Metodo Main
					submetodo
					submetodo - 0,100 seg
					for
						outro sub
						outro sub - 2,002 seg
						bloco dentro do for
						bloco dentro do for - 2,107 seg
						outro sub
						outro sub - 2,004 seg
						bloco dentro do for
						bloco dentro do for - 2,102 seg
						outro sub
						outro sub - 2,002 seg
						bloco dentro do for
						bloco dentro do for - 2,102 seg
						outro sub
						outro sub - 2,007 seg
						bloco dentro do for
						bloco dentro do for - 2,102 seg
					for - 16,428 seg
					finalizacao
					finalizacao - 6,104 seg
				Metodo Main - 28,657 seg


			 */
			
			
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
				
	}
	
	
	public static void metodoRapido()
	{
		try
		{
			Thread.sleep( 100 );
		}
		catch ( InterruptedException e )
		{
			e.printStackTrace();
		}
	}
	
	
	public static void metodoMaisOuMenos()
	{
		try
		{
			Thread.sleep( 2000 );
		}
		catch ( InterruptedException e )
		{
			e.printStackTrace();
		}
	}
	
	public static void metodoLento()
	{
		try
		{
			Thread.sleep( 6000 );
		}
		catch ( InterruptedException e )
		{
			e.printStackTrace();
		}
	}
	
	public static void metodoComposto()
	{
		metodoLento();
		metodoRapido();
	}
}
