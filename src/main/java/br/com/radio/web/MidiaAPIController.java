package br.com.radio.web;

import java.io.File;
import java.net.URLDecoder;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import br.com.radio.json.JSONListWrapper;
import br.com.radio.model.Ambiente;
import br.com.radio.model.AmbienteConfiguracao;
import br.com.radio.model.Midia;
import br.com.radio.model.Transmissao;
import br.com.radio.model.Usuario;
import br.com.radio.repository.AmbienteConfiguracaoRepository;
import br.com.radio.repository.AmbienteRepository;
import br.com.radio.repository.CategoriaRepository;
import br.com.radio.repository.MidiaRepository;
import br.com.radio.repository.TransmissaoRepository;
import br.com.radio.service.MidiaService;
import br.com.radio.service.ProgramacaoMusicalService;
import br.com.radio.service.UsuarioService;

@Controller
public class MidiaAPIController extends AbstractController {
	
	private static final Logger logger = Logger.getLogger(MidiaAPIController.class);

	
	// DAOs =====================
	@Autowired
	private AmbienteRepository ambienteRepo;
	@Autowired
	private CategoriaRepository categoriaRepo;
	@Autowired
	private MidiaRepository midiaRepo;
	@Autowired
	private TransmissaoRepository transmissaoRepo;
	@Autowired
	private AmbienteConfiguracaoRepository ambienteConfigRepo;
	// DAOs =====================
	
	
	// Services =================
	@Autowired
	private MidiaService midiaService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private ProgramacaoMusicalService progMusicalService;
	// Services =================
	
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleError(HttpServletRequest req, Exception exception) {
		
		
		// TODO: lembrar de tirar esse stacktrace
		exception.printStackTrace();
			
		logger.error("Request: " + req.getRequestURL() + " raised " + exception);
		
		String jsonResult = writeSingleErrorAsJSONErroMessage( "erro", exception.getMessage() );
		
		return new ResponseEntity<String>( jsonResult, HttpStatus.INTERNAL_SERVER_ERROR );
		
	}
	
	
	
