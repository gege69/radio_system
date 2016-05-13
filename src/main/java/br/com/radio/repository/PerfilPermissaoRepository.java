package br.com.radio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.radio.model.Perfil;
import br.com.radio.model.PerfilPermissao;

public interface PerfilPermissaoRepository extends JpaRepository<PerfilPermissao, Long> {

	List<PerfilPermissao> findByPerfil( Perfil perfil );
	
}
