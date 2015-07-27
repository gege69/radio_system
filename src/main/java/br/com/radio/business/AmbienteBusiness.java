package br.com.radio.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.radio.model.Ambiente;
import br.com.radio.model.AmbienteEmail;
import br.com.radio.model.AmbienteEndereco;
import br.com.radio.repository.AmbienteDAO;
import br.com.radio.repository.AmbienteEmailDAO;
import br.com.radio.repository.AmbienteEnderecoDAO;

@Component
public class AmbienteBusiness {

	@Autowired
	private AmbienteDAO ambienteDAO;
	
	@Autowired
	private AmbienteEmailDAO ambienteEmailDAO;

	@Autowired
	private AmbienteEnderecoDAO ambienteEnderecoDAO;

	/**
	 * Esse método salva o ambiente tomando cuidado para verificar os emails e endereços.
	 * 
	 * Nessa primeira etapa serão apenas atualizados os emails e endereços... as listas estão programadas para poder das flexibilidade no futuro. 
	 * 
	 * @param ambiente
	 */
	public void saveAmbiente( Ambiente ambiente )
	{
		List<AmbienteEmail> emails = new ArrayList<AmbienteEmail>();
		List<AmbienteEndereco> enderecos = new ArrayList<AmbienteEndereco>();
		
		emails = ambiente.getEmails();
		enderecos = ambiente.getEnderecos();
		
		ambiente.setEmails( null );  // apenas precaução para o hibernate não persistir
		ambiente.setEnderecos( null );  // apenas precaução para o hibernate não persistir
		
		ambienteDAO.save( ambiente );
		
		if ( ambiente != null && ambiente.getId_ambiente_amb() != null && ambiente.getId_ambiente_amb() > 0 )
		{
			// Por enquanto trata como apenas 1 email e 1 endereço
			AmbienteEmail emailTela = null;
			AmbienteEndereco enderecoTela = null;
			
			emailTela = emails.get( 0 );
			enderecoTela = enderecos.get( 0 );
			
//			AmbienteEmail emailBanco = ambienteEmailDAO.findLastResult( "ds_email_aml", "id_email_aml", emailTela.getDs_email_aml() );
		}
	}
	
}

