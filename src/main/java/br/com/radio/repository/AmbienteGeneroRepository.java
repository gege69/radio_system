package br.com.radio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.radio.model.Ambiente;
import br.com.radio.model.AmbienteGenero;
import br.com.radio.model.Genero;

public interface AmbienteGeneroRepository extends JpaRepository<AmbienteGenero, Long> {
	
	@Modifying
    @Query( value = "DELETE FROM AMBIENTE_GENERO WHERE ID_AMBIENTE = :idAmbiente AND ID_GENERO NOT IN :ids ", nativeQuery = true )
    int deleteByAmbienteNotInIds(@Param("idAmbiente") Long idAmbiente, @Param("ids") List<Long> idsGeneros );
	
	List<AmbienteGenero> findByAmbiente( Ambiente ambiente );
	
	AmbienteGenero findByAmbienteAndGenero( Ambiente ambiente, Genero genero );
	
	List<AmbienteGenero> findByGenero( Genero genero );

}
