package br.com.radio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.radio.enumeration.DiaSemana;
import br.com.radio.model.Ambiente;
import br.com.radio.model.Programacao;

public interface ProgramacaoRepository extends JpaRepository<Programacao, Long> {
	
	List<Programacao> findByAmbienteAndAtivoTrue( Ambiente ambiente );
	
	List<Programacao> findByAmbienteAndDiaSemanaAndAtivoTrue( Ambiente ambiente, DiaSemana diaSemana );

	Programacao findByAmbienteAndIdProgramacao( Ambiente ambiente, Long idProgramacao );
	

}
