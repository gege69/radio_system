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

	List<Midia> findByCategoriasAndValidoTrue( Categoria categoria );
	
	Long countByNome( String nome );

	// Resultado Paginado ( Utilizado para as Grids de Música no Upload e Gerenciamento )
	@Query("SELECT m FROM Midia m JOIN m.generos g JOIN m.categorias c WHERE m.valido = true AND c.codigo = 'musica' AND ( lower(m.nome) like ?1 OR lower(m.artist) like ?1 OR lower(g.nome) like ?1 )  GROUP BY m ")
	Page<Midia> findByCustomSearch( Pageable pageable, String searchLowerCase );

	@Query("SELECT m FROM Midia m JOIN m.generos g JOIN m.categorias c WHERE m.valido = true AND c.codigo = 'musica' AND g.idGenero = ?2 AND ( lower(m.nome) like ?1 OR lower(m.artist) like ?1 ) GROUP BY m ")
	Page<Midia> findByCustomSearchByGenero( Pageable pageable, String searchLowerCase, Long idGenero );

	// Listas (principalmente para alterar os gêneros de todas as músicas da pesquisa)
	@Query("SELECT m FROM Midia m JOIN m.generos g JOIN m.categorias c WHERE m.valido = true AND c.codigo = 'musica' AND ( lower(m.nome) like ?1 OR lower(m.artist) like ?1 OR lower(g.nome) like ?1 )  GROUP BY m ")
	List<Midia> findByCustomSearch( String searchLowerCase );
	
	@Query("SELECT m FROM Midia m JOIN m.generos g JOIN m.categorias c WHERE m.valido = true AND c.codigo = 'musica' AND g.idGenero = ?2 AND ( lower(m.nome) like ?1 OR lower(m.artist) like ?1 ) GROUP BY m ")
	List<Midia> findByCustomSearchByGenero( String searchLowerCase, Long idGenero );

	List<Midia> findByIdMidiaIn( List<Long> idMidia );
	
}
