package br.com.radio.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.radio.model.Categoria;
import br.com.radio.model.Midia;

public interface MidiaRepository extends JpaRepository<Midia, Long> {

	Page<Midia> findByCategoriasIn( List<Categoria> categorias, Pageable pageable );
	
}
