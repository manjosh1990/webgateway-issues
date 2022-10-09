package com.example.apigateway.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.csrf.CookieServerCsrfTokenRepository;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatcher;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}
	private static final Set<HttpMethod> ALLOWED_METHODS = new HashSet<>(
			Arrays.asList(HttpMethod.GET, HttpMethod.HEAD, HttpMethod.TRACE, HttpMethod.OPTIONS));
	@Bean
	public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
		http.authorizeExchange().anyExchange().permitAll().and()
				.csrf(csrf -> csrf
						.requireCsrfProtectionMatcher(ignoringFormUrlEncodedContentType())
						.csrfTokenRepository(CookieServerCsrfTokenRepository.withHttpOnlyFalse()));
		return http.build();
	}
	private ServerWebExchangeMatcher ignoringFormUrlEncodedContentType() {
		return (exchange) -> !MediaType.APPLICATION_FORM_URLENCODED.isCompatibleWith(
				exchange.getRequest().getHeaders().getContentType()) || ALLOWED_METHODS.contains(exchange.getRequest().getMethod())
				? ServerWebExchangeMatcher.MatchResult.match()
				: ServerWebExchangeMatcher.MatchResult.notMatch();
	}
}
