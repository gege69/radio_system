package br.com.radio.repository.impl;

import org.springframework.stereotype.Repository;

import br.com.radio.model.Ambiente;
import br.com.radio.repository.AmbienteDAO;

@Repository("ambienteDAO")
public class AmbienteDAOImpl extends AbstractDAO<Ambiente, Long> implements AmbienteDAO {

}
