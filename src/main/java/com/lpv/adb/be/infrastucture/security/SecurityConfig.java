package com.lpv.adb.be.infrastucture.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;

/**
 * @author selimssevgi
 */
@EnableWebSecurity
@Configuration
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring()
        .antMatchers("/v2/api-docs/**")
        .antMatchers("/swagger.json")
        .antMatchers("/configuration/ui")
        .antMatchers("/swagger-resources/**")
        .antMatchers("/configuration/security")
        .antMatchers("/swagger-ui.html", "/webjars/**");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf()
        .disable()
        .headers()
        .frameOptions()
        .disable()
        .and()
        .cors().configurationSource(request -> new CorsConfiguration().combine(corsConfig()))
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests()
        .antMatchers("/non-secured/**").permitAll()
        .anyRequest().authenticated();
  }

  /**
   * Definition as a bean did not work.
   * options request, preflight always fails.
   */
  private CorsConfiguration corsConfig() {
    final CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(true);
    config.addAllowedOrigin("*");
    config.addAllowedHeader("*");
    config.addAllowedMethod(HttpMethod.GET);
    config.addAllowedMethod(HttpMethod.POST);
    config.addAllowedMethod(HttpMethod.PUT);
    config.addAllowedMethod(HttpMethod.DELETE);
    config.addAllowedMethod(HttpMethod.OPTIONS);
    config.addAllowedMethod(HttpMethod.HEAD);
    config.addAllowedMethod(HttpMethod.PATCH);
    return config;
  }
}
