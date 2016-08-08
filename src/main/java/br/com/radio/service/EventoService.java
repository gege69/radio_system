package br.com.radio.service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.radio.enumeration.DiaSemana;
import br.com.radio.model.Ambiente;
import br.com.radio.model.Evento;
import br.com.radio.model.EventoHorario;
import br.com.radio.model.Transmissao;
import br.com.radio.programacaomusical.ProgramacaoMusicalService;
import br.com.radio.repository.EventoRepository;

@Service
public class EventoService {

	@Autowired
	private EventoRepository eventoRepo;
	
	@Autowired
	private ProgramacaoMusicalService progMusicalService;
	
	private static final Logger logger = Logger.getLogger(EventoService.class);
	
	/**
	 * 
	 * 
	 * 
	 * @param ambiente
	 * @return
	 */
	public Evento existeEventoAgoraPorAmbiente( Ambiente ambiente )
	{
		LocalDateTime hoje = LocalDateTime.now();
		
		LocalTime agora = LocalTime.now();
		
		List<Evento> eventos = eventoRepo.findByHorarioAndIdAmbiente( ambiente.getIdAmbiente() );
		
		eventos.forEach( e -> {
			
			if ( repeteHoje( hoje, e ) )
			{
				List<EventoHorario> horarios = e.getHorarios();
				
				if ( horarios != null && horarios.size() > 0 )
				{
					horarios.forEach( hor -> {
						
						LocalTime horaEvento = LocalTime.of( hor.getHora(), hor.getMinuto() );
						
						Long minutesBetween = ChronoUnit.MINUTES.between(horaEvento, agora);
						
						System.out.println(minutesBetween);
						
						minutesBetween = Math.abs( minutesBetween );
						
//						if ( minutesBetween <= 1 )
//							// agendar para agora
//						else
//							// agendar para próximo
					});
				}
			}
		});
		
		return null;
	}
	
	
	private boolean repeteHoje( LocalDateTime hoje, Evento evento )
	{
		DiaSemana diaSemana = DiaSemana.getByIndex( hoje.getDayOfWeek().getValue() );
		
		boolean result = false;
		
		if ( diaSemana.equals( DiaSemana.DOMINGO ) && evento.getRepeteDomingo() )
			result = true;
		else if ( diaSemana.equals( DiaSemana.SEGUNDA ) && evento.getRepeteSegunda() )
			result = true;
		else if ( diaSemana.equals( DiaSemana.TERCA ) && evento.getRepeteTerca() )
			result = true;
		else if ( diaSemana.equals( DiaSemana.QUARTA ) && evento.getRepeteQuarta() )
			result = true;
		else if ( diaSemana.equals( DiaSemana.QUINTA ) && evento.getRepeteQuinta() )
			result = true;
		else if ( diaSemana.equals( DiaSemana.SEXTA ) && evento.getRepeteSexta() )
			result = true;
		else if ( diaSemana.equals( DiaSemana.SABADO ) && evento.getRepeteSabado() )
			result = true;
		
		return result;
	}
	
	
	public List<Evento> getEventosHojePorAmbiente( Ambiente ambiente )
	{
		final LocalDateTime hoje = LocalDateTime.now();
		
		List<Evento> eventos = eventoRepo.findByHorarioAndIdAmbiente( ambiente.getIdAmbiente() );
		
		List<Evento> result = eventos.stream().filter( e -> repeteHoje( hoje, e ) ).collect( Collectors.toList() );
		
		return result;
	}
	

	
	/**
	 * Esse método vai verificar se existem eventos para hoje.
	 * 
	 * Caso existam vai verificar se existem horários programados para daqui a 5 minutos.
	 * 
	 * Se tiver horários vai criar os registros de transmissão e inserir na ordem. 
	 * 
	 * Esse método precisa ser executado novamente depois de 5 minutos
	 * 
	 * @param ambiente
	 */
	public void criaTransmissaoDosEventos( Ambiente ambiente )
	{
		List<Evento> eventos = getEventosHojePorAmbiente( ambiente );
		
		boolean temEventos = eventos != null && eventos.size() > 0;
		
		if ( temEventos )
			logger.info( String.format( "Tarefas de eventos do ambiente %s ", ambiente.getNome() ) );

		LocalTime agora = LocalTime.now().withSecond( 0 ).withNano( 0 );
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		
		eventos.forEach( e -> {

			List<EventoHorario> horarios = e.getHorarios();
			
			horarios.forEach( h -> {
				
				LocalTime horaEvento = LocalTime.of( h.getHora(), h.getMinuto() );
				
				Long minutesBetween = ChronoUnit.MINUTES.between(agora, horaEvento);
				
				if ( minutesBetween >= 0 && minutesBetween < 5 )
				{
					logger.info( minutesBetween );
					logger.info( horaEvento );
					logger.info( agora );
					
					Transmissao transmissaoAnterior = progMusicalService.getTransmissaoEmHorarioAproximado( ambiente, horaEvento );

					if ( transmissaoAnterior != null )
						progMusicalService.criaRegistroTransmissaoPorEvento( e, h, transmissaoAnterior, ambiente );
				}
				
			});
			
		});


		if ( temEventos )
			logger.info( String.format( "FIM - Tarefas de eventos do ambiente %s ", ambiente.getNome() ) );
	}
	
	
	
}
