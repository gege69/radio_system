package br.com.radio.web;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import br.com.radio.dto.ContatoDTO;
import br.com.radio.service.ClienteService;

@Controller
public class ContatoController extends AbstractController {
	
	private final Logger logger = Logger.getLogger( ContatoController.class );

	@Autowired
	private ClienteService clienteService;
	

	@Override
	protected Logger getLogger()
	{
		return this.logger;
	}

	
	@RequestMapping(value="/contato", method=RequestMethod.POST)
	public ResponseEntity<?> registrar( @Valid ContatoDTO contatoDto, BindingResult result, WebRequest request, Errors errors )
	{
//		ModelAndView modelAndView = new ModelAndView();
		
		// Importante proteger contra brute force aqui....
		if ( !result.hasErrors() )
		{
			try
			{

				
				
				return new ResponseEntity<>( HttpStatus.OK );
			}
			catch ( Exception e )
			{
				return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR );
			}

		}
		
		return new ResponseEntity<>( HttpStatus.NOT_ACCEPTABLE );
	}
	
	
}
