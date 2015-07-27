package br.com.radio.repository.impl;

import org.springframework.stereotype.Repository;

import br.com.radio.model.AmbienteEndereco;
import br.com.radio.repository.AmbienteEnderecoDAO;

@Repository("ambienteEnderecoDAO")
public class AmbienteEnderecoDAOImpl extends AbstractDAO<AmbienteEndereco, Long> implements AmbienteEnderecoDAO {

}
