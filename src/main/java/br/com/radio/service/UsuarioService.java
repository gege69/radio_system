package br.com.radio.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.radio.model.Usuario;
import br.com.radio.repository.UsuarioDAO;

public class UsuarioService implements UserDetailsService {

	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Override
	public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException
	{
		Usuario usuario = usuarioDAO.findLastResult( "cd_login_usu", "id_usuario_usu", username );
		
		if ( usuario != null )
		{
			boolean enabled = true;
	        boolean accountNonExpired = true;
	        boolean credentialsNonExpired = true;
	        boolean accountNonLocked = true;
			
			List<GrantedAuthority> permissoesList = new ArrayList<GrantedAuthority>();
			
			permissoesList.add( new SimpleGrantedAuthority( "INCLUIR_AMB" ) );
			permissoesList.add( new SimpleGrantedAuthority( "ADMINISTRAR_AMB" ) );
			
			User user = new User( usuario.getNm_usuario_usu(), usuario.getCd_password_usu(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, permissoesList );
			
			return user;
		}
		
		return null;
	}

}
