package com.simonamilosheska.configurations;

import com.simonamilosheska.handlers.SecurityExceptionHandler;
import com.simonamilosheska.securityfilters.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.simonamilosheska.constants.SecurityConstants.JWT_COOKIE_NAME;

@Component
public class SecurityFilterChainConfig {

  private final JwtAuthenticationFilter jwtAuthenticationFilter;

  public SecurityFilterChainConfig(
    JwtAuthenticationFilter jwtAuthenticationFilter
  ) {
    this.jwtAuthenticationFilter = jwtAuthenticationFilter;
  }

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
      .csrf()
      .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
      .requireCsrfProtectionMatcher(new CustomRequestMatcher())
      .and()
      .authorizeRequests()
      .antMatchers("/librarian").hasRole("ADMIN")
      .antMatchers("/login", "/user", "/admin")
      .permitAll()
      .anyRequest()
      .authenticated()
      .and()
      .sessionManagement()
      .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      .and()
      .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
      .exceptionHandling()
      .accessDeniedHandler(new SecurityExceptionHandler())
      .authenticationEntryPoint(new SecurityExceptionHandler());
    return http.build();
  }

  private static class CustomRequestMatcher implements RequestMatcher {

    @Override
    public boolean matches(HttpServletRequest request) {
      return
        (request.getMethod().equalsIgnoreCase("POST")
         && SecurityContextHolder.getContext().getAuthentication() != null);
    }
  }
}
