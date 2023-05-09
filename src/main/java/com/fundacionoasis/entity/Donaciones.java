package com.fundacionoasis.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "donaciones")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Donaciones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "linknequi")
    private String linknequi;

    @Column(name = "linkdaviplata")
    private String linkdaviplata;

    @Column(name = "linkpaypal")
    private String linkpaypal;

    @Column(name = "descripcion_plan_padrino")
    private String descripcionPlanPadrino;

    @Column(name = "imagen_padrino")
    private String imagenPadrino;

    @Column(name = "descripcion_voluntario")
    private String descripcionVoluntario;

    @Column(name = "imagen_voluntario")
    private String imagenVoluntario;
}
