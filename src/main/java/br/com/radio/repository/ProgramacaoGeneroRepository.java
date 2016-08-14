package br.com.radio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.radio.model.Ambiente;
import br.com.radio.model.ProgramacaoGenero;

public interface ProgramacaoGeneroRepository extends JpaRepository<ProgramacaoGenero, Long> {
	
	List<ProgramacaoGenero> findByProgramacao_AmbienteAndProgramacao_AtivoTrue(Ambiente ambiente);

}
