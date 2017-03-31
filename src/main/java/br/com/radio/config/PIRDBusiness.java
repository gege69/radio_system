package br.com.radio.config;

import java.io.IOException;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class PIRDBusiness {


    public PropertiesPropertySource getDuxusPropertySource() {

    	// Obtendo o PIRD
		Resource r = new ClassPathResource( "pird.properties" );
		
		// Entrando dentro do PIRD e realizando a regra de negócio de olhar o endereço de IP
		// Vai retornar o path configDir
		String path_duxus_properties = this.processaDecisaoPathDuxusProperties( r );
		
		// Obtendo o arquivo do duxus.properties
	    Resource resource = new FileSystemResource(path_duxus_properties); 

	    // Preenchendo uma classe de Properties com o conteúdo do duxus.properties
	    Properties result = new Properties();
	    try
		{
			PropertiesLoaderUtils.fillProperties(result, resource);
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}

	    // Criando uma "property source" igual à annotation de maneira programática com o nome de "duxus.properties"
		PropertiesPropertySource prop = new PropertiesPropertySource("duxus.properties", result);

        return prop;
    }


	private String processaDecisaoPathDuxusProperties(Resource resource){
		
		Properties propsPIRD = null;
		try
		{
			propsPIRD = PropertiesLoaderUtils.loadProperties(resource);
		}
		catch ( IOException e )
		{
			// tratar caso não consiga ler...
			e.printStackTrace();
		}	
		
		// Seja lá como é obtido o endereço de IP;
		String enderecoIP = obtemEnderecoIP();
		
		// Percorrendo e filtrando o pird.properties e pegando o primeiro que tiver o mesmo Endereço de IP da máquina
		Entry<Object, Object> entry = propsPIRD.entrySet().stream().filter( e -> enderecoIP.equals( e.getValue() ) ).findFirst().get();
		
		System.out.println(entry);
		
		// Uma vez obtido o par (chave,valor) vamos usar a chave e arrancar o que interessa
		String chave = (String) entry.getKey();
		
		String chaveProcessada = StringUtils.remove( chave, ".identificador" );
		
		// Utilizando o pedaço que "interessa" da chave para obter FINALMENTE o valor da chave configDir
		String path = propsPIRD.getProperty( chaveProcessada + ".configDir" );
		
		System.out.println(path);
		
		// Montando o resultado
		return path;
	}
	
	
	private String obtemEnderecoIP(){
		// alguma coisa aqui pra determinar o IP da máquina... 
		return "10.200.2.212";
	}
}
