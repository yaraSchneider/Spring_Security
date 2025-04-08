package com.example.security.security.controller;

import com.example.security.security.Utils.JWTUtils;
import com.example.security.security.dto.Login;
import com.example.security.security.service.AtunticacaoService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AutenticationController {

    AuthenticationManager manager;
    SecurityContextRepository repository;
    private final JWTUtils jwtUtils = new JWTUtils();
    private AtunticacaoService service;

    @PostMapping("/login")
    public void login(@RequestBody Login login, HttpServletRequest request, HttpServletResponse response){
        Authentication auth = new UsernamePasswordAuthenticationToken(login.username(), login.password());
        auth = manager.authenticate(auth); // obrigatorio
        if (auth.isAuthenticated()){
//            Mantem sessão de usuario salvo no servidor
//            SecurityContext context = SecurityContextHolder.getContext();
//            context.setAuthentication(auth);
//            repository.saveContext(context, request, response);
//            uso do jwt
           String token = jwtUtils.gerarToken((UserDetails) auth.getPrincipal());
            response.setStatus(200);
            response.addCookie(new Cookie( "JWTSESION" , token));
        } else {
            response.setStatus(401);
        }
    }

    @PostMapping("/logauot")
    public void logout(HttpServletRequest request, HttpServletResponse response){
        repository.saveContext(SecurityContextHolder.createEmptyContext(), request, response);
    }


}
