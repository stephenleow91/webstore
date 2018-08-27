package com.packt.webstore.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	@Override
	public UserDetailsService userDetailsService() {

		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

		final User.UserBuilder userBuilder = User.builder().passwordEncoder(encoder::encode);
		UserDetails user = userBuilder.username("tungwoey").password("1234").roles("USER").build();

		UserDetails admin = userBuilder.username("stephen").password("1234").roles("USER", "ADMIN").build();

		return new InMemoryUserDetailsManager(user, admin);
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.formLogin().loginPage("/login").usernameParameter("userId").passwordParameter("password");

		httpSecurity.formLogin().defaultSuccessUrl("/market/products/add").failureUrl("/login?error");

		httpSecurity.logout().logoutSuccessUrl("/login?logout");

		httpSecurity.exceptionHandling().accessDeniedPage("/login?accessDenied");

		httpSecurity.authorizeRequests() //
				.antMatchers("/").permitAll() //
				.antMatchers("/**/add").access("hasRole('ADMIN')") //
				.antMatchers("/**/market/**").access("hasRole('USER')");

		httpSecurity.csrf().disable();

	}

}
