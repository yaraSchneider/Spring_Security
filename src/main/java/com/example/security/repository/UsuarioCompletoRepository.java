package com.example.security.repository;

import com.example.security.model.entity.UsuarioCompleto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioCompletoRepository extends JpaRepository<UsuarioCompleto, Long> {

    Optional<UsuarioCompleto> findByUsername(String username);


}
