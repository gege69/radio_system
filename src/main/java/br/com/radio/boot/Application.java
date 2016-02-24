package br.com.radio.boot;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import br.com.radio.model.Conversa;
import br.com.radio.model.Usuario;
import br.com.radio.repository.UsuarioRepository;
import br.com.radio.service.ConversaService;

/* LEMBRAR DE COMMENTAR ISSO AQUI POIS ALGUMAS TELAS DÃO CONFLITO COM O BOOT.... DESCOBRIR DEPOIS */


//@SpringBootApplication
//@ComponentScan( basePackages = { "br.com.radio.*" } )
//@EnableConfigurationProperties
//@ActiveProfiles({"default"})
//@EnableTransactionManagement
public class Application {
				
	public static void main(String[] aaaa)
	{
		ApplicationContext ctx = SpringApplication.run(Application.class, aaaa);

		Logger.getLogger("org.jaudiotagger").setLevel(Level.OFF);
		
		ConversaService convService = ctx.getBean( ConversaService.class );
		UsuarioRepository usuRepo = ctx.getBean( UsuarioRepository.class );
		
		Pageable pageable = new PageRequest( 10, 200 );
		
		Usuario usuario = usuRepo.findOne( 1l );
		
		
		Page<Conversa> conversas = convService.getListaConversasPorUsuario( usuario, pageable );
		
		List<Conversa> conversasList = conversas.getContent();
		
		conversasList.stream().forEach( c -> { 
			System.out.println( c.toString() );
		});
		
		
		System.out.println("fim");
																																																								
	}
	
}
