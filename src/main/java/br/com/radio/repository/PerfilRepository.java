package br.com.radio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.radio.model.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {

	Perfil findByNomeAndComumTrue( String nome );

	List<Perfil> findByComumTrue();
	
}
