package br.com.radio.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import br.com.radio.service.UsuarioService;

@Component
public class SpringContextListener {

	@Autowired
	private UsuarioService usuarioService;
	
	@EventListener
    public void handleContextRefresh(ContextRefreshedEvent event) {
		usuarioService.updateTodosAcessosAbertos();
		System.out.println("SpringContextListener : Atualizando acessos abertos"  + ( usuarioService != null));
		
    }

}
