package com.board.recipes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	 @Autowired
	    UserDetailsService userDetailsService;

	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            // AUTHORIZE
            .authorizeRequests()
            	.antMatchers("/registrationForm").permitAll()
            	.antMatchers("/register").permitAll()
            	.antMatchers("/result").permitAll()
            	.anyRequest().authenticated();

            // LOGIN
            http.formLogin()
            	.loginPage("/login")
            	.loginProcessingUrl("/authenticate")
            	.usernameParameter("name")
                .passwordParameter("password")
            	.defaultSuccessUrl("/index")
            	.permitAll()
        // end
        ;
    }

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	void configureAuthenticationManager(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
        .passwordEncoder(passwordEncoder());
}
}
