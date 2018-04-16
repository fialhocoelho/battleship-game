package br.fatecsp.engsw3.battleship.config;

import br.fatecsp.engsw3.battleship.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userService; 
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
        .authorizeRequests()
	        .antMatchers("/", "/welcome", "/index").permitAll() // páginas permitidas por todos
	        .antMatchers("/admin").hasRole("ADMIN")
	        .anyRequest().authenticated() // qualquer outra requisicao precisa estar autenticado?
	        
	        	// login configuration
	        	.and().formLogin()
		        .loginPage("/login")
		        .permitAll()
		        .defaultSuccessUrl("/index", true)
	
	        
	            	// logout configuration
	            	.and().logout()
	            	.logoutSuccessUrl("/index")
		    		.logoutUrl("/logout")
		    		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		        	
			        	//exception handling configuration
			        	.and().exceptionHandling() 
		        		.accessDeniedPage("/error")
        	;
			
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(userService)
			.passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers(
				"/style/**", 
				"resources/**", 
				"/static/**", 
				"/css/**", 
				"/js/**", 
				"/images/**"
		);
	}
	
}
