package br.com.radio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.radio.model.UsuarioPermissao;

public interface UsuarioPermissaoRepository extends JpaRepository<UsuarioPermissao, Long> {

}
