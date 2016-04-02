package br.com.radio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.radio.enumeration.DiaSemana;
import br.com.radio.model.Ambiente;
import br.com.radio.model.Programacao;

public interface ProgramacaoRepository extends JpaRepository<Programacao, Long> {
	
	List<Programacao> findByAmbienteAndAtivoTrue( Ambiente ambiente );
	
	List<Programacao> findByAmbienteAndDiaSemanaAndAtivoTrue( Ambiente ambiente, DiaSemana diaSemana );

	Programacao findByAmbienteAndIdProgramacao( Ambiente ambiente, Long idProgramacao );

	
	// Essa query verifica se existe algum registro de programação para o momento atual.. ( não está levando em consideração o timezone )
	@Query(value="select min(1) existe from programacao where dia = ?2 AND id_ambiente = ?1 and ativo = true and pg_catalog.time(datetime_inicio) > pg_catalog.time(clock_timestamp()) and pg_catalog.time(datetime_fim) <= pg_catalog.time(clock_timestamp()) ", nativeQuery=true)
	Integer getExisteProgramacaoParaHorarioAtual( Ambiente ambiente, String diaSemanaStr );
	
}
