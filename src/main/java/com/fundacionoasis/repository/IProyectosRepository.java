package com.fundacionoasis.repository;

import com.fundacionoasis.entity.Proyectos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProyectosRepository extends JpaRepository<Proyectos, Long> {
}
