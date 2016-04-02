package br.com.radio.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.radio.dto.cliente.ClienteRelatorioDTO;
import br.com.radio.dto.cliente.ClienteResumoFinanceiroDTO;
import br.com.radio.model.Ambiente;
import br.com.radio.model.Cliente;
import br.com.radio.model.CondicaoComercial;
import br.com.radio.model.Telefone;
import br.com.radio.model.Titulo;
import br.com.radio.model.Usuario;
import br.com.radio.repository.AmbienteRepository;
import br.com.radio.repository.ClienteRepository;
import br.com.radio.repository.CondicaoComercialRepository;
import br.com.radio.repository.TelefoneRepository;
import br.com.radio.repository.TituloRepository;
import br.com.radio.util.UtilsStr;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepo;
	
	@Autowired
	private TelefoneRepository telefoneRepo;

	@Autowired
	private CondicaoComercialRepository ccRepo;
	
	@Autowired 
	private AmbienteRepository ambienteRepo;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private TituloRepository tituloRepo;


	@Transactional
	public Cliente saveCliente( Cliente clienteVO )
	{
		if ( clienteVO.getAtivo() == null )
			clienteVO.setAtivo( false );
 
		if ( StringUtils.isNotBlank( clienteVO.getCnpj() ) )
			clienteVO.setCnpj( clienteVO.getCnpj().replaceAll("\\D+","") );
		
		List<Telefone> telefones = clienteVO.getTelefones();
		
		boolean clienteNovo = clienteVO.getIdCliente() == null;
		
		if ( clienteNovo )
		{
			Cliente clienteExistente = clienteRepo.findByCnpj( clienteVO.getCnpj() );
			
			if ( clienteExistente != null )
				throw new RuntimeException( "Cliente já existe no banco de dados." );
		}
		
		clienteRepo.save( clienteVO );

		if ( clienteNovo )
		{
			List<CondicaoComercial> condicoesComerciais = ccRepo.findByCliente( clienteVO );
			
			if ( condicoesComerciais != null && condicoesComerciais.size() == 0 )
			{
				// Buscando as condicoes comerciais default
				List<CondicaoComercial> condicoesDefault = ccRepo.findByCliente( null );
				
				for (CondicaoComercial ccDefault : condicoesDefault )
				{
					CondicaoComercial ccNova = new CondicaoComercial();
					BeanUtils.copyProperties( ccDefault, ccNova );

					ccNova.setIdCondcom( null );
					ccNova.setCliente( clienteVO );
					
					ccRepo.save( ccNova );
				}
			}
		}
		
		if ( clienteVO.getIdCliente() != null && clienteVO.getIdCliente() > 0 )
			telefoneRepo.deleteByCliente( clienteVO );
		
		if ( telefones != null )
		{
			for ( Telefone tel : telefones )
			{
				if ( tel != null ){
					tel.setCliente( clienteVO );
					
					telefoneRepo.save( tel );
				}
			}
		}

		return clienteVO;
	}
	
	
	public ClienteResumoFinanceiroDTO getResumoFinanceiro( Cliente cliente )
	{
		List<Ambiente> ambientes = ambienteRepo.findByCliente( cliente );
		
		Long ativos = ambientes.stream().filter( a -> a.getAtivo() ).count();
		Long inativos = ambientes.stream().filter( a -> !a.getAtivo() ).count();
		
		ClienteResumoFinanceiroDTO dto = new ClienteResumoFinanceiroDTO();
		
		dto.setAmbientesAtivos( ativos.intValue() );
		dto.setAmbientesInativos( inativos.intValue() );
		dto.setTotalAmbientes( ambientes.size() );
		
		return dto;
	}
	
	
	@Transactional
	public CondicaoComercial saveCondicaoComercial( Usuario usuario, CondicaoComercial ccVO ){
		
		if ( usuario == null || usuario.getCliente() == null )
			throw new RuntimeException( "Não foi possível determinar o Cliente para a Condição Comercial" );
		
		if ( ccVO.getIdCondcom() == null || ccVO.getIdCondcom() <= 0 )
		{
			Long qtd = ccRepo.countByClienteAndTipoTaxaAndDefinicaoTaxaAndValor( usuario.getCliente(), ccVO.getTipoTaxa(), ccVO.getDefinicaoTaxa(), ccVO.getValor() );
			
			if ( qtd > 0 )
				throw new RuntimeException("Essa condição comercial para esse cliente já existe. Não é possível incluir.");
		}

		if ( ccVO.getCliente() == null )
			ccVO.setCliente( usuario.getCliente() );

		ccVO.setDataAlteracao( new Date() );
		
		ccRepo.save( ccVO );
		
		return ccVO;
	}
	

	public Page<ClienteRelatorioDTO> getRelatorioCliente( Pageable pageable, String search ){

		Page<Cliente> paginaCliente = clienteRepo.findAll( pageable );
		
		if ( StringUtils.isBlank( UtilsStr.notNull( search ) ) )
			paginaCliente = clienteRepo.findAll( pageable );
		else
		{
			String razaosocial = "%" + search + "%";
			String nomefantasia = "%" + search + "%";
			String cnpj = "%" + search + "%";

			paginaCliente = clienteRepo.findByRazaosocialContainingIgnoreCaseOrNomefantasiaContainingIgnoreCaseOrCnpjContaining( pageable, razaosocial, nomefantasia, cnpj );
		}
		
		List<ClienteRelatorioDTO> listaRelatorio = new ArrayList<ClienteRelatorioDTO>();
		
		for ( Cliente cliente : paginaCliente.getContent() ){
			
			// implementar
			Usuario usuario = usuarioService.getUsuarioMaisRelevantePorCliente( cliente );
			
			List<Titulo> titulosAbertos = tituloRepo.findByClienteAndDataPagamentoIsNullAndValorPago( cliente, BigDecimal.ZERO );
			
			// Pode ser que precise de alguma informação dos ambientes... por isso o select qualquer coisa troco por um count
			List<Ambiente> ambientes = ambienteRepo.findByClienteAndAtivo( cliente, true );
			
			Integer totalAmbientes = ambientes.size();
			
			BigDecimal totalLiquido = BigDecimal.ZERO;
			BigDecimal totalDescontos = BigDecimal.ZERO;
			BigDecimal totalPagar = BigDecimal.ZERO;
			
			for ( Titulo tit : titulosAbertos )
			{
				totalLiquido = totalLiquido.add( tit.getValorLiquido() ).add( tit.getValorTaxas() ).add( tit.getValorJuros() ).add( tit.getValorAcresc() );
				totalDescontos = totalDescontos.add( tit.getValorDescontos() );
				
				totalPagar = totalPagar.add( tit.getValorTotal() );
			}
			
			BigDecimal verificarConta = totalLiquido.subtract( totalDescontos );
			
			if ( verificarConta.compareTo( totalPagar ) != 0 )
				System.out.println("DEU ALGUMA COISA ERRADA NOS CALCULOS");
			
			
			ClienteRelatorioDTO clienteRelatorioDTO = new ClienteRelatorioDTO( usuario, totalAmbientes, cliente, totalLiquido, totalDescontos, totalPagar );
			
			listaRelatorio.add( clienteRelatorioDTO );
		}
		
		PageImpl<ClienteRelatorioDTO> paginaRelatorio = new PageImpl<ClienteRelatorioDTO>( listaRelatorio, pageable, paginaCliente.getTotalElements() );
		
		return paginaRelatorio;
	}




}
