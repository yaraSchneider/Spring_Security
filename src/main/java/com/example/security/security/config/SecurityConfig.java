package com.example.security.security.config;

import com.example.security.security.service.AtunticacaoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

@Configuration
public class SecurityConfig {

//    @Bean
//    public UserDetailsService userDetailsService(){
//        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
//        inMemoryUserDetailsManager.createUser(User.builder().username("Yara").password("yara").build());
//        inMemoryUserDetailsManager.createUser(User.builder().username("Romário").password("Senha123").build());
//        return inMemoryUserDetailsManager;
//    }

    @Bean
    public AuthenticationManager authenticationManeger(AtunticacaoService service){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(service);
        return new ProviderManager(authenticationProvider);
    }

//    @Bean --> injetar a service
    public PasswordEncoder passwordEncoder (){
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity httpSecurity) throws Exception {
       httpSecurity.formLogin(config -> config.disable());
       httpSecurity.csrf(config -> config.disable());
       httpSecurity.authorizeHttpRequests(config -> {
           config.requestMatchers(HttpMethod.POST, "api/auth/login", "api/auth/logauot").authenticated();
       });
       return httpSecurity.build();
    }

    @Bean
    public SecurityContextRepository contextRepository(){
        return new HttpSessionSecurityContextRepository();
    }
}
