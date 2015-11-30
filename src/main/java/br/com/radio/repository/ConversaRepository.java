package br.com.radio.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.radio.model.Conversa;
import br.com.radio.model.Empresa;

public interface ConversaRepository extends JpaRepository<Conversa, Long> {
	
	Page<Conversa> findByEmpresaAndAtivo( Pageable pageable, Empresa empresa, Boolean ativo );
	
//	@Query("SELECT c FROM Conversa c JOIN c.usuarios u WHERE u = ?1 AND ativo = ?2 group by c ")
//	List<>  // buscar pelo usuário tomar cuidado com repeticao

}
