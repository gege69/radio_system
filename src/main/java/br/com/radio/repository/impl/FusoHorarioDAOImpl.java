package br.com.radio.repository.impl;

import org.springframework.stereotype.Repository;

import br.com.radio.model.FusoHorario;
import br.com.radio.repository.FusoHorarioDAO;

@Repository("fusoDAO")
public class FusoHorarioDAOImpl extends AbstractDAO<FusoHorario, Long> implements FusoHorarioDAO {

}
