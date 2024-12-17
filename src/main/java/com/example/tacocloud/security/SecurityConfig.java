package com.example.tacocloud.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new StandardPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(httpRequests ->
                httpRequests
                        .requestMatchers("/design", "/orders")
                        .hasAuthority("ROLE_USER")
                        .requestMatchers("/", "/**")
                        .permitAll()
        );
        http.formLogin(form ->
                form
                        .loginPage("/login")
                        .defaultSuccessUrl("/design")
        );
        http.logout(logout ->
                logout
                        .logoutSuccessUrl("/")
        );

        http.csrf(csrf ->
                csrf
                        .ignoringRequestMatchers("/h2-console/**")
        );
        http.headers(headers ->
                headers
                        .frameOptions(frameOptions ->
                                frameOptions
                                        .sameOrigin()
                        ));
        return http.build();
    }
}
