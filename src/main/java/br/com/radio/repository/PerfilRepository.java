package br.com.radio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.radio.model.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {

	Perfil findByNome( String nome );
	
}
