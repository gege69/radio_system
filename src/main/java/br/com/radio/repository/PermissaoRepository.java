package br.com.radio.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.radio.model.Permissao;

public interface PermissaoRepository extends JpaRepository<Permissao, Long> {

	Permissao findByCodigo( String codigo );

	Page<Permissao> findByExclusivoFalse( Pageable pageable );
	
	List<Permissao> findByIdPermissaoIn( List<Long> idPermissoes );

	List<Permissao> findByIdPermissaoInAndExclusivoFalse( List<Long> idPermissoes );

}
