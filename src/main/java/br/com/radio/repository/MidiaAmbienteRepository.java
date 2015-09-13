package br.com.radio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.radio.model.Ambiente;
import br.com.radio.model.Midia;
import br.com.radio.model.MidiaAmbiente;

public interface MidiaAmbienteRepository extends JpaRepository<MidiaAmbiente, Long> {

	MidiaAmbiente findByAmbienteAndMidia( Ambiente ambiente, Midia midia );
}
