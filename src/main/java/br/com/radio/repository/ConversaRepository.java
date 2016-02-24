package br.com.radio.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.radio.model.Cliente;
import br.com.radio.model.Conversa;
import br.com.radio.model.Usuario;

public interface ConversaRepository extends JpaRepository<Conversa, Long> {
	
	Page<Conversa> findByClienteAndAtivo( Pageable pageable, Cliente cliente, Boolean ativo );
	
	Page<Conversa> findByClienteAndAtivoAndUsuariosIn( Pageable pageable, Cliente cliente, Boolean ativo, List<Usuario> usuarios );
	
	
	
//	@Query("SELECT c FROM Conversa c JOIN c.usuarios u WHERE u = ?1 AND ativo = ?2 group by c ")
//	List<>  // buscar pelo usuário tomar cuidado com repeticao

}
