package br.com.radio.web.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

@Configuration
@EnableWebMvc
public class WebAppConfig extends WebMvcConfigurerAdapter {

	@Override
    public void configureDefaultServletHandling( DefaultServletHandlerConfigurer configurer )
    {
        configurer.enable();
    }
	
	@Bean
	public InternalResourceViewResolver getInternalResourceViewResolver()
	{
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setExposeContextBeansAsAttributes( true );
		
		return resolver;
	}
	
	@Bean
	public StandardServletMultipartResolver multipartResolver()
	{
		StandardServletMultipartResolver resolver = new StandardServletMultipartResolver();
	    return resolver;	
	}
	
		
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("/");
	}

//	
//	/* Here we register the Hibernate4Module into an ObjectMapper, then set this custom-configured ObjectMapper
//     * to the MessageConverter and return it to be added to the HttpMessageConverters of our application*/
//    public MappingJackson2HttpMessageConverter jacksonMessageConverter(){
//        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
//
//        ObjectMapper mapper = new ObjectMapper();
//        //Registering Hibernate4Module to support lazy objects
//        
//        Hibernate4Module module = new Hibernate4Module();
//        module.enable( Hibernate4Module.Feature.FORCE_LAZY_LOADING );
//        
//        mapper.registerModule( module );
//
//        messageConverter.setObjectMapper(mapper);
//        return messageConverter;
//
//    }
//
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        //Here we add our custom-configured HttpMessageConverter
//        converters.add(jacksonMessageConverter());
//        super.configureMessageConverters(converters);
//    }
//	
}
