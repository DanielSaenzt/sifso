package com.fundacionoasis.repository;

import com.fundacionoasis.entity.SexType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISexTypeRepository extends JpaRepository<SexType, Integer> {
}
