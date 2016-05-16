package br.com.radio.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.radio.model.Perfil;
import br.com.radio.model.PerfilPermissao;
import br.com.radio.model.Permissao;

public interface PerfilPermissaoRepository extends JpaRepository<PerfilPermissao, Long> {

	List<PerfilPermissao> findByPerfil( Perfil perfil );

	Page<PerfilPermissao> findByPerfil( Pageable pageable, Perfil perfil );
	
	PerfilPermissao findByPerfilAndPermissao( Perfil perfil, Permissao permissao );

	int deleteByPerfil( Perfil perfil );
	
}
