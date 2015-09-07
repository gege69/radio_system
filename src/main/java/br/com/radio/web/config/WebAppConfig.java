package br.com.radio.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

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

}
