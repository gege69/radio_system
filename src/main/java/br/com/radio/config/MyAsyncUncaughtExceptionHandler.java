package br.com.radio.config;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

public class MyAsyncUncaughtExceptionHandler implements AsyncUncaughtExceptionHandler {
	
	private final Logger logger = Logger.getLogger( MyAsyncUncaughtExceptionHandler.class );

	@Override
	public void handleUncaughtException( Throwable ex, Method method, Object... params )
	{
		logger.error( String.format( "Erro do Async | Método: %s ", method.getName() ), ex );
	}

}
