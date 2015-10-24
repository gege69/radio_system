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
	
	
	// Esse método vai tentar encontrar um registro que esteja mais ou menos no mesmo horário do servidor e ainda não foi tocado
	@Query(value="select t.* from transmissao t where t.id_ambiente = ?1 and linkativo = true and clock_timestamp() between t.dataprevisaoplay and t.dataprevisaoplay + ( t.duracao * interval '1 second' ) order by t.dataprevisaoplay asc limit 1 ", nativeQuery=true )
	Transmissao findByIdAmbienteAndLinkativoTrueAndPrevisaoAtual( Long idAmbiente );
	
	// Esse método vai tentar encontrar o primeiro registro que exista pra tocar NO DIA.... 
	Transmissao findFirstByAmbienteAndLinkativoTrueAndDiaPlayOrderByIdTransmissaoAscOrdemPlayAsc( Ambiente ambiente, Date diaPlay );

	
	@Modifying(clearAutomatically=true)
	@Query("update Transmissao t set t.link = ?1||t.idTransmissao||'/midia' where t.link is null")
	int setLinkFor(String url);

	List<Transmissao> findByAmbienteAndLinkativoOrderByProgramacao_idProgramacaoAscOrdemPlayAsc( Ambiente ambiente, Boolean linkativo );
	
	List<Transmissao> findByAmbienteAndStatusPlaybackAndDiaPlayBetween( Ambiente ambiente, StatusPlayback statusplay, Date diaPlayIni, Date diaPlayFim );

	@Modifying(clearAutomatically=true)
	@Query("update Transmissao t set t.statusPlayback = 'IGNORADA' where t.ambiente = ?1 and t.linkativo = true ")
	int setStatusIgnorada( Ambiente ambiente );    // Desativando os links para gerar uma nova transmissão
	
	@Modifying(clearAutomatically=true)
	@Query("update Transmissao t set t.linkativo = false where t.ambiente = ?1 and t.linkativo = true ")
	int setLinkInativo( Ambiente ambiente );    // Desativando os links para gerar uma nova transmissão

	
	// Para encontrar a próxima musica
	Transmissao findByAmbienteAndLinkativoTrueAndOrdemPlay( Ambiente ambiente, Long ordemPlay );
	
	
	@Modifying(clearAutomatically=true)
	@Query("update Transmissao t set t.linkativo = false, t.downloadcompleto = true, t.dataFinishPlay = clock_timestamp(), t.statusPlayback = 'FIM' where t.ambiente = ?1 and t.linkativo = true and t.idTransmissao < ?2 ")
	int setLinkInativoAnteriores( Ambiente ambiente, Long idTransmissao );    

	
}
