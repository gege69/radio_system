package br.com.radio.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.radio.model.Ambiente;
import br.com.radio.model.Categoria;
import br.com.radio.model.Genero;
import br.com.radio.model.Midia;

public interface MidiaRepository extends JpaRepository<Midia, Long> {

	Page<Midia> findByAmbientesAndCategoriasAndValidoTrue( Pageable pageable, Ambiente ambiente, Categoria categoria );
	
	List<Midia> findByAmbientesAndCategoriasAndValidoTrue( Ambiente ambiente, Categoria categoria );

	// Para montar os blocos comerciais é interessante pegar apenas as mídias comerciais que tenham duração maior que zero pra evitar um bug
	List<Midia> findByAmbientesAndCategoriasAndValidoTrueAndDuracaoGreaterThan( Ambiente ambiente, Categoria categoria, Integer duracao );
	
	Page<Midia> findByAmbientesAndCategorias_codigoAndValidoTrue( Pageable pageable, Ambiente ambiente, String codigo );

	List<Midia> findByAmbientesAndCategorias_codigoAndValidoTrue( Ambiente ambiente, String codigo );
	
	Page<Midia> findByAmbientesAndValidoTrue( Pageable pageable, Ambiente ambiente );
	
	Page<Midia> findByAmbientesAndNomeContainingIgnoreCaseAndValidoTrue( Ambiente ambiente, String nome, Pageable pageable );
	
	Page<Midia> findByAmbientesAndNomeContainingAndCategoriasInAndValidoTrue( Ambiente ambiente, String nome, List<Categoria> categorias, Pageable pageable );
	
	Midia findByFilehash( String filehash );
	
	// Procurando por midias desse ambiente, na categoria indicada, nos generos indicados, sem repetição
	@Query("SELECT m FROM Midia m JOIN m.ambientes a JOIN m.categorias c JOIN m.generos g WHERE m.valido = true AND a = ?1 AND c = ?2 AND g IN ?3 group by m ")
	List<Midia> findByAmbientesAndCategoriasAndGenerosInGroupBy( Ambiente ambiente, Categoria categoria, Set<Genero> genero );
	
	// Procurando por midias desse ambiente, na categoria indicada, nos generos indicados, COM EXCEÇÃO DAS JÁ TOCADAS, sem repetição
	@Query("SELECT m FROM Midia m JOIN m.ambientes a JOIN m.categorias c JOIN m.generos g WHERE m.valido = true AND a = ?1 AND c = ?2 AND g IN ?3 AND m NOT IN ?4 group by m ")
	List<Midia> findByAmbientesAndCategoriasAndGenerosInAndMidiaNotInGroupBy( Ambiente ambiente, Categoria categoria, Set<Genero> genero, Set<Midia> midiasJaTocadas );

	// Admin
	Page<Midia> findByNomeContainingAndCategoriasInAndValidoTrue( Pageable pageable, String nome, List<Categoria> categorias );

	// Admin
	Page<Midia> findByCategoriasInAndValidoTrue( Pageable pageable, List<Categoria> categorias );

	Page<Midia> findByCategoriasAndValidoTrue( Pageable pageable, Categoria categoria );
	
	Long countByNome( String nome );
}
