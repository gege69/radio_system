package br.com.radio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.radio.model.Ambiente;
import br.com.radio.model.Transmissao;

public interface TransmissaoRepository extends JpaRepository<Transmissao, Long> {

	Transmissao findByIdTransmissaoAndAmbienteAndLinkativoTrue( Long idTransmissao, Ambiente ambiente );
	
	@Modifying(clearAutomatically=true)
	@Query("update Transmissao t set t.link = ?1||t.idTransmissao where t.link is null")
	int setLinkFor(String url);

	List<Transmissao> findByAmbienteAndLinkativoOrderByProgramacao_idProgramacaoAscOrdemPlayAsc( Ambiente ambiente, Boolean linkativo );
	
//	List<Transmissao> findByAmbienteAndLinkativo( Ambiente ambiente, Boolean linkativo );
	
	
}
