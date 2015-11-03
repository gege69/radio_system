package br.com.radio.security.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import br.com.radio.enumeration.UsuarioTipo;
import br.com.radio.model.Usuario;
import br.com.radio.repository.UsuarioRepository;

@Component
public class SimpleAuthenticationHandler implements AuthenticationSuccessHandler {

	private static final Logger logger = Logger.getLogger(SimpleAuthenticationHandler.class);

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Override
	public void onAuthenticationSuccess( HttpServletRequest request, HttpServletResponse response, Authentication authentication ) throws IOException, ServletException
	{
		// TODO Auto-generated method stub
		handle( request, response, authentication );
		clearAuthenticationAttributes(request);
	}

	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
		String targetUrl = determineTargetUrl(authentication);
 
        if (response.isCommitted()) {
            logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }

        redirectStrategy.sendRedirect(request, response, targetUrl);
    }
	
    protected String determineTargetUrl(Authentication authentication) 
    {
        boolean isUser = false;
        boolean isPlayer = false;
        
        User user = (User) authentication.getPrincipal();

        Usuario usuario = usuarioRepo.findByLogin( user.getUsername() );
        
        if ( usuario != null )
        {
        	isUser = usuario.getUsuarioTipo().equals( UsuarioTipo.GERENCIADOR );
        	isPlayer = usuario.getUsuarioTipo().equals( UsuarioTipo.PLAYER );
        }
        
        
        // redirect está enviando como se fosse o usuario antigo... mas no chrome incognito funciona
        
        if (isUser) {
            return "/principal";
        } else if (isPlayer) {
            return "/player";
        } else {
            throw new IllegalStateException();
        }
    }

    
    protected void clearAuthenticationAttributes(HttpServletRequest request) 
    {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
 
    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }
    
    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }
	
}
