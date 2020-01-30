package com.ishanitech.ipalika.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ishanitech.ipalika.security.CustomAuthenticationEntryPoint;
import com.ishanitech.ipalika.security.CustomAuthenticationProvider;
import com.ishanitech.ipalika.security.TokenAuthenticationFilter;
import com.ishanitech.ipalika.security.TokenAuthorizationFilter;
import com.ishanitech.ipalika.utils.JsonTokenHelper;
/**
 * Custom Security configuration class. 
 * Any customization (except auth providers) related to security has to be done 
 * in this class. 
 * @author <b> Umesh Bhujel
 * @since 1.0
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private CustomAuthenticationProvider authenticationProvider;
	private CustomAuthenticationEntryPoint authenticationEntryPoint;
	private ObjectMapper objectMapper;
	private JsonTokenHelper tokenHelper;
	
	public SecurityConfig(CustomAuthenticationProvider authenticationProvider,
			CustomAuthenticationEntryPoint authenticationEntryPoint,
			ObjectMapper objectMapper,
			JsonTokenHelper tokenHelper) {
		this.authenticationProvider = authenticationProvider;
		this.authenticationEntryPoint = authenticationEntryPoint;
		this.objectMapper = objectMapper;
		this.tokenHelper = tokenHelper;
	}

	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.cors()
			.and()
			.csrf().disable()
			.httpBasic().disable()
			.exceptionHandling()
			.authenticationEntryPoint(authenticationEntryPoint)
			.and()
			.authorizeRequests()
			.antMatchers(HttpMethod.GET, "/form-detail/**").permitAll()
			.antMatchers("/resource/**", "/home", "/resident/**", "/form/**", "/question-option/**", "/survey-answer/**", "/admin/**","/favourite-place/**").permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.addFilter(corsConfiguration())
			.addFilter(new TokenAuthenticationFilter(authenticationManager(), objectMapper, tokenHelper))
			.addFilterAfter(new TokenAuthorizationFilter(tokenHelper, objectMapper), TokenAuthenticationFilter.class)
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.enableSessionUrlRewriting(false);
	}
	
	/**
	 * @return CorsFilter object. Configuration to allow cors request..
	 */
	
	@Bean
	CorsFilter corsConfiguration() {
		UrlBasedCorsConfigurationSource urlBasedCors = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfig = new CorsConfiguration();
		corsConfig.setAllowCredentials(true);
		corsConfig.addAllowedOrigin("*");
		corsConfig.addAllowedHeader("*");
		corsConfig.addAllowedMethod("OPTIONS");
		corsConfig.addAllowedMethod("GET");
		corsConfig.addAllowedMethod("POST");
		corsConfig.addAllowedMethod("PUT");
		corsConfig.addAllowedMethod("DELETE");
		urlBasedCors.registerCorsConfiguration("/**", corsConfig);
		return new CorsFilter(urlBasedCors);
	}
}

