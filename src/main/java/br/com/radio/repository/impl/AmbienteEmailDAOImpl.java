package br.com.radio.repository.impl;

import org.springframework.stereotype.Repository;

import br.com.radio.model.AmbienteEmail;
import br.com.radio.repository.AmbienteEmailDAO;

@Repository("ambienteEmailDAO")
public class AmbienteEmailDAOImpl extends AbstractDAO<AmbienteEmail, Long> implements AmbienteEmailDAO {
	
}
