package br.com.radio.config;

import java.io.IOException;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import br.com.radio.security.config.SecurityConfigMulti;
import br.com.radio.web.config.WebAppConfig;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan( basePackages = { "br.com.radio.*" } )
@EnableJpaRepositories( basePackages = { "br.com.radio.*" } )
@EnableTransactionManagement
@PropertySource({"classpath:pird.properties", "classpath:db.properties"})
@Import( { WebAppConfig.class, SecurityConfigMulti.class, SchedulingConfig.class, AsyncConfig.class} )
public class AppConfig {

	private static final Logger logger = Logger.getLogger(AppConfig.class);
	
	private ConfigurableEnvironment env;


    // Esse é o Setter do Environment
	@Autowired
	public void setEnv( ConfigurableEnvironment env )
	{
		PropertiesPropertySource props = new PIRDBusiness().getDuxusPropertySource();

		env.getPropertySources().addFirst(props);
		
		this.env = env;
	}


	@Bean( destroyMethod = "close" )
	@Profile("default")
	public DataSource getDataSourceDesenvolvimento()
	{
		String path = env.getRequiredProperty("doido");
		logger.info(path);

		String fdp = env.getRequiredProperty("fdp.path");
		logger.info(fdp);

		HikariConfig dataSourceConfig = new HikariConfig();
		dataSourceConfig.setDriverClassName( env.getRequiredProperty( "dev.db.driver" ) );
		dataSourceConfig.setJdbcUrl( env.getRequiredProperty( "dev.db.url" ) );
		dataSourceConfig.setUsername( env.getRequiredProperty( "dev.db.username" ) );
		dataSourceConfig.setPassword( env.getRequiredProperty( "dev.db.password" ) );
		
		logger.info( MensagensProfile.getMensagemDesenvolvimento() );
		
		logger.info( "DataSource Conectado em Desenvolvimento" );

		return new HikariDataSource( dataSourceConfig );
	}

	


	@Bean( destroyMethod = "close" )
	@Profile("prod")
	public DataSource getDataSourceProducao()
	{
		HikariConfig dataSourceConfig = new HikariConfig();
		dataSourceConfig.setDriverClassName( env.getRequiredProperty( "prod.db.driver" ) );
		dataSourceConfig.setJdbcUrl( env.getRequiredProperty( "prod.db.url" ) );
		dataSourceConfig.setUsername( env.getRequiredProperty( "prod.db.username" ) );
		
		dataSourceConfig.setPassword( env.getRequiredProperty( "prod.db.password" ) );

		logger.info( MensagensProfile.getMensagemProducao() );
		
		logger.info( "DataSource Conectado em Produção" );
		
		return new HikariDataSource( dataSourceConfig );
	}

	
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource ds)
	{
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource( ds );
		entityManagerFactoryBean.setPackagesToScan( "br.com.radio.model" );
		
		HibernateJpaVendorAdapter jpaAdapter = new HibernateJpaVendorAdapter();
		
		entityManagerFactoryBean.setJpaVendorAdapter( jpaAdapter );
		entityManagerFactoryBean.setLoadTimeWeaver( new InstrumentationLoadTimeWeaver() );

		Properties jpaProperties = new Properties();

		jpaProperties.put( "hibernate.dialect", env.getRequiredProperty( "hibernate.dialect" ) );
		jpaProperties.put( "hibernate.hbm2ddl.auto", env.getRequiredProperty( "hibernate.hbm2ddl.auto" ) );
		jpaProperties.put( "hibernate.show_sql", env.getRequiredProperty( "hibernate.show_sql" ) );
		jpaProperties.put( "hibernate.format_sql", env.getRequiredProperty( "hibernate.format_sql" ) );
		jpaProperties.put( "hibernate.enable_lazy_load_no_trans", env.getRequiredProperty( "hibernate.enable_lazy_load_no_trans" ) );
		jpaProperties.put( "hibernate.jdbc.batch_size", env.getRequiredProperty( "hibernate.jdbc.batch_size" ) );

		entityManagerFactoryBean.setJpaProperties( jpaProperties );

		return entityManagerFactoryBean;
	}

	@Bean
	public JpaTransactionManager transactionManager( EntityManagerFactory entityManagerFactory )
	{

		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory( entityManagerFactory );

		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslationPostProcessor()
	{

		PersistenceExceptionTranslationPostProcessor exceptionTranslationPostProcessor = new PersistenceExceptionTranslationPostProcessor();

		return exceptionTranslationPostProcessor;
	}


	@Bean  
    public ResourceBundleMessageSource messageSource() {  
		String path = env.getRequiredProperty("doido");
		logger.info(path);

		String fdp = env.getRequiredProperty("fdp.path");
		logger.info(fdp);

        ResourceBundleMessageSource source = new ResourceBundleMessageSource();  
        source.setBasenames( "application", "messages" );
        source.setUseCodeAsDefaultMessage(true);  
        return source;  
    }  
	

}
