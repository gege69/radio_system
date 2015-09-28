package br.com.radio.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.radio.model.Ambiente;
import br.com.radio.model.Categoria;
import br.com.radio.model.Midia;

public interface MidiaRepository extends JpaRepository<Midia, Long> {

	Page<Midia> findByAmbientesAndCategorias( Pageable pageable, Ambiente ambiente, Categoria categoria );
	
	Page<Midia> findByAmbientesAndCategorias_codigo( Pageable pageable, Ambiente ambiente, String codigo );
	
	Page<Midia> findByAmbientes( Pageable pageable, Ambiente ambiente );
	
	Page<Midia> findByAmbientesAndNomeContaining( Ambiente ambiente, String nome, Pageable pageable );
	
	Page<Midia> findByAmbientesAndNomeContainingAndCategoriasIn( Ambiente ambiente, String nome, List<Categoria> categorias, Pageable pageable );
	
	Midia findByFilehash( String filehash );
	
}
