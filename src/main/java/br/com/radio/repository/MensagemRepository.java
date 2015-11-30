package br.com.radio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.radio.model.Mensagem;

public interface MensagemRepository extends JpaRepository<Mensagem, Long> {

}
