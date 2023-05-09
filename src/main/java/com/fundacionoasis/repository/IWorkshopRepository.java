package com.fundacionoasis.repository;

import com.fundacionoasis.entity.Workshop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface IWorkshopRepository extends JpaRepository<Workshop, Long> {

    @Modifying
    @Transactional
    @Query("update Workshop w set w.status=?1 where w.id=?2")
    void updateStatus(String status, Long id);
}
