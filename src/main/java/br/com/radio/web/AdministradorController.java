package br.com.radio.web;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.radio.model.Usuario;
import br.com.radio.repository.PerfilRepository;
import br.com.radio.service.UsuarioService;
import br.com.radio.web.AbstractController;


@Controller
public class AdministradorController extends AbstractController {
	
	@Autowired
	private PerfilRepository perfilRepo;

	@Autowired
	private UsuarioService usuarioService;
	
	
	@RequestMapping(value="/admin/painel", method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADM_SISTEMA')")
	public String principal( ModelMap model, Principal principal )
	{
		Usuario usuario = usuarioService.getUserByPrincipal( principal );
		
		if ( usuario == null || usuario.getCliente() == null )
			return null;
		
		return "admin/painel";
	}

}
