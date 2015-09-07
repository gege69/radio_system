package br.com.radio.util;

import java.lang.reflect.Field;

import org.apache.commons.lang3.StringUtils;

import br.com.radio.model.Ambiente;

public class ExpandHTMLHelper {


	public static String fieldTemplate = 
			"<div class=\"form-group\">" + 
					"  <label for=\"login\" class=\"control-label col-sm-2 col-md-3\">%s</label>" + 
					"  <div class=\"col-sm-10 col-md-8\">" + 
					"	<input type=\"text\" class=\"form-control\" id=\"%s\" name=\"%s\">" + 
					"  </div>" + 
					"</div>";	 
			
	
	public static void main(String[] aaaa)
	{
		StringBuilder sb = new StringBuilder();
		
		String sp = System.lineSeparator();

		sb.append( "<div class=\"form-group\">" ).append( sp );
		sb.append( "  <label for=\"login\" class=\"control-label col-sm-2 col-md-3\">%s</label>" ).append( sp );
		sb.append( "  <div class=\"col-sm-10 col-md-8\">" ).append( sp ); 
		sb.append( "	<input type=\"text\" class=\"form-control\" id=\"%s\" name=\"%s\">" ).append( sp );
		sb.append( "  </div>" ).append( sp );
		sb.append( "</div>" ).append( sp );
		
        try
		{
			Field[] fields = (Ambiente.class).getDeclaredFields();
			
			for ( Field f : fields )
			{

				String[] palavras = StringUtils.splitByCharacterTypeCamelCase( f.getName() );
				
				String label = "";
				
				for ( String s : palavras )
				{
					label = label + s + " "; 
				}
				
				String msg = String.format( sb.toString(), StringUtils.capitalize( label.trim() ), f.getName() + "_amb", f.getName() );
				
				System.out.println( msg ); 
			}
			
			
		}
		catch ( Exception e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		
		
		
	}
	
}