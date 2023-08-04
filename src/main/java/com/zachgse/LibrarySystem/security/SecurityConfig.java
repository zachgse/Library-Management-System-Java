package com.zachgse.LibrarySystem.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
   @Bean
   public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
       return authenticationConfiguration.getAuthenticationManager();
   }
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        .authorizeRequests((authorizeRequests) ->
                authorizeRequests
				.requestMatchers("/admin").hasAnyRole("ADMIN")
				.requestMatchers("/books/*").hasAnyRole("ADMIN","USER")
				.requestMatchers("/").permitAll()
        )
        .formLogin((formLogin) ->
                {
					try {
						formLogin
						        .usernameParameter("username")
						        .passwordParameter("password")
						        .loginPage("/login")
						        .failureUrl("/authentication/login?failed")
						        .loginProcessingUrl("/authentication/login/process");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
        );
        return http.build();
	}
	
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
	

}
