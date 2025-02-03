package com.web.drainInfo.config;

import java.util.Collections;
import java.util.List;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;



@EnableWebSecurity
@EnableMethodSecurity
@Configuration

public class SecurityConfig {
	 

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.csrf(CsrfConfigurer::disable)
	
       
		.authorizeHttpRequests(authorize ->
        authorize
        
                .requestMatchers("/mypage/**").authenticated()
                .requestMatchers("/manager/**").hasAnyRole("ADMIN", "MANAGER")
                .requestMatchers("/admin/**").hasAnyRole("ADMIN")
                .anyRequest().permitAll()
				)
        	.formLogin(
                formLogin ->
                        formLogin
                                .loginPage("/login")
                                .loginProcessingUrl("/user/login") // login 주소가 호출되면 시큐리티가 낚아채서 대신 로그인 진행
                                .defaultSuccessUrl("/")
                                .usernameParameter("username") // 아이디 파라미터명 설정, default: username
                                .passwordParameter("password")
        	);
		  
//		http.logout(logout ->
//		logout.logoutSuccessUrl("/")
//              .deleteCookies("JSESSIONID")
//              .invalidateHttpSession(true)
//              .clearAuthentication(true));
	
       

         http
          .logout()
          .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
          .invalidateHttpSession(true)
          .deleteCookies("JSESSIONID")
          .permitAll();


		
		  return http.build();
}
	

}
	
	 	

