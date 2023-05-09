package com.fundacionoasis.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table (name = "footer")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Footer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "direccion", length = 100)
    private String direccion;

    @Column(name = "telefono", length = 100)
    private String telefono;

    @Column(name = "celular", length = 100)
    private String celular;

    @Column(name = "whatsapp", length = 100)
    private String whatsapp;

// constructor, getters, setters y otros métodos
}