package br.com.radio.boot;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import br.com.radio.model.Ambiente;
import br.com.radio.model.Categoria;
import br.com.radio.model.Midia;
import br.com.radio.repository.AmbienteRepository;
import br.com.radio.repository.MidiaRepository;
import br.com.radio.service.MidiaService;
import br.com.radio.service.ProgramacaoMusicalService;
import br.com.radio.service.programacaomusical.BlocosManipulacaoDTO;

/* LEMBRAR DE COMMENTAR ISSO AQUI POIS ALGUMAS TELAS DÃO CONFLITO COM O BOOT.... DESCOBRIR DEPOIS */


//@SpringBootApplication
//@ComponentScan( basePackages = { "br.com.radio.*" } )
//@EnableConfigurationProperties
//@ActiveProfiles({"default"})
public class Application {
																
	public static void main(String[] aaaa)
	{
		ApplicationContext ctx = SpringApplication.run(Application.class, aaaa);
		
		MidiaService midiaService = ctx.getBean( MidiaService.class );
		
		ProgramacaoMusicalService serv = ctx.getBean( ProgramacaoMusicalService.class );
		AmbienteRepository ambRepo = ctx.getBean( AmbienteRepository.class );
																																																																																																																			
		Ambiente ambiente = ambRepo.findOne( 1L );
				
		serv.geraTransmissao( ambiente );
																																																								
		
	}
	
}
