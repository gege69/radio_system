package br.com.radio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.radio.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

	Categoria findByCodigo( String codigo );
	
	List<Categoria> findByCodigoIn( List<String> codigos );	
	
	List<Categoria> findByIdCategoriaIn( Long[] categorias );
	
}
