package br.com.radio.web.listener;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.support.WebApplicationContextUtils;

import br.com.radio.model.AcessoUsuario;
import br.com.radio.model.Usuario;
import br.com.radio.repository.AcessoUsuarioRepository;
import br.com.radio.service.UsuarioService;

public class SessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated( HttpSessionEvent event )
	{
        event.getSession().setMaxInactiveInterval(10*60);
	}

	@Override
	public void sessionDestroyed( HttpSessionEvent event )
	{
//		System.out.println("==== Session is destroyed ====");

		HttpSession session = event.getSession();

        String sessionId = session.getId();

		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
		
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        boolean isAnonymous = auth instanceof AnonymousAuthenticationToken;
        
		List<AcessoUsuario> acessos = null;

		AcessoUsuarioRepository acessoRepo = ctx.getBean( AcessoUsuarioRepository.class );

        if ( auth != null && auth.isAuthenticated() && !isAnonymous ){
				
        	String username = auth.getName();

			UsuarioService usuarioService = ctx.getBean( UsuarioService.class );
			
			Usuario usuario = usuarioService.findByLogin(username);
			
			// tenta encontrar um acesso pelo sessionId que não esteja fechado
			acessos = acessoRepo.findBySessionIdAndUsuarioAndDataLogoutIsNull( sessionId, usuario );
        }
        else {
			acessos = acessoRepo.findBySessionIdAndDataLogoutIsNull( sessionId );
        }

        if ( acessos != null ){
			acessos.forEach( a -> {
				a.setDataLogout( new Date() );
			});
			
			acessoRepo.save( acessos );
        }

	}
	
	

}
