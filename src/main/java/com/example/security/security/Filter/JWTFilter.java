package com.example.security.security.Filter;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.security.security.Utils.CookieUtils;
import com.example.security.security.Utils.JWTUtils;
import com.example.security.security.model.Exeption.CookieNotFoundException;
import com.example.security.security.service.AtunticacaoService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTFilter extends OncePerRequestFilter {

    private CookieUtils cookieUtils = new CookieUtils();
    private final JWTUtils jwtUtils = new JWTUtils();

    private AtunticacaoService service;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       try {
           Cookie cookie = cookieUtils.getJWTCookie(request);
           String token = cookie.getValue();
           jwtUtils.validarToken(token);
           String username = jwtUtils.getUsername(token);
           UserDetails user = service.loadUserByUsername(username);
           Authentication auth = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
           SecurityContextHolder.getContext().setAuthentication(auth);

       } catch (CookieNotFoundException cookieException){
           filterChain.doFilter(request, response);

       }catch (SignatureVerificationException | TokenExpiredException jwtE){
            response.setStatus(403);
           return;
       }

       filterChain.doFilter(request, response);
    }
}
