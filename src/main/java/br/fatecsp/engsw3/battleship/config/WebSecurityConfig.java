package br.fatecsp.engsw3.battleship.config;

import br.fatecsp.engsw3.battleship.security.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
        .authorizeRequests()
	        .antMatchers("/", "/index").permitAll() // páginas permitidas por todos
	        .antMatchers("/admin").hasAuthority("ADMIN") // somente administradores podem acessar
	        .anyRequest().authenticated() // qualquer outra requisicao precisa estar autenticado

		// login configuration
		.and()
		.formLogin()
			.loginPage("/login")
			.permitAll()
			.defaultSuccessUrl("/welcome", true)

		// logout configuration
		.and()
		.logout()
			.logoutSuccessUrl("/")
			.logoutUrl("/logout")
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))

        // remember me
        .and()
        .rememberMe()
            .userDetailsService(userService)

		//exception handling configuration
		.and()
		.exceptionHandling()
			.accessDeniedPage("/error")
        ;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(userService)
			.passwordEncoder(new BCryptPasswordEncoder());
	}
	
}
