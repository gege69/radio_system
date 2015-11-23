package br.com.radio.schedule;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.radio.model.Ambiente;
import br.com.radio.repository.AmbienteRepository;
import br.com.radio.service.EventoService;
import br.com.radio.service.ProgramacaoMusicalService;

@Component
public class TarefasAgendadas {
	
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    
	private static final Logger logger = Logger.getLogger(TarefasAgendadas.class);

    @Autowired
    private ProgramacaoMusicalService progMusicalService;
    
    @Autowired
    private EventoService eventoService;
    
    @Autowired
    private AmbienteRepository ambienteRepo;
	
	@Scheduled(cron="0 0 8-9 * * *")
	public void verificarProgramacaoAmbientes()
	{
		
		List<Ambiente> ambientes = ambienteRepo.findByAtivo( true );
		
		ambientes.forEach( amb -> {

			System.out.println(  amb );
			
			try
			{
				progMusicalService.criaProgramacaoMusicalDoDiaParaAmbiente( amb );	
			}
			catch ( Exception e )
			{
				logger.error( String.format( "Erro ao criar programação musical do ambiente %s ", amb.getNome() ), e );
				
				e.printStackTrace();
			}
		});
	}

	
	
	@Scheduled(cron="0 */5 * * * *")  // a cada 5 minutos roda
	public void verificarEventos()
	{
		List<Ambiente> ambientes = ambienteRepo.findByAtivo( true );
		
		logger.info( "Rodando tarefas de eventos..." );
		
		ambientes.forEach( amb -> {
			
			eventoService.criaTransmissaoDosEventos( amb );
			
		});
		
		logger.info( "Fim tarefas..." );
	}
	
	
}
