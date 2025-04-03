package com.example.security.model;

import com.example.security.security.model.entidy.UsuarioAutentication;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String endereco;

    @OneToOne(cascade = CascadeType.PERSIST)
    private UsuarioAutentication usuarioAutentication;

}


