package br.com.zupacademy.jpcsik.proposta.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/actuator/**").permitAll()
			.antMatchers("/h2-console/**").permitAll()
			.anyRequest().hasAuthority("SCOPE_proposta")
			.and()
			.csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.oauth2ResourceServer().jwt();
			
	}
	
	 @Override
	 public void configure(WebSecurity web) throws Exception {
	        web
	        .ignoring()
	        .antMatchers("/h2-console/**");
	    }
	 
}
