package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    // @Bean
    // public InMemoryUserDetailsManager userDetailsService(PasswordEncoder
    // passwordEncoder) {
    // UserDetails user = User.withUsername("user")
    // .password(passwordEncoder.encode("password"))
    // .roles("USER")
    // .build();

    // UserDetails admin = User.withUsername("admin")
    // .password(passwordEncoder.encode("admin"))
    // .roles("USER", "ADMIN")
    // .build();

    // return new InMemoryUserDetailsManager(user, admin);
    // }

    // @Bean
    // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    //     http.csrf().disable().authorizeRequests()
    //     .requestMatchers("/admin").permitAll()
    //     .anyRequest().authenticated().and()

    //             .httpBasic();
    //     return http.build();
    // }
    @Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/admin/zuhot/*","/admin","/admin/").permitAll()
				.anyRequest().authenticated()
			).formLogin((form) -> form
            .loginPage("/login")
            .permitAll()
        )
        .logout((logout) -> logout.permitAll());


		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user =
			 User.withDefaultPasswordEncoder()
				.username("user")
				.password("password")
				.roles("USER")
				.build();

		return new InMemoryUserDetailsManager(user);
	}
}
