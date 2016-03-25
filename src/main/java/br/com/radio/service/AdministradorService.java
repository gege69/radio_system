package br.com.radio.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.radio.model.Cliente;
import br.com.radio.model.Telefone;
import br.com.radio.repository.ClienteRepository;
import br.com.radio.repository.TelefoneRepository;

@Service
public class AdministradorService {
	
	@Autowired
	private ClienteRepository clienteRepo;
	
	@Autowired
	private TelefoneRepository telefoneRepo;

	@Transactional
	public Cliente saveCliente( Cliente clienteVO )
	{
		if ( clienteVO.getAtivo() == null )
			clienteVO.setAtivo( false );

		if ( StringUtils.isNotBlank( clienteVO.getCnpj() ) )
			clienteVO.setCnpj( clienteVO.getCnpj().replaceAll("\\D+","") );

		List<Telefone> telefones = clienteVO.getTelefones();
		
		clienteRepo.save( clienteVO );
		
		if ( clienteVO.getIdCliente() != null && clienteVO.getIdCliente() > 0 )
			telefoneRepo.deleteByCliente( clienteVO );
		
		for ( Telefone tel : telefones )
		{
			if ( tel != null ){
				tel.setCliente( clienteVO );
				
				telefoneRepo.save( tel );
			}
		}

		return clienteVO;
	}
	

}
