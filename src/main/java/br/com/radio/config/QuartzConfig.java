package br.com.radio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
@ComponentScan("br.radio.com")
public class QuartzConfig {

	@Bean
	public SchedulerFactoryBean schedulerFactoryBean() {
		
		SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
		
		return scheduler;
	}
	
}
