package br.com.radio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.radio.model.Conversa;
import br.com.radio.model.Mensagem;

public interface MensagemRepository extends JpaRepository<Mensagem, Long> {

	List<Mensagem> findByConversa( Conversa conversa );
	
}
