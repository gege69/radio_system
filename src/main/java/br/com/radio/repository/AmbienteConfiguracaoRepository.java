package br.com.radio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.radio.model.Ambiente;
import br.com.radio.model.AmbienteConfiguracao;

public interface AmbienteConfiguracaoRepository extends JpaRepository<AmbienteConfiguracao, Long> {

	AmbienteConfiguracao findByAmbiente( Ambiente ambiente );
	
	Long deleteByAmbiente( Ambiente ambiente );
	
}
