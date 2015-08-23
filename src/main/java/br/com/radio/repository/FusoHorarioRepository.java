package br.com.radio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.radio.model.FusoHorario;

public interface FusoHorarioRepository extends JpaRepository<FusoHorario, Long> {

	 @Query("SELECT f FROM FusoHorario f order by f.orderComum ")
	 public List<FusoHorario> findAllWithSortByOrderComum();
	
}
