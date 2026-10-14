package com.finance.tracker;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.*;
@Configuration public class SecurityConfig {
 @Value("${app.origin}") String origin;
 @Bean SecurityFilterChain security(HttpSecurity h)throws Exception{
  h.csrf(c->c.disable()).cors(c->c.configurationSource(cors())).authorizeHttpRequests(a->a.requestMatchers("/api/auth/register","/api/auth/login").permitAll().anyRequest().authenticated()).formLogin(f->f.disable()).httpBasic(b->b.disable()); return h.build();
 }
 @Bean CorsConfigurationSource cors(){CorsConfiguration c=new CorsConfiguration();c.setAllowedOrigins(List.of(origin));c.setAllowedMethods(List.of("GET","POST","PUT","DELETE","OPTIONS"));c.setAllowedHeaders(List.of("Content-Type","Accept"));c.setAllowCredentials(true);UrlBasedCorsConfigurationSource s=new UrlBasedCorsConfigurationSource();s.registerCorsConfiguration("/**",c);return s;}
 @Bean PasswordEncoder encoder(){return new BCryptPasswordEncoder();}
}
