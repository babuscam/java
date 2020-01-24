package com.proiect.proiect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@SpringBootApplication
public class ProiectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProiectApplication.class, args);
	}

	@Configuration
	@Order(SecurityProperties.BASIC_AUTH_ORDER)
	protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
		@Autowired
		private DataSource datasource;

		@Autowired
		public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
			authenticationManagerBuilder.jdbcAuthentication().dataSource(datasource)
					.passwordEncoder(new BCryptPasswordEncoder())
					.usersByUsernameQuery("select email as username,password,1 from user where email=?")
					.authoritiesByUsernameQuery("select username,authority from role where username=?");
		}
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
					.authorizeRequests()
					.antMatchers("/team*").hasRole("ADMIN")
					.antMatchers("/home*").hasRole("USER")
					.antMatchers("/","/register").permitAll()
					.and()
					.httpBasic();
		}
	}
}
