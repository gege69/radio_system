package br.com.radio.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.radio.model.Funcionalidade;

public interface FuncionalidadeRepository extends JpaRepository<Funcionalidade, Long> {

	List<Funcionalidade> findByAtivo( Sort sort, Boolean ativo );

}
