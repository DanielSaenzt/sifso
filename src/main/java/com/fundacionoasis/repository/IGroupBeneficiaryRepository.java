package com.fundacionoasis.repository;

import com.fundacionoasis.entity.GroupBeneficiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IGroupBeneficiaryRepository extends JpaRepository<GroupBeneficiary, Integer> {
    @Query("select g from GroupBeneficiary g where g.description = ?1")
    Optional<GroupBeneficiary> findByDescription(String description);
}
