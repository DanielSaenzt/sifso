package com.fundacionoasis.repository;

import com.fundacionoasis.entity.BloodType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBloodTypeRepository extends JpaRepository<BloodType, Integer> {

}
