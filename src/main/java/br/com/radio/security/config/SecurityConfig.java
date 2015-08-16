package br.com.radio.security.config;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
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
	
	@Autowired
	private DataSource dataSource;
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/favicon.ico", "/resources/**", "/bundle/**", "/css/**", "/faviconfolder/**", "/js/**");
    }

	@Bean
	public PasswordEncoder getPasswordEncoder(){
		
		return new BCryptPasswordEncoder( 8 );
		
	}
	

	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		
	  auth
	    .jdbcAuthentication()
	      .dataSource(dataSource)
	      .usersByUsernameQuery(DEF_USERS_BY_USERNAME_QUERY)
	      .authoritiesByUsernameQuery(DEF_AUTHORITIES_BY_USERNAME_QUERY)
	      .groupAuthoritiesByUsername( DEF_GROUP_AUTHORITIES_BY_USERNAME_QUERY );
	  
//	  auth.passwordEncoder( new BCryptPasswordEncoder( 8 ) );
	  
	}
	
	
	@Override
	protected void configure( HttpSecurity http ) throws Exception
	{
		http

		// access-denied-page: this is the page users will be
		// redirected to when they try to access protected areas.
		.exceptionHandling()
			.accessDeniedPage( "/403" )
			.and()

		// The intercept-url configuration is where we specify what roles are allowed access to what areas.
		// We specifically force the connection to https for all the pages, although it could be sufficient
		// just on the login page. The access parameter is where the expressions are used to control which
		// roles can access specific areas. One of the most important things is the order of the intercept-urls,
		// the most catch-all type patterns should at the bottom of the list as the matches are executed
		// in the order they are configured below. So /** (anyRequest()) should always be at the bottom of the list.
		.authorizeRequests()
			.antMatchers( "/register", "/updatePassword" ).permitAll()
			.antMatchers( "/login**" ).permitAll()
			.antMatchers( "/admin/**" ).hasRole( "ADMIN" )
			.anyRequest().authenticated()
			.and()
//		.requiresChannel()
//			.anyRequest().requiresSecure()
//			.and()

		// This is where we configure our login form.
		// login-page: the page that contains the login screen
		// login-processing-url: this is the URL to which the login form should be submitted
		// default-target-url: the URL to which the user will be redirected if they login successfully
		// authentication-failure-url: the URL to which the user will be redirected if they fail login
		// username-parameter: the name of the request parameter which contains the username
		// password-parameter: the name of the request parameter which contains the password
		.formLogin()
			.loginPage( "/login" )
			.defaultSuccessUrl("/gerenciador/principal", true)
			.failureUrl( "/login?err=1" )
			.usernameParameter( "username" )
			.passwordParameter( "password" )
			.and()

		// This is where the logout page and process is configured. The logout-url is the URL to send
		// the user to in order to logout, the logout-success-url is where they are taken if the logout
		// is successful, and the delete-cookies and invalidate-session make sure that we clean up after logout
		.logout()
			.logoutRequestMatcher( new AntPathRequestMatcher( "/logout" ) )
			.logoutSuccessUrl( "/login?out=1" )
			.deleteCookies( "JSESSIONID" )
			.invalidateHttpSession( true )
			.and()

		// The session management is used to ensure the user only has one session. This isn't
		// compulsory but can add some extra security to your application.
		.sessionManagement()
			.invalidSessionUrl( "/login?time=1" )
			.maximumSessions( 1 );
		
		
	}

	
	
}
