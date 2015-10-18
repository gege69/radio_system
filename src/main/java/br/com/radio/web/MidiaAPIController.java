package br.com.radio.web;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

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
import br.com.radio.model.Midia;
import br.com.radio.model.Transmissao;
import br.com.radio.model.Usuario;
import br.com.radio.repository.AmbienteRepository;
import br.com.radio.repository.CategoriaRepository;
import br.com.radio.repository.MidiaRepository;
import br.com.radio.repository.TransmissaoRepository;
import br.com.radio.service.MidiaService;
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
	// DAOs =====================
	
	
	// Services =================
	@Autowired
	private MidiaService midiaService;
	@Autowired
	private UsuarioService usuarioService;
	// Services =================
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleError(HttpServletRequest req, Exception exception) {
			
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
		
		if ( usuario == null || usuario.getEmpresa() == null )
			return new ResponseEntity<String>( writeSingleErrorAsJSONErroMessage( "alertArea", "Usuário não encontrado ou Empresa não encontrada." ), HttpStatus.INTERNAL_SERVER_ERROR );
		
		Ambiente ambiente = ambienteRepo.findOne( idAmbiente );
		
		if ( ambiente != null )
		{
			if ( file != null && !file.isEmpty() )
			{
				try
				{
					midiaService.saveUpload( file, codigo, usuario.getEmpresa(), ambiente, descricao );
							
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

	

	@RequestMapping(value = "/api/ambientes/{idAmbiente}/transmissoes/{idTransmissao}", method = RequestMethod.GET)
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

	
	
	
	@RequestMapping( value = { "/api/ambientes/{idAmbiente}/transmissoes" }, method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8 )
	public @ResponseBody JSONListWrapper<Transmissao> listTransmissaoAtiva( @PathVariable Long idAmbiente, Principal principal, HttpServletRequest request )
	{
		Ambiente ambiente = ambienteRepo.findOne( idAmbiente );
		
		List<Transmissao> transmissoes = transmissaoRepo.findByAmbienteAndLinkativoOrderByProgramacao_idProgramacaoAscOrdemPlayAsc( ambiente, true );

		String baseURL = StringUtils.replace( request.getRequestURL().toString(), request.getServletPath(), "" );
		
		transmissoes.forEach( t -> {
			
			String link = baseURL + String.format( "/api/ambientes/%d/transmissoes/%d", idAmbiente, t.getIdTransmissao() );
			
			t.setLink( link ); 
		});
		
		
		JSONListWrapper<Transmissao> jsonList = new JSONListWrapper<Transmissao>( transmissoes , transmissoes.size() );

		return jsonList;
	}
	
	
}
