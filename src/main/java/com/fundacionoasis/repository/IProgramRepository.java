package com.fundacionoasis.repository;

import com.fundacionoasis.entity.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface IProgramRepository extends JpaRepository<Program,Long> {
}
