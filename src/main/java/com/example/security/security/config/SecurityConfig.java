package com.example.security.security.config;

import com.example.security.security.service.AtunticacaoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

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
    public AuthenticationProvider authenticationProvider(AtunticacaoService service){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(service);
        return  authenticationProvider;
    }

//    @Bean --> injetar a service
    public PasswordEncoder passwordEncoder (){
        return NoOpPasswordEncoder.getInstance();
    }
}
