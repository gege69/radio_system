package br.com.radio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.radio.model.Ambiente;
import br.com.radio.model.Midia;
import br.com.radio.model.MidiaAmbiente;

public interface MidiaAmbienteRepository extends JpaRepository<MidiaAmbiente, Long> {

	MidiaAmbiente findByAmbienteAndMidia( Ambiente ambiente, Midia midia );
	
	@Modifying(clearAutomatically=true)
	@Query(	value= 	"INSERT INTO midia_ambiente "+ 
					" SELECT nextval('midia_ambiente_id_midiaamb_seq'), clock_timestamp(), ?1, m.id_midia FROM midia m "+
					" INNER JOIN midia_categoria mc ON mc.id_midia = m.id_midia "+
					" INNER JOIN categoria cat ON cat.id_categoria = mc.id_categoria "+
					" WHERE cat.codigo = 'musica' ", nativeQuery = true)
	int insertMusicasAmbiente( Long idAmbiente );    

}
