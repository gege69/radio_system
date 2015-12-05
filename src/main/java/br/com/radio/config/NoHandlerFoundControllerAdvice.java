package br.com.radio.config;

import org.springframework.web.servlet.NoHandlerFoundException;

//@ControllerAdvice
public class NoHandlerFoundControllerAdvice {

//	@ExceptionHandler(NoHandlerFoundException.class)
    public String handleNoHandlerFoundException(NoHandlerFoundException ex) {

        return "HTTPerror/404";
    }
	
}
