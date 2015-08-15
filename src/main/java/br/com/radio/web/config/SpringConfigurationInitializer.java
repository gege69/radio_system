package br.com.radio.web.config;


import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import br.com.radio.config.AppConfig;


public class SpringConfigurationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer
{
	@Override
	protected Class<?>[] getRootConfigClasses()
	{
		return new Class[] { AppConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses()
	{
		return null;
	}

	@Override
	protected String[] getServletMappings()
	{
		return new String[] { "/" };
	}

}
