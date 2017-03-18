package br.com.radio.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

@Configuration
@PropertySources({
	@PropertySource("classpath:captcha.properties"),
	@PropertySource("classpath:messages.properties")
})
public class PropertiesWithJavaConfig {
	
//	@Autowired
//	private Environment env;
	
	@Bean
	public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		
		PropertySourcesPlaceholderConfigurer c = new PropertySourcesPlaceholderConfigurer();
		
//		String caminhoPIRD = env.getRequiredProperty( "caminho.pird" );
		
		Resource[] resources = new Resource[ ] {
	            new ClassPathResource( "db.properties" ),
	            new FileSystemResource(caminhoPIRD)
	    };
		
		c.setLocations( resources );
		c.setIgnoreResourceNotFound( true );
		c.setIgnoreUnresolvablePlaceholders( false );
		
		return c;
	}
	
}
