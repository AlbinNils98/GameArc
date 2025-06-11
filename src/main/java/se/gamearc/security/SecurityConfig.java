package se.gamearc.security;


import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import se.gamearc.user.service.UserDetailsServiceImpl;

import java.util.List;

import static org.springframework.http.HttpMethod.GET;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
  @Value("${frontend.url}")
  private String frontendUrl;

  UserDetailsServiceImpl userDetailsService;
  AuthSuccessHandlerImpl authSuccessHandler;

  public SecurityConfig(UserDetailsService userDetailsService, AuthSuccessHandlerImpl authSuccessHandler) {
    this.userDetailsService = (UserDetailsServiceImpl) userDetailsService;
    this.authSuccessHandler = authSuccessHandler;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
        .cors(Customizer.withDefaults())
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/register", "/css/**", "/js/**", "/resources/**")
            .permitAll()
            .requestMatchers(GET, "/api/games/**")
            .permitAll()
            .anyRequest().authenticated())
        .formLogin(formLogin -> formLogin
                .loginProcessingUrl("/login")
            .successHandler(authSuccessHandler)
                .failureHandler((request, response, exception) -> {
                  response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                  response.setContentType("application/json");
                  response.getWriter().write("{\"error\": \"Invalid username or password\"}");
                })
            )
        .logout(logout -> logout
            .logoutUrl("/logout")
            .logoutSuccessHandler((request, response, authentication) -> {
              response.setStatus(HttpStatus.OK.value());
            })
            .deleteCookies("JSESSIONID")
        )
        .exceptionHandling(exceptionHandle -> exceptionHandle
            .authenticationEntryPoint((request, response, authException) -> {
              response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            }))
        .build();
  }

  @Bean
  public AuthenticationManager authenticationManager(
      PasswordEncoder passwordEncoder
  ) throws Exception {
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(userDetailsService);
    authenticationProvider.setPasswordEncoder(passwordEncoder);
    return new ProviderManager(authenticationProvider);
  }


  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowedOrigins(List.of(frontendUrl));
    config.setAllowedMethods(List.of("GET", "POST", "PUT"));
    config.setAllowedHeaders(List.of("*"));
    config.setAllowCredentials(true);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", config);
    return source;
  }
}


