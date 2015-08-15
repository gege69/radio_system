package br.com.radio.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginPageController extends AbstractController {

	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String home( HttpServletRequest request, ModelMap model )
	{
		return "login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login( HttpServletRequest request, ModelMap model )
	{
		CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        if (csrfToken != null) {
            model.addAttribute("csrfParameterName", csrfToken.getParameterName());
            model.addAttribute("csrfToken", csrfToken.getToken());
        }
		return "login";
	}
	
	
	@RequestMapping(value="/403", method=RequestMethod.GET)
	public String acessoNegado( HttpServletRequest request, ModelMap model )
	{
		return "system/403";
	}

	
}
