package br.com.radio.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
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
@Import( { WebAppConfig.class, SecurityConfigMulti.class } )
public class AppConfig {

	@Autowired
	private Environment env;

	@Bean( destroyMethod = "close" )
	public DataSource getDataSource()
	{
		HikariConfig dataSourceConfig = new HikariConfig();
		dataSourceConfig.setDriverClassName( env.getRequiredProperty( "db.driver" ) );
		dataSourceConfig.setJdbcUrl( env.getRequiredProperty( "db.url" ) );
		dataSourceConfig.setUsername( env.getRequiredProperty( "db.username" ) );
		dataSourceConfig.setPassword( env.getRequiredProperty( "db.password" ) );

		return new HikariDataSource( dataSourceConfig );
	}


	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory()
	{

		DataSource dataSource = getDataSource();

		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource( dataSource );
		entityManagerFactoryBean.setPackagesToScan( "br.com.radio.model" );
		entityManagerFactoryBean.setJpaVendorAdapter( new HibernateJpaVendorAdapter() );
		entityManagerFactoryBean.setLoadTimeWeaver( new InstrumentationLoadTimeWeaver() );

		Properties jpaProperties = new Properties();

		jpaProperties.put( "hibernate.dialect", env.getRequiredProperty( "hibernate.dialect" ) );
		jpaProperties.put( "hibernate.hbm2ddl.auto", env.getRequiredProperty( "hibernate.hbm2ddl.auto" ) );
		jpaProperties.put( "hibernate.show_sql", env.getRequiredProperty( "hibernate.show_sql" ) );
		jpaProperties.put( "hibernate.format_sql", env.getRequiredProperty( "hibernate.format_sql" ) );
		jpaProperties.put( "hibernate.enable_lazy_load_no_trans", env.getRequiredProperty( "hibernate.enable_lazy_load_no_trans" ) );

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

}
