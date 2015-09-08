package br.com.radio.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.radio.model.Categoria;
import br.com.radio.model.Midia;

public interface MidiaRepository extends JpaRepository<Midia, Long> {

	Page<Midia> findByCategoriasIn( Pageable pageable, Categoria... categorias );
	
	Page<Midia> findByCategoriasInOrderByIdMidiaDesc( Pageable pageable, Categoria... categorias );
	
	Page<Midia> findAllByOrderByIdMidiaDesc( Pageable pageable );
	
	Page<Midia> findByNomeContainingOrTitleContaining( String nome, String title, Pageable pageable );
	
	Page<Midia> findByNomeContainingOrTitleContainingAndCategoriasIn( String nome, String title, List<Categoria> categorias, Pageable pageable );
	
	Midia findByFilehash( String filehash );
	
}
