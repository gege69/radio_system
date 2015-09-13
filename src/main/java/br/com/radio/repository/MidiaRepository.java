package br.com.radio.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.radio.model.Ambiente;
import br.com.radio.model.Categoria;
import br.com.radio.model.Midia;

public interface MidiaRepository extends JpaRepository<Midia, Long> {

	// Filtrar o Ambiente aqui é BIG DEAL....
	
	Page<Midia> findByAmbientesAndCategorias( Pageable pageable, Ambiente ambiente, Categoria categoria );
	
	Page<Midia> findByAmbientes( Pageable pageable, Ambiente ambiente );
	
	
	Page<Midia> findByAmbientesAndNomeContaining( Ambiente ambiente, String nome, Pageable pageable );
	
	Page<Midia> findByAmbientesAndNomeContainingAndCategoriasIn( Ambiente ambiente, String nome, List<Categoria> categorias, Pageable pageable );
	
	Midia findByFilehash( String filehash );
	
}
