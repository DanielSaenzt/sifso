package com.fundacionoasis.repository;

import com.fundacionoasis.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Role,Integer> {
    @Query("select r from Role r where r.description = ?1")
    Optional<Role> findByDescription(String description);
}
