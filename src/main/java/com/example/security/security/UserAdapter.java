//package com.example.security.security;
//
//import com.example.security.model.Usuario;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//
//@AllArgsConstructor
//@Data
//public class UserAdapter implements UserDetails {
//
//    private Usuario usuario;
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return null;
//    }
//
//    @Override
//    public String getPassword() {
//        return this.usuario.getSenha();
//    }
//
//    @Override
//    public String getUsername() {
//        return this.usuario.getUsuario();
//    }
//}
