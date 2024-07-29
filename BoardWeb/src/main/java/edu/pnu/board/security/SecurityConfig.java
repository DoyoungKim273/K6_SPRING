package edu.pnu.board.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	SecurityFilterChain securityfilterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests(auth -> auth.requestMatchers("/system/**").authenticated()
				.requestMatchers("/board/**").authenticated()
				.requestMatchers("/admin/**").hasRole("ADMIN").anyRequest().permitAll());
		
		http.csrf(csrf -> csrf.disable()); 
		http.formLogin(frmLogin -> frmLogin
				.loginPage("/system/login")
				.defaultSuccessUrl("/board/getBoardList", true));
        http.exceptionHandling(handling -> handling.accessDeniedPage("/system/accessDenied"));
        http.logout(logout -> logout.logoutUrl("/system/logout")
                .invalidateHttpSession(true).logoutSuccessUrl("/"));		
		return http.build();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