	/**
	 *  Como esse método não envolve tela ele é exclusivo da API
	 *  
	 * @param idAmbiente
	 * @param codigo
	 * @param file
	 * @param categorias
	 * @param principal
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/api/ambientes/{idAmbiente}/upload-midia", method=RequestMethod.POST)
	public ResponseEntity<String> upload(
    		@PathVariable Long idAmbiente,
    		@RequestParam("codigo") String codigo,  // código da categoria
    		@RequestParam("file") MultipartFile file, 
    		@RequestParam(name="descricao", required=false) String descricao,
    		Principal principal, 
    		Model model )
	{
		String jsonResult = "";

		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente() == null )
			return new ResponseEntity<String>( writeSingleErrorAsJSONErroMessage( "alertArea", "Usuário não encontrado ou Cliente não encontrada." ), HttpStatus.INTERNAL_SERVER_ERROR );
		
		Ambiente ambiente = ambienteRepo.findOne( idAmbiente );
		
		if ( ambiente != null )
		{
			if ( file != null && !file.isEmpty() )
			{
				try
				{
					midiaService.saveUpload( file, codigo, usuario.getCliente(), ambiente, descricao );
							
					jsonResult = writeOkResponse();
				}
				catch ( Exception e )
				{
					e.printStackTrace();

					jsonResult = writeSingleErrorAsJSONErroMessage( "alertArea", e.getMessage() );
					return new ResponseEntity<String>( jsonResult, HttpStatus.INTERNAL_SERVER_ERROR );
				}
			}
			else
			{
				jsonResult = writeSingleErrorAsJSONErroMessage( "alertArea", "Arquivo está vazio" );
				return new ResponseEntity<String>( jsonResult, HttpStatus.INTERNAL_SERVER_ERROR );
			}
		}

		return new ResponseEntity<String>( jsonResult, HttpStatus.OK );
    }	

	
	@RequestMapping(value="/api/upload-musica", method=RequestMethod.POST)
	public ResponseEntity<String> uploadMusica(
    		@RequestParam("file") MultipartFile file, 
    		@RequestParam(name="descricao", required=false) String descricao,
    		@RequestParam("generos[]") Long[] generos,
    		Principal principal, 
    		Model model )
	{
		String jsonResult = "";

		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente() == null )
			return new ResponseEntity<String>( writeSingleErrorAsJSONErroMessage( "alertArea", "Usuário não encontrado ou Cliente não encontrada." ), HttpStatus.INTERNAL_SERVER_ERROR );
		
		if ( file != null && !file.isEmpty() )
		{
			try
			{
				midiaService.saveUploadMusica( file, "musica", usuario.getCliente(), descricao, generos );
						
				jsonResult = writeOkResponse();
			}
			catch ( Exception e )
			{
				//e.printStackTrace();

				jsonResult = writeSingleErrorAsJSONErroMessage( "alertArea", e.getMessage() );
				return new ResponseEntity<String>( jsonResult, HttpStatus.INTERNAL_SERVER_ERROR );
			}
		}
		else
		{
			jsonResult = writeSingleErrorAsJSONErroMessage( "alertArea", "Arquivo está vazio" );
			return new ResponseEntity<String>( jsonResult, HttpStatus.INTERNAL_SERVER_ERROR );
		}

		return new ResponseEntity<String>( jsonResult, HttpStatus.OK );
    }	

	@RequestMapping(value = "/api/ambientes/{idAmbiente}/transmissoes/{idTransmissao}/midia", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<FileSystemResource> downloadTransmissao(@PathVariable Long idAmbiente, @PathVariable Long idTransmissao, Principal principal) {
		
		// TODO: pensar em uma maneira de dropar requests repetidos pra evitar ataque de DDOS
		
		Ambiente ambiente = ambienteRepo.findOne( idAmbiente );
		
		if ( ambiente == null )
			return ResponseEntity.badRequest().body( null );
		
		Transmissao transmissao = transmissaoRepo.findByIdTransmissaoAndAmbienteAndLinkativoTrue( idTransmissao, ambiente );
		
		if ( transmissao == null )
			return ResponseEntity.unprocessableEntity().body( null );
		
		Midia midia = transmissao.getMidia();
		
		File arquivo = new File( midia.getFilepath() );
		
		FileSystemResource fsr = new FileSystemResource( arquivo );

		String name = Integer.valueOf( midia.getNome().hashCode() ).toString();
		
		return ResponseEntity.ok()
				.header( "Content-Disposition", "attachment; filename=\""+ name +"\"" )
				.contentLength( midia.getFilesize() )
				.contentType( MediaType.parseMediaType( midia.getMimetype() ) )
				.body( fsr );
	}

	
	
	@RequestMapping(value = "/api/ambientes/{idAmbiente}/midia/{idMidia}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<FileSystemResource> downloadMidia(@PathVariable Long idAmbiente, @PathVariable Long idMidia, Principal principal) {
		
		// TODO: pensar em uma maneira de dropar requests repetidos pra evitar ataque de DDOS
		
		Ambiente ambiente = ambienteRepo.findOne( idAmbiente );
		
		// Esse método por exemplo não vai ser chamado repetidas vezes pelo mesmo ambiente... no máximo 4 vezes em seguida...
		
		if ( ambiente == null )
			return ResponseEntity.badRequest().body( null );
		
		Midia midia = midiaRepo.findOne( idMidia );
		
		if ( midia == null )
			return ResponseEntity.unprocessableEntity().body( null );
		
		File arquivo = new File( midia.getFilepath() );
		
		FileSystemResource fsr = new FileSystemResource( arquivo );

		String name = Integer.valueOf( midia.getNome().hashCode() ).toString();
		
		return ResponseEntity.ok()
				.header( "Content-Disposition", "attachment; filename=\""+ name +"\"" )
				.contentLength( midia.getFilesize() )
				.contentType( MediaType.parseMediaType( midia.getMimetype() ) )
				.body( fsr );
	}

	
	
	
	
	
	@RequestMapping( value = { "/api/ambientes/{idAmbiente}/transmissoes" }, method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody JSONListWrapper<Transmissao> listTransmissaoAtiva( @PathVariable Long idAmbiente, Principal principal, HttpServletRequest request )
	{
		Ambiente ambiente = ambienteRepo.findOne( idAmbiente );
		
		List<Transmissao> transmissoes = transmissaoRepo.findByAmbienteAndLinkativoOrderByProgramacao_idProgramacaoAscPosicaoplayAsc( ambiente, true );

		String baseURL = StringUtils.replace( request.getRequestURL().toString(), request.getServletPath(), "" );
		
		transmissoes.forEach( t -> {
			
			String link = baseURL + t.getLink();
			
			t.setLink( link ); 
		});
		
		
		JSONListWrapper<Transmissao> jsonList = new JSONListWrapper<Transmissao>( transmissoes , transmissoes.size() );

		return jsonList;
	}
	
	
	
	
	@RequestMapping( value = "/api/ambientes/{idAmbiente}/transmissoes/new", method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody String geraTransmissao( @PathVariable Long idAmbiente, 
    		@RequestParam(name="relatorio", required=false) String relatorio,
			Principal principal, HttpServletRequest request )
	{
		// esse aqui também tem que proteger ou arrancar..
		
		Ambiente ambiente = ambienteRepo.findOne( idAmbiente );
		
//		String baseURL = StringUtils.replace( request.getRequestURL().toString(), request.getServletPath(), "" );
		
		if ( ambiente != null )
			progMusicalService.geraTransmissao( ambiente );
		
		
		String result = null;

		if ( StringUtils.isNotBlank( relatorio ) && "S".equals(relatorio))
		{
			
			List<Transmissao> transmissoes = transmissaoRepo.findByAmbienteAndLinkativoOrderByProgramacao_idProgramacaoAscPosicaoplayAsc( ambiente, true ); 

			JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
			
			transmissoes.forEach( t -> {
				
				Midia m = t.getMidia();
			
				JsonObject obj = Json.createObjectBuilder()
					.add( "nome", m.getNome() )
					.add( "ordem", t.getPosicaoplay() )
					.add( "tipo", t.getCategoria().getDescricao() ).build();
				
				jsonArrayBuilder.add( obj );
			});
			
			JsonObject jsonObject = Json.createObjectBuilder()
					.add("transmissao", jsonArrayBuilder)
					.build();
			
			result = jsonObject.toString();
		}
		else
			result = writeOkResponse();
		
		
		return result;
	}
	
	
	
	

	
	/**
	 * Esse método vai usar o horário do servidor para determinar a música que deve ser tocada agora ( ao vivo )
	 * 
	 * @param idAmbiente
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/api/ambientes/{idAmbiente}/transmissoes/live", method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody Transmissao getLinkTransmissaoAoVivo(@PathVariable Long idAmbiente, Principal principal, HttpServletRequest request ) {
		
		// TODO: pensar em uma maneira de dropar requests repetidos pra evitar ataque de DDOS
		
		Ambiente ambiente = ambienteRepo.findOne( idAmbiente );
		
		if ( ambiente == null )
			throw new RuntimeException( "Ambiente não encontrado" );

		
		Transmissao transmissao = progMusicalService.getTransmissaoAoVivo( ambiente );
		
		String baseURL = StringUtils.replace( request.getRequestURL().toString(), request.getServletPath(), "" );
		
		String link = baseURL + transmissao.getLink();

		transmissao.setLink( link );
		
		if ( transmissao == null )
			throw new RuntimeException( "Não existe transmissão. Verifique se o expediente já terminou." );
		
		return transmissao;
	}
	
	
	
	/**
	 * Esse método vai usar o horário do servidor para determinar a música que deve estar tocando nesse momento e vai pegar a próxima...
	 * 
	 * @param idAmbiente
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/api/ambientes/{idAmbiente}/transmissoes/live/next", method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody Transmissao getLinkTransmissaoAoVivoNext(@PathVariable Long idAmbiente, Principal principal, HttpServletRequest request) {
		
		// TODO: pensar em uma maneira de dropar requests repetidos pra evitar ataque de DDOS
		
		Ambiente ambiente = ambienteRepo.findOne( idAmbiente );
		
		if ( ambiente == null )
			throw new RuntimeException( "Ambiente não encontrado" );
		
		AmbienteConfiguracao configuracao = ambienteConfigRepo.findByAmbiente( ambiente );
		
		if ( configuracao != null && configuracao.getAvancarRetornar() == false )
			throw new RuntimeException( "Não é possível avançar a transmissão." );

		Transmissao transmissao = progMusicalService.getTransmissaoAoVivoSkipForward( ambiente );
		
		System.out.println( transmissao.getIdTransmissao() );
		
		
		String baseURL = StringUtils.replace( request.getRequestURL().toString(), request.getServletPath(), "" );
		
		String link = baseURL + transmissao.getLink();

		transmissao.setLink( link );
		
		if ( transmissao == null )
			throw new RuntimeException( "Não existe transmissão. Verifique se o expediente já terminou." );
		
		return transmissao;
	}

	
	
	
	@RequestMapping(value="/ambientes/{idAmbiente}/midia/{idMidia}", method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteMidia( @PathVariable Long idAmbiente, @PathVariable Long idMidia, Principal principal, Model model )
	{
		String jsonResult = "";

		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente() == null )
			return new ResponseEntity<String>( writeSingleErrorAsJSONErroMessage( "alertArea", "Usuário não encontrado ou Cliente não encontrada." ), HttpStatus.INTERNAL_SERVER_ERROR );
		
		Ambiente ambiente = ambienteRepo.findOne( idAmbiente );
		
		if ( ambiente != null )
		{
			try
			{
				midiaService.deleteMidiaSePossivel( idMidia, ambiente );
				jsonResult = writeOkResponse();
			}
			catch ( Exception e )
			{
				e.printStackTrace();

				jsonResult = writeSingleErrorAsJSONErroMessage( "alertArea", e.getMessage() );
				return new ResponseEntity<String>( jsonResult, HttpStatus.INTERNAL_SERVER_ERROR );
			}
		}

		return new ResponseEntity<String>( jsonResult, HttpStatus.OK );
    }	

	
	
	
	@RequestMapping( value = "/api/syncfilesystem", method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody String sync()
	{
		midiaService.syncMusicFileSystem();
		
		return writeOkResponse();
	}
		
	
		
	
}
