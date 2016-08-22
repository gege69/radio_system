package br.com.radio.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.engine.spi.SessionImplementor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.radio.dto.ContatoDTO;
import br.com.radio.dto.cliente.ClienteRelatorioDTO;
import br.com.radio.dto.cliente.ClienteResumoFinanceiroDTO;
import br.com.radio.dto.cliente.ParametroDTO;
import br.com.radio.enumeration.StatusAmbiente;
import br.com.radio.model.Ambiente;
import br.com.radio.model.Cliente;
import br.com.radio.model.CondicaoComercial;
import br.com.radio.model.Parametro;
import br.com.radio.model.Telefone;
import br.com.radio.model.Titulo;
import br.com.radio.model.Usuario;
import br.com.radio.repository.AmbienteRepository;
import br.com.radio.repository.ClienteRepository;
import br.com.radio.repository.CondicaoComercialRepository;
import br.com.radio.repository.ParametroRepository;
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

	@Autowired
	private ParametroRepository parametroRepo;

	@Autowired
	private CondicaoComercialRepository condRepo;

	// EM 
	@Autowired
	private EntityManager entityManager;
	
	
	
	@Transactional
	public Cliente testeCliente(){
		
		// DÁ PRA USAR O CRITERIA DO HIBERNATE COM O ENTITY MANAGER!!!!!!!! 

		Session session = entityManager.unwrap( Session.class );
		
		Criteria crit = session.createCriteria( Cliente.class );
		
		crit.add( Restrictions.eq( "cnpj", "28372714000140" ) );
		
		List<Cliente> result = crit.list();

		// TESTE
		return result.get( 0 );
	}


	@Transactional
	public Cliente saveCliente( Cliente clienteVO )
	{
		if ( clienteVO.getDataCriacao() == null )
			clienteVO.setDataCriacao( new Date() );
		
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
		
		Long ativos = ambientes.stream().filter( a -> a.isAtivo() ).count();
		Long inativos = ambientes.stream().filter( a -> a.isInativo() ).count();
		Long bloqueados = ambientes.stream().filter( a -> a.isBloqueado() ).count();
		
		ClienteResumoFinanceiroDTO dto = new ClienteResumoFinanceiroDTO();
		
		dto.setAmbientesAtivos( ativos.intValue() );
		dto.setAmbientesInativos( inativos.intValue() );
		dto.setTotalAmbientes( ambientes.size() );
		dto.setAmbientesBloqueados( bloqueados.intValue() );
		
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
	

	

	private Criteria createCriteriaCliente( String razaoSocial, String nomeFantasia, String cnpj, Session session)
	{
		if ( StringUtils.isBlank( razaoSocial ) && StringUtils.isBlank( nomeFantasia ) && StringUtils.isBlank( cnpj ) )
			throw new RuntimeException("Nenhum campo preenchido");

		Criteria crit = session.createCriteria( Cliente.class );

		Disjunction disjunction = Restrictions.disjunction();

		if ( StringUtils.isNotBlank( razaoSocial ) )
			disjunction.add( Restrictions.ilike( "razaosocial", razaoSocial ) );
		
		if ( StringUtils.isNotBlank( nomeFantasia ) )
			disjunction.add( Restrictions.ilike( "nomefantasia", nomeFantasia ) );
		
		if ( StringUtils.isNotBlank( cnpj ) )
			disjunction.add( Restrictions.ilike( "cnpj", cnpj ) );
		
		crit.add( disjunction );

		return crit;
	}



	@Transactional
	public Page<ClienteRelatorioDTO> getRelatorioCliente( Pageable pageable, String search ){

		Page<Cliente> paginaCliente = null;
		
		if ( StringUtils.isBlank( UtilsStr.notNull( search ) ) )
			paginaCliente = clienteRepo.findAll( pageable );
		else
		{
			String razaosocial = "%" + search + "%";
			String nomefantasia = "%" + search + "%";
			String cnpj = "%" + search + "%";

			Session session = entityManager.unwrap( Session.class );

			Criteria critCount =  createCriteriaCliente( razaosocial, nomefantasia, cnpj, session );
			critCount.setProjection( Projections.rowCount() );
			Long total = (Long)critCount.uniqueResult();
			
			Criteria crit = createCriteriaCliente( razaosocial, nomefantasia, cnpj, session );
			
			if ( pageable != null ){
				crit.setMaxResults( pageable.getPageSize() );
				crit.setFirstResult( pageable.getPageNumber() );
			}
			
			List<Cliente> listCliente = crit.list();

			paginaCliente = new PageImpl<Cliente>( listCliente, pageable, total );
		}

		if ( paginaCliente == null )
			throw new RuntimeException("Não foi possível buscar os clientes");
		
		List<ClienteRelatorioDTO> listaRelatorio = new ArrayList<ClienteRelatorioDTO>();
		
		for ( Cliente cliente : paginaCliente.getContent() ){

			cliente = (Cliente) entityManager.unwrap(SessionImplementor.class).getPersistenceContext().unproxy(cliente);

			// implementar
			Usuario usuario = usuarioService.getUsuarioMaisRelevantePorCliente( cliente );
			
			List<Titulo> titulosAbertos = tituloRepo.findByClienteAndDataPagamentoIsNullAndValorPago( cliente, BigDecimal.ZERO );
			
			// Pode ser que precise de alguma informação dos ambientes... por isso o select qualquer coisa troco por um count
			List<Ambiente> ambientes = ambienteRepo.findByClienteAndStatus( cliente, StatusAmbiente.ATIVO );
			
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


	
	@Transactional
	public Titulo saveTitulo( Usuario usuario, Titulo tituloVO ){
		
		if ( tituloVO == null )
			throw new RuntimeException("Título está em branco.");
		
		if ( usuario == null )
			throw new RuntimeException("Usuário está em branco.");
		
		if ( tituloVO.getCliente() == null || tituloVO.getCliente().getIdCliente() == null || tituloVO.getCliente().getIdCliente() <= 0 )
			throw new RuntimeException("Cliente não foi preenchido.");
		
		if ( tituloVO.getDataEmissao() == null )
			throw new RuntimeException("Data de emissão não foi preenchida.");
		
		if ( tituloVO.getValorLiquido() == null || tituloVO.getValorLiquido().compareTo( BigDecimal.ZERO ) <= 0 )
			throw new RuntimeException("Valor líquido não foi preenchido ou está negativo. Por favor digite um valor Líquido válido.");
		
		if ( tituloVO.getIdTitulo() != null && tituloVO.getIdTitulo() > 0 )
		{
			Titulo tituloAtual = tituloRepo.findOne( tituloVO.getIdTitulo() );
			
			if ( tituloAtual != null && tituloAtual.getDataPagamento() != null )
				throw new RuntimeException( "Não é possível alterar o Título já Pago.");
		}
		
		BigDecimal valorTotal = tituloVO.getValorLiquido().add( tituloVO.getValorTaxas() ).add( tituloVO.getValorJuros() ).add( tituloVO.getValorAcresc() );
		
		valorTotal = valorTotal.subtract( tituloVO.getValorDescontos() );

		valorTotal = valorTotal.setScale( 2, BigDecimal.ROUND_HALF_EVEN );
		
		tituloVO.setValorTotal( valorTotal );
		
		tituloVO.setDataAlteracao( new Date() );
		
		tituloRepo.save( tituloVO );
		
		return tituloVO;
	}


	
	@Transactional
	public void cancelarTitulo( Long idTitulo ){
		
		Titulo tit = tituloRepo.findOne( idTitulo );
		
		if ( tit == null )
			throw new RuntimeException("Título não encontrado.");
		
		if ( tit.getDataPagamento() != null )
			throw new RuntimeException("Título não pode ser cancelado pois já existe Data de Pagamento.");
		
		if ( tit.getDataCancelamento() != null )
			throw new RuntimeException("Título não pode ser cancelado pois já está Cancelado.");
		
		Date hoje = new Date();
		tit.setDataCancelamento(hoje);
		tit.setDataAlteracao( hoje );
		
		tituloRepo.save( tit );
	}


	public Page<Ambiente> getAmbientesPorCliente( Pageable pageable, Long idCliente, String search ){
		
		Cliente cliente = clienteRepo.findOne( idCliente );
		
		if ( cliente == null )
			return null;
		
		Page<Ambiente> ambientePage = null;

		if ( StringUtils.isBlank( search )){
			ambientePage = ambienteRepo.findByCliente( pageable, cliente );
		}
		else {
			String nome = "%"+ search + "%";
			ambientePage = ambienteRepo.findByClienteAndNomeContainingIgnoreCase( pageable, cliente, nome );
		}
		
		return ambientePage;
	}



	public void saveParametro( ParametroDTO parametroDTO, Cliente cliente ){
		
	
		if ( StringUtils.isNotBlank( parametroDTO.getTema() ) ){
			
			Parametro temaParam = parametroRepo.findByCodigoAndCliente( "TEMA", cliente );
			
			if ( temaParam != null ){
				temaParam.setValor( parametroDTO.getTema() );
				parametroRepo.save( temaParam );
			}
		}
	}
	


	public void criarCobrancas(){
		
		
	}
	
	
	@Transactional
	public Titulo geraCobranca( Cliente cliente, Date dataVencimento ){
		
		List<Ambiente> ambientes = ambienteRepo.findByCliente( cliente );
		
		List<CondicaoComercial> condicoesDoCliente = condRepo.findByCliente( cliente );
		
		List<CondicaoComercial> condicoesDosAmbientes = condicoesDoCliente.stream().filter( c -> c.getTipoTaxa().getPorambiente() ).collect( Collectors.toList() );
		List<CondicaoComercial> condicoesGeral = condicoesDoCliente.stream().filter( c -> !c.getTipoTaxa().getPorambiente() ).collect( Collectors.toList() );

		List<Ambiente> ambientesFull = ambientes;
//		List<Ambiente> ambientesProporcionais = ambientes.stream().filter( a -> !isFull( a, historicosPorAmbiente.get( a ) ) ).collect( Collectors.toList() );
		
		final Map<CondicaoComercial, List<Ambiente>> mapAmbientesPorCondicao = new HashMap<CondicaoComercial, List<Ambiente>>();
		
		condicoesDosAmbientes.forEach( condicao -> {
			mapAmbientesPorCondicao.put( condicao, ambientesFull );
		});
		
		BigDecimal liquido = BigDecimal.ZERO;
		
		StringBuilder strBuild = new StringBuilder();
		
		for ( Entry<CondicaoComercial, List<Ambiente>> entry : mapAmbientesPorCondicao.entrySet() ){
			
			CondicaoComercial cond = entry.getKey();
			List<Ambiente> ambList = entry.getValue();
			
			BigDecimal valorCobrancas =  cond.getValor().multiply( new BigDecimal( ambList.size() ) );
			
			strBuild.append( String.format( "Cobrança de %.2f da condição comercial '%s' para cada um dos ambientes : ", valorCobrancas, cond.getTipoTaxa().getDescricao() ) );

			String separados = ambList.stream()
				     .map(amb -> amb.getNome())
				     .collect(Collectors.joining(", ", "[", "]"));	

			strBuild.append( separados );
			strBuild.append(System.lineSeparator());
			
			liquido = liquido.add( valorCobrancas );
		}
		
		StringBuilder strBuildGeral = new StringBuilder();
		
		for ( CondicaoComercial condGeral : condicoesGeral ){
			
			strBuildGeral.append(System.lineSeparator());
			strBuildGeral.append( String.format( "Cobrança de %.2f da condição comercial '%s' para o cliente.", condGeral.getValor().doubleValue(), condGeral.getTipoTaxa().getDescricao() ) );
			
			liquido = liquido.add( condGeral.getValor() );
		}

		Titulo tit = new Titulo();
		
		Date hoje = new Date();
		
		tit.setCliente( cliente );
		tit.setDataCriacao( hoje );
		tit.setDataEmissao( hoje );
		tit.setDataVencimento( dataVencimento );
		tit.setFechamento( true );
		
		tit.setHistorico( strBuild.toString() );
		tit.setValorLiquido( liquido );
		tit.setValorTotal( liquido );
		
		tituloRepo.save( tit );
		
		return tit;
	}
	
	
	public void registraContato(ContatoDTO contatoDTO){

	}
	

}
