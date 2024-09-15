package com.example.userservice.security;

import jakarta.servlet.http.HttpServletRequest;
import java.util.function.Supplier;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurity {

    public static final String MY_IP_ADDRESS = "192.168.0.13";

    // Authorization
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(authz -> authz
                    .requestMatchers("/**").access(this::hasIpAddress)
//                    .requestMatchers(new AntPathRequestMatcher("/user-service/**")).permitAll()
//                    .requestMatchers(new AntPathRequestMatcher("/users/**")).permitAll()
//                    .requestMatchers(new AntPathRequestMatcher("/**")).permitAll()
            )
            .addFilter(getAuthenticationFilter())
            .headers(headers -> headers.frameOptions(FrameOptionsConfig::disable)); // To access H2 Console

        return http.build();
    }

    private AuthenticationFilter getAuthenticationFilter() throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter();
        authenticationFilter.setAuthenticationManager(authenticationManager -> authenticationManager);
        return authenticationFilter;
    }

    private AuthorizationDecision hasIpAddress(Supplier<Authentication> authentication, RequestAuthorizationContext context) {
        HttpServletRequest request = context.getRequest();
        String clientIp = request.getRemoteAddr();
        return new AuthorizationDecision(MY_IP_ADDRESS.equals(clientIp));
    }
}
