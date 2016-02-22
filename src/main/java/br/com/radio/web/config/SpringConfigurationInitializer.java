package br.com.radio.web.config;


import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import br.com.radio.config.AppConfig;
import br.com.radio.web.listener.SessionListener;


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
	
	@Override
	public void onStartup( ServletContext servletContext ) throws ServletException
	{
		super.onStartup( servletContext );
		servletContext.addListener( new SessionListener() );
	}

	@Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setMultipartConfig(getMultipartConfigElement());
    }
 
	
//	@Override
//    protected DispatcherServlet createDispatcherServlet(WebApplicationContext servletAppContext) {
//        final DispatcherServlet dispatcherServlet = super.createDispatcherServlet(servletAppContext);
//        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
//        return dispatcherServlet;
//    }
	
    private MultipartConfigElement getMultipartConfigElement() {
        MultipartConfigElement multipartConfigElement = new MultipartConfigElement( LOCATION, MAX_FILE_SIZE, MAX_REQUEST_SIZE, FILE_SIZE_THRESHOLD);
        return multipartConfigElement;
    }

    
    private static final String LOCATION = "/tmp/"; // Temporary location where files will be stored
 
    private static final long MAX_FILE_SIZE = 31457280; // 30MB : Max file size.
                                                        // Beyond that size spring will throw exception.
    private static final long MAX_REQUEST_SIZE = 36700160; // 35MB : Total request size containing Multi part.
     
    private static final int FILE_SIZE_THRESHOLD = 0; // Size threshold after which files will be written to disk
    

}
