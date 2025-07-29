package com.calygam.back.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	@Autowired
	private MySecurityFilter mySecurityFilter;
	//caio<- decidindo as rotas liberadas e as não liberadas
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception {
		return httpSecurity
				.csrf(csrf -> csrf.disable())
				.cors()
				.and()
				.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(authorize -> authorize
						.requestMatchers(HttpMethod.POST,"/auth/register","/auth/login").permitAll()
						.requestMatchers(HttpMethod.POST,"/progress/join/**").authenticated()
						.requestMatchers(HttpMethod.GET,"/auth/google").permitAll()
						.requestMatchers(HttpMethod.GET,"/auth/google/callback").permitAll()
						.requestMatchers(HttpMethod.PUT,"/users/teacher/**").hasRole("COORDENADOR")
						.requestMatchers(HttpMethod.POST,"/pet/admin/creating").hasRole("ADMIN")
						.requestMatchers(HttpMethod.PUT,"/pet/admin/creating/new/skin/{petId}").hasRole("ADMIN")
						.requestMatchers(HttpMethod.POST,"/emporium/add/item").hasRole("ADMIN")
						.requestMatchers(HttpMethod.POST,"/reward/create").hasRole("ADMIN")
						.requestMatchers(HttpMethod.POST,"/emporium/purchase/obtain/item/{emporiumItemId}/type/{emporiumItemCatalogType}").hasRole("ALUNO")
						.requestMatchers("/file/read/user/**").permitAll()
						.requestMatchers("/file/**").permitAll()
						.requestMatchers("/file/read/**").permitAll()
						.anyRequest().authenticated())
				.addFilterBefore(mySecurityFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	
	
}
