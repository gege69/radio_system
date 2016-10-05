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
import org.springframework.security.core.userdetails.User;
import org.springframework.web.context.support.WebApplicationContextUtils;

import br.com.radio.model.AcessoUsuario;
import br.com.radio.model.Usuario;
import br.com.radio.repository.AcessoUsuarioRepository;
import br.com.radio.service.UsuarioService;

public class SessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated( HttpSessionEvent event )
	{
//		System.out.println("==== Session is created ====");
        event.getSession().setMaxInactiveInterval(10*60);

//		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
//
//        String ipAddr = request.getRemoteAddr();

//        HttpSession session = event.getSession();
//        
//        String sessionId = session.getId();
//
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        
//        boolean isAnonymous = auth instanceof AnonymousAuthenticationToken;
//        
//        if ( auth != null && auth.isAuthenticated() && !isAnonymous){
//
//        	String username = auth.getName();
//
//			ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
//			
//			AcessoUsuarioRepository acessoRepo = ctx.getBean( AcessoUsuarioRepository.class );
//			UsuarioService usuarioService = ctx.getBean( UsuarioService.class );
//			
//			Usuario usuario = usuarioService.findByLogin( username );
//
//			AcessoUsuario acesso = new AcessoUsuario();
//			
//			acesso.setDataCriacao( new Date() );
//			acesso.setSessionId( sessionId );
////			acesso.setEnderecoIp( ipAddr );
//			acesso.setUsuario( usuario );
//			
//			acessoRepo.save( acesso );
//			
//			System.out.println(acesso);
//        }
	}

	@Override
	public void sessionDestroyed( HttpSessionEvent event )
	{
//		System.out.println("==== Session is destroyed ====");

//		HttpSession session = event.getSession();
//
//        String sessionId = session.getId();
//
//		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
//		
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        
//        boolean isAnonymous = auth instanceof AnonymousAuthenticationToken;
//        
//        if ( auth != null && auth.isAuthenticated() && !isAnonymous ){
//				
//			User user = (User) auth.getPrincipal();
//
//			AcessoUsuarioRepository acessoRepo = ctx.getBean( AcessoUsuarioRepository.class );
//			UsuarioService usuarioService = ctx.getBean( UsuarioService.class );
//			
//			Usuario usuario = usuarioService.findByLogin( user.getUsername() );
//			
//			// tenta encontrar um acesso pelo sessionId que não esteja fechado
//			List<AcessoUsuario> acessos = acessoRepo.findBySessionIdAndUsuarioAndDataLogoutIsNull( sessionId, usuario );
//			
//			acessos.forEach( a -> {
//				a.setDataLogout( new Date() );
//			});
//			
//			acessoRepo.save( acessos );
//        }
	}

}
