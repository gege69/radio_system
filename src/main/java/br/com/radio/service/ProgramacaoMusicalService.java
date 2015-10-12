package br.com.radio.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.radio.enumeration.DiaSemana;
import br.com.radio.model.Ambiente;
import br.com.radio.model.Genero;
import br.com.radio.model.Programacao;
import br.com.radio.model.ProgramacaoGenero;
import br.com.radio.repository.ProgramacaoGeneroRepository;
import br.com.radio.repository.ProgramacaoRepository;
import br.com.radio.util.UtilsDates;


@Service
public class ProgramacaoMusicalService {
	
	@Autowired
	private ProgramacaoRepository programacaoRepo;
	
	@Autowired
	private ProgramacaoGeneroRepository programacaoGeneroRepo;
	
	
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

		result.forEach( prog -> {

			Map<String, String> api = prog.getProgAPI();
			
			if ( prog.getGeneros() == null )
				api.put( "generosCount", "0" );
			else
				api.put( "generosCount", Integer.toString( prog.getGeneros().size() ) );
			
			if ( prog.getGeneros() != null && prog.getGeneros().size() == 1 )
				api.put( "generoSingle", prog.getGeneros().get( 0 ).getNome() );
			
		});
		
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
	
	
	
	
	/**
	 * Esse método vai pegar os gêneros passados por parâmetro e copiar para o dia inteiro ( apenas o determinado pelo expediente )
	 * 
	 * @param ambiente
	 * @param dia
	 * @param generos
	 * @return
	 */
	@Transactional
	public boolean gravaGenerosProgramacaoDiaInteiro( Ambiente ambiente, DiaSemana dia, List<Genero> generos )
	{
		List<Programacao> programacoesDoDia = programacaoRepo.findByAmbienteAndDiaSemanaAndAtivoTrue( ambiente, dia );
		
		programacoesDoDia.forEach( prog -> {
			prog.setAtivo( false );
			prog.setDataInativo( new Date() );
		});
		
		programacaoRepo.save( programacoesDoDia );
		
		Integer horaInicioDia = ambiente.getHoraIniExpediente();
		Integer horaFimDia = 0;

		if ( ambiente.getMinutoFimExpediente() > 0 )
		{
			if ( ambiente.getHoraFimExpediente().equals(23) )
				horaFimDia = 0;
			else
				horaFimDia = ambiente.getHoraFimExpediente() + 1;
		}
		else
			horaFimDia = ambiente.getHoraFimExpediente();

		Integer maxLoopFor = horaFimDia;
		
		if ( horaFimDia.equals( 0 ) )
			maxLoopFor = 24;

		for ( int hora = horaInicioDia; hora <= maxLoopFor; hora++ )
		{
			Programacao nova = gravaNovaProgramacao( ambiente, new Programacao( ambiente, dia, hora ), false );
			
			gravaGeneros( generos, nova );
		}
		
		return true;
	}
	

	
	/**
	 * Vai inativar o registro antigo da programação e criar um registro novo ( histórico )
	 * 
	 * Vai inserir os novos gêneros para o registro novo
	 *
	 * @param ambiente
	 * @param dto
	 * @param generos
	 * @return
	 */
	@Transactional
	public Programacao gravaGenerosProgramacao( Ambiente ambiente, Programacao dto, List<Genero> generos )	
	{
		if ( ambiente == null )
			throw new RuntimeException("Código do ambiente inválido");
		
		if ( dto == null )
			throw new RuntimeException( "Código da programação inválido");
		
		dto.setAtivo( false );
		dto.setDataInativo( new Date() );
		
		programacaoRepo.save( dto );
		
		Programacao nova = gravaNovaProgramacao( ambiente, dto );

		gravaGeneros( generos, nova );

		return nova;
	}


	private void gravaGeneros( List<Genero> generos, Programacao nova )
	{
		List<ProgramacaoGenero> progGeneros = new ArrayList<ProgramacaoGenero>();
		
		generos.forEach( gen -> {
			
			ProgramacaoGenero progGen = new ProgramacaoGenero();
			
			progGen.setGenero( gen );
			progGen.setProgramacao( nova );
			
			progGeneros.add( progGen );
		});
		
		programacaoGeneroRepo.save( progGeneros );

	}
	
	

	public Programacao gravaNovaProgramacao( Ambiente ambiente, Programacao dto )
	{
		return this.gravaNovaProgramacao( ambiente, dto, true );
	}
	
	
	public Programacao gravaNovaProgramacao( Ambiente ambiente, Programacao dto, Boolean testOverlap )
	{
		if ( dto.getDiaSemana() == null )
			throw new RuntimeException("Dia da Semana não preenchido");
		
		if ( dto.getHoraInicio() == null )
			throw new RuntimeException("Início não determinado");
		
//		if ( dto.getHoraFim() == null || dto.getMinutoFim() == null )
//			throw new RuntimeException("Fim não determinado");
		
		if ( dto.getMinutoInicio() == null )
			dto.setMinutoInicio( 0 );
		
		if ( dto.getHoraFim() == null )
		{
			if ( dto.getHoraInicio().equals( 23 ) )
				dto.setHoraFim( 0 );
			else
				dto.setHoraFim( dto.getHoraInicio() + 1 );
		}
		
		if ( dto.getMinutoFim() == null )
			dto.setMinutoFim( 0 );
		
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

		if ( testOverlap )
		{
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
		}
		
		programacaoRepo.save( p );
		
		return p;
	}
	
	
	
	/**
	 * 
	 * Uma vez gravado o novo expediente ( ou até mesmo antes de gravar ) é necessário mostrar para o usuário que ficaram programações musicais programadas para depois do expediente.
	 * 
	 * Isso não é um erro pois o player vai obedecer o Expediente mas é bom avisar.
	 * 
	 * @param ambiente
	 * @return
	 */
	public Boolean verificaExisteProgramacaoForaDoNovoExpediente( Ambiente ambiente )
	{
//		List<Programacao> programacoes = programacaoRepo.findbyambiente
		
		return false;
	}
	
	

}
