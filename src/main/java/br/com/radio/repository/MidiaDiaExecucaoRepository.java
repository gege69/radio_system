package br.com.radio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.radio.model.Midia;
import br.com.radio.model.MidiaDiaExecucao;

public interface MidiaDiaExecucaoRepository extends JpaRepository<MidiaDiaExecucao, Long> {

	List<MidiaDiaExecucao> findByMidiaIn( List<Midia> midias );
	
	long deleteByMidia( Midia midia );
	
}
