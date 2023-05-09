package com.fundacionoasis.repository;

import com.fundacionoasis.entity.Donaciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface IDonacionesRepository extends JpaRepository<Donaciones,Long> {
}
