package com.fundacionoasis.repository;

import com.fundacionoasis.entity.Role;
import com.fundacionoasis.entity.TypeWorkshop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ITypeWorkshopRepository extends JpaRepository<TypeWorkshop,Integer> {

    @Query("select t from TypeWorkshop t where t.description = ?1")
    Optional<TypeWorkshop> findByDescription(String description);
}
