package br.com.radio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.radio.model.Ambiente;
import br.com.radio.model.Midia;
import br.com.radio.model.MidiaAmbiente;

public interface MidiaAmbienteRepository extends JpaRepository<MidiaAmbiente, Long> {

	Long countByAmbienteAndMidia( Ambiente ambiente, Midia midia );
	
	@Modifying(clearAutomatically=true)
	@Query(	value=  " INSERT INTO midia_ambiente  "+
					" SELECT nextval('midia_ambiente_id_midiaamb_seq'), clock_timestamp(), ?1, m.id_midia FROM midia m  "+
					"  INNER JOIN midia_categoria mc ON mc.id_midia = m.id_midia  "+
					"  INNER JOIN categoria cat ON cat.id_categoria = mc.id_categoria  "+
					"  LEFT JOIN midia_ambiente ma ON ma.id_midia = m.id_midia AND ma.id_ambiente = ?1 "+
					"  WHERE cat.codigo in ('musica', 'veic_frase_ini', 'veic_marca', 'veic_modelo', 'veic_cor', 'veic_frase_fim', 'horoscopo', 'veic_placa_numero', 'veic_placa_letra', 'opcionais' )  "+
					"  AND ma.id_midiaamb IS NULL ", nativeQuery = true)
	int insertMidiasDefaultAmbiente( Long idAmbiente );
	
	
	List<MidiaAmbiente> findByMidia( Midia midia );

}
