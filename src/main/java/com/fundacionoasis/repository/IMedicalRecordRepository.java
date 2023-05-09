package com.fundacionoasis.repository;

import com.fundacionoasis.entity.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface IMedicalRecordRepository extends JpaRepository<MedicalRecord,Long> {

    @Modifying
    @Transactional
    @Query("update MedicalRecord m set m.status=?1 where m.id=?2")
    void updateStatus(String status, Long id);
}
