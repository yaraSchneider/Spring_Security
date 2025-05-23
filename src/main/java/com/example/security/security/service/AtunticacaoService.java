package com.example.security.security.service;

import com.example.security.model.Usuario;
import com.example.security.model.entity.UsuarioCompleto;
import com.example.security.repository.UsuarioCompletoRepository;
import com.example.security.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AtunticacaoService implements UserDetailsService{

    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsuarioAutentication_Username(username).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
        return usuario.getUsuarioAutentication();
    }
}
