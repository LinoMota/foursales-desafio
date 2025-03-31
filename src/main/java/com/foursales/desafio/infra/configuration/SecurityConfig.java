package com.foursales.desafio.infra.configuration;

import com.foursales.desafio.infra.security.SecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private SecurityFilter securityFilter;

    @Autowired
    private UserDetailsService userDetailsService;


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll()

                        .requestMatchers(HttpMethod.POST, "/users").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/users/").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/users/{id}").hasAuthority("ADMIN")

                        .requestMatchers(HttpMethod.PUT, "/products/{id}").hasAuthority("ADMIN")

                        .requestMatchers(HttpMethod.POST, "/products").hasAuthority("ADMIN")

                        .requestMatchers(HttpMethod.GET, "/products/{id}").hasAnyAuthority("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/products").hasAnyAuthority("USER", "ADMIN")

                        .requestMatchers(HttpMethod.POST, "/orders").hasAnyAuthority("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/orders").hasAnyAuthority("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/orders/{id}").hasAnyAuthority("USER", "ADMIN")

                        .requestMatchers(HttpMethod.POST, "/pay-order/{id}").hasAnyAuthority("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/my-orders").hasAnyAuthority("USER", "ADMIN")

                        .requestMatchers(HttpMethod.GET, "/users-average-ticket").hasAnyAuthority("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/current-month-revenue").hasAnyAuthority("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/top-five-users").hasAnyAuthority("USER", "ADMIN")

                        .requestMatchers(HttpMethod.POST, "/search").hasAnyAuthority("USER", "ADMIN")


                        .anyRequest().authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
