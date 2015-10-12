package br.com.radio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.radio.model.Ambiente;
import br.com.radio.model.Programacao;
import br.com.radio.repository.ProgramacaoRepository;
import br.com.radio.util.UtilsDates;

@Service
public class ProgramacaoMusicalService {
	
	@Autowired
	private ProgramacaoRepository programacaoRepo;
	
	
	
	/**
	 * Esse método vai obter os registros de programação do banco de dados e validar se existe colisão.
	 * 
	 * Se existir vai tentar inativar.
	 * 
	 * @param ambiente
	 * @return
	 */
	public List<Programacao> getProgramacaoAtivaByAmbiente( Ambiente ambiente )
	{
		List<Programacao> result = null;
		
		result = programacaoRepo.findByAmbienteAndAtivoTrue( ambiente );

//		Map<DiaSemana, List<Programacao>> mapProdPorDia = result.stream().collect( Collectors.groupingBy( Programacao::getDiaSemana ) );
//
//		mapProdPorDia.forEach( ( dia, progs ) -> {
//			
//			// Quadrático... uma bosta
//			progs.forEach( p -> {
//				
//				progs.forEach( outro -> {
//					
//					if ( !p.equals( outro ) )
//					{
//						if ( UtilsDates.isOverlapping( p.getDateTimeInicio(), p.getDateTimeFim(), outro.getDateTimeInicio(), outro.getDateTimeFim() ) )
//						{
//							System.out.println( p + " overlap com " + outro );
//							throw new RuntimeException( "Existe programação com overlap" );
//						}
//					}
//				});
//			});
//		});
		
		return result;
	}
	
	
	public Programacao saveProgramacao( Ambiente ambiente, Programacao dto )
	{
		if ( dto.getIdProgramacao() != null && dto.getIdProgramacao() > 0 )
		{
			programacaoRepo.save( dto );  // no caso um update
			
			return dto;
		}
		else
		{
			if ( dto.getDiaSemana() == null )
				throw new RuntimeException("Dia da Semana não preenchido");
			
			if ( dto.getHoraInicio() == null || dto.getMinutoInicio() == null )
				throw new RuntimeException("Início não determinado");
			
			if ( dto.getHoraFim() == null || dto.getMinutoFim() == null )
				throw new RuntimeException("Fim não determinado");
			
			Programacao p = new Programacao();
			
			p.setAmbiente( ambiente );
			p.setAtivo( true );	
			p.setDiaSemana( dto.getDiaSemana() );
			
			p.setHoraInicio( dto.getHoraInicio() );
			p.setMinutoInicio( dto.getMinutoInicio() );
			
			p.setHoraFim( dto.getHoraFim() );
			p.setMinutoFim( dto.getMinutoFim() );
			
			p.setDateTimeInicio( p.getDate( p.getHoraInicio(), p.getMinutoInicio() ) );
			p.setDateTimeFim( p.getDate( p.getHoraFim(), p.getMinutoFim() ) );

			List<Programacao> progs = programacaoRepo.findByAmbienteAndDiaSemanaAndAtivoTrue( ambiente, dto.getDiaSemana() );
			
			progs.forEach( outro -> {
				
				if ( !p.equals( outro ) )
				{
					if ( UtilsDates.isOverlapping( p.getDateTimeInicio(), p.getDateTimeFim(), outro.getDateTimeInicio(), outro.getDateTimeFim() ) )
					{
						System.out.println( p + " overlap com " + outro );
						throw new RuntimeException( "Essa programação está em overlap com outra já existente" );
					}
				}
			});
			
			programacaoRepo.save( p );
			
			return p;
		}
	}
	
	
	
	

}
