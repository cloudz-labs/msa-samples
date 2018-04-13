package io.dtlabs.discovery;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Profile("docker")
@EnableWebSecurity
@Order(1)
public class SecurityConfigration extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// page authentication, authority
		http.requestMatchers().antMatchers("/eureka/**")
			.and().authorizeRequests().antMatchers("/eureka/**").hasRole("ADMIN").anyRequest().denyAll()			
			.and().httpBasic()
			.and().csrf().disable();
	}
}
