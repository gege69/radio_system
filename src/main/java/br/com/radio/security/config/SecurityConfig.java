package br.com.radio.security.config;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.radio.service.UsuarioService;

//@Configuration
//@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
	private static final String DEF_USERS_BY_USERNAME_QUERY = "SELECT CD_LOGIN_USU AS USERNAME, CD_PASSWORD_USU AS PASSWORD, FL_ATIVO_USU AS ENABLED FROM USUARIO WHERE CD_LOGIN_USU = ? ";
	
	private static final String DEF_AUTHORITIES_BY_USERNAME_QUERY = " SELECT CD_LOGIN_USU AS USERNAME, CD_PERMISS_PRM FROM USUARIO USU " + 
																	" INNER JOIN USUARIO_PERMISSAO USP ON USP.ID_USUARIO_USU = USU.ID_USUARIO_USU " +
																	" INNER JOIN PERMISSAO PRM ON PRM.ID_PERMISSAO_PRM = USP.ID_PERMISSAO_PRM " +
																	" WHERE CD_LOGIN_USU = ? ";
	
	private static final String DEF_GROUP_AUTHORITIES_BY_USERNAME_QUERY = 	" SELECT PER.ID_PERFIL_PER ID, PER.NM_PERFIL_PER, PRM.CD_PERMISS_PRM" +
																			" FROM PERFIL PER" +
																			" INNER JOIN USUARIO_PERFIL UPF ON UPF.ID_PERFIL_PER = PER.ID_PERFIL_PER" +
																			" INNER JOIN PERFIL_PERMISSAO PRP ON PRP.ID_PERFIL_PER = UPF.ID_PERFIL_PER" +
																			" INNER JOIN PERMISSAO PRM ON PRM.ID_PERMISSAO_PRM = PRP.ID_PERMISSAO_PRM" +
																			" INNER JOIN USUARIO USU ON USU.ID_USUARIO_USU = UPF.ID_USUARIO_USU" +
																			" WHERE " +
																			" USU.CD_LOGIN_USU = ? ";

	@Resource
    private Environment environment;
	
//	@Resource
//    private AuthenticationProvider springSecurityAuthenticationProvider;
	
	@Autowired
	private DataSource dataSource;
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/favicon.ico", "/resources/**", "/bundle/**");
    }
	
	@Bean
	public RoleVoter getRoleVoter()
	{
		RoleVoter voter = new RoleVoter();
		voter.setRolePrefix( "" );
		
		return voter;
	}
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
	  auth
	    .jdbcAuthentication()
	      .dataSource(dataSource)
	      .usersByUsernameQuery(DEF_USERS_BY_USERNAME_QUERY)
	      .authoritiesByUsernameQuery(DEF_AUTHORITIES_BY_USERNAME_QUERY)
	      .groupAuthoritiesByUsername( DEF_GROUP_AUTHORITIES_BY_USERNAME_QUERY )
	      .passwordEncoder( new BCryptPasswordEncoder( 8 ) );
	  
	  auth.userDetailsService(new UsuarioService());
	  
	}
	
	
	@Override
	protected void configure( HttpSecurity http ) throws Exception
	{
//		http
//		.formLogin().loginPage("/login").defaultSuccessUrl("/").permitAll()
//		.and()
//	    .authorizeRequests().antMatchers("/**").authenticated();
		
		http
////			.requiresChannel().anyRequest().requiresSecure()    // depois mudar pra páginas que não precisam de HTTPS
////	        .and()
////	        .portMapper().http(Integer.parseInt(environment.getProperty("http.port", "8080"))).mapsTo(Integer.parseInt(environment.getProperty("https.port", "8443")))
////	        .and()
//	        .sessionManagement().sessionFixation().newSession()
//	        .and()
//	        .authorizeRequests().antMatchers("/register", "/updatePassword").permitAll()
//	        .and()
//	        .formLogin().loginPage("/login").defaultSuccessUrl("/").permitAll()
//	        .and()
//	        .logout().logoutUrl("/logout").logoutSuccessUrl("/login").invalidateHttpSession(true).deleteCookies("JSESSIONID")
//	        .and()
//	        .authorizeRequests().antMatchers("/**").authenticated();
			.authorizeRequests().antMatchers("/**").permitAll();
	}

	
	
}
