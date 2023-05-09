package com.fundacionoasis.repository;

import com.fundacionoasis.entity.Footer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface IFooterRepository extends JpaRepository<Footer,Long> {
}
