package br.com.radio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.radio.dto.midia.RelatorioMidiaGeneroVO;
import br.com.radio.model.Genero;
import br.com.radio.model.Midia;
import br.com.radio.model.MidiaGenero;

public interface MidiaGeneroRepository extends JpaRepository<MidiaGenero, Long> {

	Long deleteByMidia( Midia midia );
	
	Long countByMidia( Midia midia );

	List<MidiaGenero> findByGenero( Genero genero );
	
	@Query("SELECT new br.com.radio.dto.midia.RelatorioMidiaGeneroVO( count(mg.idMediagen), g.idGenero, g.nome ) FROM MidiaGenero mg JOIN mg.genero g GROUP BY g.idGenero, g.nome ")
	List<RelatorioMidiaGeneroVO> findRelatorioGeneros();
	
}
