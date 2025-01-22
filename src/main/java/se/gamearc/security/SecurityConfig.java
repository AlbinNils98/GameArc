package se.gamearc.security;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import se.gamearc.user.service.UserDetailsServiceImpl;

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
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/register", "/css/**", "/js/**", "/resources/**")
            .permitAll()
            .requestMatchers(GET, "/api/games/**")
            .permitAll()
            .anyRequest().authenticated())
        .formLogin(formLogin -> formLogin
            .loginPage("/login")
                .permitAll()
            .successHandler(authSuccessHandler)
            )
        .logout(logout -> logout
            .logoutSuccessUrl(frontendUrl))
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

}


