package br.com.radio.boot;

import java.time.LocalDateTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import br.com.radio.model.Ambiente;
import br.com.radio.model.Transmissao;
import br.com.radio.repository.AmbienteRepository;
import br.com.radio.repository.TransmissaoRepository;
import br.com.radio.service.EventoService;
import br.com.radio.util.UtilsDates;

/* LEMBRAR DE COMMENTAR ISSO AQUI POIS ALGUMAS TELAS DÃO CONFLITO COM O BOOT.... DESCOBRIR DEPOIS */


//@SpringBootApplication
//@ComponentScan( basePackages = { "br.com.radio.*" } )
//@EnableConfigurationProperties
//@ActiveProfiles({"default"})
public class Application {
																
	public static void main(String[] aaaa)
	{
		ApplicationContext ctx = SpringApplication.run(Application.class, aaaa);
		
//		MidiaService midiaService = ctx.getBean( MidiaService.class );
		
//		ProgramacaoMusicalService serv = ctx.getBean( ProgramacaoMusicalService.class );
		AmbienteRepository ambRepo = ctx.getBean( AmbienteRepository.class );
																																																																																																																			
		Ambiente ambiente = ambRepo.findOne( 2L );
		
		EventoService eventoService = ctx.getBean( EventoService.class );
		
		eventoService.criaTransmissaoDosEventos( ambiente );

//		TransmissaoRepository tRepo = ctx.getBean( TransmissaoRepository.class );
		
//		LocalDateTime agora = LocalDateTime.now().withHour( 17 ).withMinute( 07 );
		
//		Transmissao result = tRepo.findByIdAmbienteAndLinkativoTrueAndDataPrevisaoplay( ambiente.getIdAmbiente(), UtilsDates.fromLocalDateTime( agora ) );

		
		System.out.println("fim");
																																																								
		
	}
	
}
