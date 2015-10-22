package br.com.radio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.radio.model.Genero;

public interface GeneroRepository extends JpaRepository<Genero, Long> {

	List<Genero> findAllByOrderByNome();
	
	List<Genero> findFirst10By();	
}
	