package br.com.radio.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.radio.enumeration.StatusPlayback;
import br.com.radio.model.Ambiente;
import br.com.radio.model.Transmissao;

public interface TransmissaoRepository extends JpaRepository<Transmissao, Long> {

	Transmissao findByIdTransmissaoAndAmbienteAndLinkativoTrue( Long idTransmissao, Ambiente ambiente );
	
	@Modifying(clearAutomatically=true)
	@Query("update Transmissao t set t.link = ?1||t.idTransmissao where t.link is null")
	int setLinkFor(String url);

	List<Transmissao> findByAmbienteAndLinkativoOrderByProgramacao_idProgramacaoAscOrdemPlayAsc( Ambiente ambiente, Boolean linkativo );
	
	List<Transmissao> findByAmbienteAndStatusPlaybackAndDiaPlayBetween( Ambiente ambiente, StatusPlayback statusplay, Date diaPlayIni, Date diaPlayFim );

	@Modifying(clearAutomatically=true)
	@Query("update Transmissao t set t.statusPlayback = 'IGNORADA' where t.ambiente = ?1 and t.linkativo = true ")
	int setStatusIgnorada( Ambiente ambiente );    // Desativando os links para gerar uma nova transmissão
	
	@Modifying(clearAutomatically=true)
	@Query("update Transmissao t set t.linkativo = false where t.ambiente = ?1 and t.linkativo = true ")
	int setLinkInativo( Ambiente ambiente );    // Desativando os links para gerar uma nova transmissão

	
}
