package br.com.radio.service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import net.objectlab.kit.datecalc.common.DateCalculator;
import net.objectlab.kit.datecalc.common.DefaultHolidayCalendar;
import net.objectlab.kit.datecalc.common.HolidayCalendar;
import net.objectlab.kit.datecalc.common.HolidayHandlerType;
import net.objectlab.kit.datecalc.jdk8.LocalDateKitCalculatorsFactory;

import org.springframework.stereotype.Service;

import br.com.radio.util.UtilsDates;
import de.jollyday.Holiday;
import de.jollyday.HolidayManager;

@Service
public class DiaUtilService {
	
	private HolidayManager gerenciadorDeFeriados = HolidayManager.getInstance(de.jollyday.HolidayCalendar.BRAZIL);
	
	private DateCalculator<LocalDate> calendario;
	
	@PostConstruct
	private void init(){

		Set<Holiday> feriados = gerenciadorDeFeriados.getHolidays(LocalDate.now().getYear());
		Set<LocalDate> dataDosFeriados = new HashSet<LocalDate>();
		for (Holiday h : feriados) {
			dataDosFeriados.add(UtilsDates.asLocalDate( h.getDate().toDate() ));
		}		

		HolidayCalendar<LocalDate> calendarioDeFeriados = new DefaultHolidayCalendar<LocalDate>(dataDosFeriados);	
		LocalDateKitCalculatorsFactory.getDefaultInstance().registerHolidays("BR", calendarioDeFeriados);
		calendario = LocalDateKitCalculatorsFactory.getDefaultInstance().getDateCalculator("BR", HolidayHandlerType.FORWARD);
	}

	public HolidayManager getGerenciadorDeFeriados()
	{
		return gerenciadorDeFeriados;
	}

	public DateCalculator<LocalDate> getCalendario()
	{
		return calendario;
	}


	public int getDiasNaoUteis(LocalDate from, LocalDate to){
		
		int diasNaoUteis = 0;
		 
		LocalDate dataInicialTemporaria = from;
		LocalDate dataFinalTemporaria = to;
		 
		while (!dataInicialTemporaria.isAfter(dataFinalTemporaria)) {
		    if (calendario.isNonWorkingDay(dataInicialTemporaria)) {
		       diasNaoUteis++;
		    }
		    dataInicialTemporaria = dataInicialTemporaria.plusDays(1);
		}	
		
		return diasNaoUteis;
	}
	

	public int getDiasUteis(LocalDate from, LocalDate to){
		
		int diasUteis = 0;
		 
		LocalDate dataInicialTemporaria = from;
		LocalDate dataFinalTemporaria = to;
		 
		while (!dataInicialTemporaria.isAfter(dataFinalTemporaria)) {
		    if (!calendario.isNonWorkingDay(dataInicialTemporaria)) {
		       diasUteis++;
		    }
		    dataInicialTemporaria = dataInicialTemporaria.plusDays(1);
		}	
		
		return diasUteis;
	}

}
