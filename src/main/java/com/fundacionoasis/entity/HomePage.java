package com.fundacionoasis.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "homepage")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HomePage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "contenido")
    private String contenido;

    @Column(name = "titulo_secundario")
    private String tituloSecundario;

    // Getters y setters
}
