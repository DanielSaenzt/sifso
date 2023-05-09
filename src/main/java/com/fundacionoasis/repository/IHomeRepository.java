package com.fundacionoasis.repository;

import com.fundacionoasis.entity.HomePage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface IHomeRepository extends JpaRepository<HomePage,Long> {
}
