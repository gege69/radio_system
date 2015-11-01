package br.com.radio.security.config;

import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.allanditzel.springframework.security.web.csrf.CsrfTokenResponseHeaderBindingFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfigMulti {

	
	private static final String DEF_USERS_BY_USERNAME_QUERY = "SELECT LOGIN AS USERNAME, PASSWORD, ATIVO AS ENABLED FROM USUARIO WHERE LOGIN = ? ";
	
	private static final String DEF_AUTHORITIES_BY_USERNAME_QUERY = " SELECT LOGIN AS USERNAME, CODIGO FROM USUARIO USU" + 
																	" LEFT JOIN USUARIO_PERMISSAO USP ON USP.ID_USUARIO = USU.ID_USUARIO" + 
																	" LEFT JOIN PERMISSAO PRM ON PRM.ID_PERMISSAO = USP.ID_PERMISSAO" + 
																	" WHERE LOGIN = ? ";
	
	private static final String DEF_GROUP_AUTHORITIES_BY_USERNAME_QUERY = 	" SELECT PER.ID_PERFIL ID, PER.NOME GROUP_NAME, PRM.CODIGO AUTHORITY" +
																			" FROM PERFIL PER" +
																			" LEFT JOIN USUARIO_PERFIL UPF ON UPF.ID_PERFIL = PER.ID_PERFIL" +
																			" LEFT JOIN PERFIL_PERMISSAO PRP ON PRP.ID_PERFIL = UPF.ID_PERFIL" +
																			" LEFT JOIN PERMISSAO PRM ON PRM.ID_PERMISSAO = PRP.ID_PERMISSAO" +
																			" LEFT JOIN USUARIO USU ON USU.ID_USUARIO = UPF.ID_USUARIO" +
																			" WHERE " +
																			" USU.LOGIN = ? ";

	@Resource
    private Environment environment;
	
	@Autowired
	private DataSource dataSource;

	@Bean
	public PasswordEncoder getPasswordEncoder(){
		
		return new BCryptPasswordEncoder( 8 );
		
	}
	
	
	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		
	  auth
	    .jdbcAuthentication()
	      .passwordEncoder( getPasswordEncoder() )
	      .dataSource(dataSource)
	      .usersByUsernameQuery(DEF_USERS_BY_USERNAME_QUERY)
	      .authoritiesByUsernameQuery(DEF_AUTHORITIES_BY_USERNAME_QUERY)
	      .groupAuthoritiesByUsername( DEF_GROUP_AUTHORITIES_BY_USERNAME_QUERY );
	  
	}

	
	@Configuration
    @Order(1)                                                        
    public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
		
        protected void configure(HttpSecurity http) throws Exception {
        	
        	
        	RequestMatcher csrfRequestMatcher = new RequestMatcher() 
        	{
	    		// Always allow the HTTP GET method
	    		private Pattern allowedMethods = Pattern.compile("^GET$");
	    		  
	    		// Disable CSFR protection on the following urls:
	    		private AntPathRequestMatcher[] requestMatchers = {
	    		    new AntPathRequestMatcher("/api/**")
	    		};
	
	    		@Override
	    		public boolean matches(HttpServletRequest request) {
	    		    // Skip allowed methods
	    		    if (allowedMethods.matcher(request.getMethod()).matches()) {
	    		        return false;
	    		    }   
	
	    		    // If the request match one url the CSFR protection will be disabled
	    		    for (AntPathRequestMatcher rm : requestMatchers) {
	    		        if (rm.matches(request)) { return false; }
	    		    }
	
	    		    return true;
	    		} // method matches
    		};
        	
        	
            http
            	// desabilitando o CSRF para as URLs de API
            	.csrf().requireCsrfProtectionMatcher( csrfRequestMatcher )
            	.and()
                .antMatcher( "/api/**")                               
                .authorizeRequests().anyRequest().authenticated()  
                .and()
                .httpBasic();
            
            
            // Não é mais necessário porque não estou fazendo SPA e o client vai sempre usar basic authentication
//			CsrfTokenResponseHeaderBindingFilter csrfTokenFilter = new CsrfTokenResponseHeaderBindingFilter();    
//		    http.addFilterAfter(csrfTokenFilter, CsrfFilter.class);
        }
    }
	
	
	@Configuration
	@Order(2)  
    public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
		
		@Override
	    public void configure(WebSecurity web) throws Exception {
	        web.ignoring().antMatchers("/favicon.ico", "/resources/**", "/bundle/**", "/css/**", "/faviconfolder/**", "/js/**" );
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
				.antMatchers( "/register" ).permitAll()
				.antMatchers( "/login**" ).permitAll()
				.antMatchers( "/admin/**" ).hasRole( "ADMIN" )
				.anyRequest().authenticated()
				.and()
//			.requiresChannel()
//				.anyRequest().requiresSecure()
//				.and()

			// This is where we configure our login form.
			// login-page: the page that contains the login screen
			// login-processing-url: this is the URL to which the login form should be submitted
			// default-target-url: the URL to which the user will be redirected if they login successfully
			// authentication-failure-url: the URL to which the user will be redirected if they fail login
			// username-parameter: the name of the request parameter which contains the username
			// password-parameter: the name of the request parameter which contains the password
			.formLogin()
				.loginPage( "/login" )
				.defaultSuccessUrl("/principal", true)
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
	
	
	

	
	
}
