package br.com.radio.schedule;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.radio.enumeration.StatusAmbiente;
import br.com.radio.model.Ambiente;
import br.com.radio.programacaomusical.ProgramacaoMusicalService;
import br.com.radio.repository.AmbienteRepository;
import br.com.radio.service.ClienteService;
import br.com.radio.service.EventoService;
import br.com.radio.service.UsuarioService;

@Component
public class TarefasAgendadas {
	
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    
	private static final Logger logger = Logger.getLogger(TarefasAgendadas.class);

    @Autowired
    private ProgramacaoMusicalService progMusicalService;
    
    @Autowired
    private EventoService eventoService;
    
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private AmbienteRepository ambienteRepo;
	
	@Scheduled(cron="0 0 8-9 * * *")
	public void verificarProgramacaoAmbientes()
	{
		List<Ambiente> ambientes = ambienteRepo.findByStatus( StatusAmbiente.ATIVO );
		
		ambientes.forEach( amb -> {

			System.out.println(  amb );
			
			try
			{
				progMusicalService.criaProgramacaoMusicalDoDiaParaAmbiente( amb );	
			}
			catch ( Exception e )
			{
				logger.error( String.format( "Erro ao criar programação musical do ambiente %s ", amb.getNome() ), e );
				
				e.printStackTrace();
			}
		});
	}

	
	
	@Scheduled(cron="0 */5 * * * *")  // a cada 5 minutos roda
	public void verificarEventos()
	{
		List<Ambiente> ambientes = ambienteRepo.findByStatus( StatusAmbiente.ATIVO );
		ambientes.forEach( amb -> {
			eventoService.criaTransmissaoDosEventos( amb );
		});
	}
	
	
	@Scheduled(cron="0 0 6 * * *")
	public void verificarCobrancas(){
		
		logger.info( "Rodando task de criação de cobranças..." );

		clienteService.criarCobrancas();
		
		logger.info( "Fim task de criação de cobranças..." );
	}
	

	/**
	 *  Se por algum problema o SessionListener não reportar que uma sessão morreu... esse método vai garantir que os registros da tabela AcessoUsuario pelo menos ganhem um desfecho
	 *  
	 *  Ele vai buscar por registros perdidos de acesso que não tem dataLogout... entretanto vai ignorar sempre o último de cada usuário (pode ser que esteja logado)
	 */
	@Scheduled(cron="0 */30 * * * *")  // a cada 30 minutos
	public void marcarSessoesAcessosEncerrados(){

		logger.info( "Rodando task de finalização de Acesso sem Logout..." );

		usuarioService.finalizaAcessosSemLogout();

		logger.info( "Fim task de finalização de Acesso sem Logout..." );
	}
	
}
