package com.fundacionoasis.repository;

import com.fundacionoasis.entity.Beneficiary;
import com.fundacionoasis.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface IBeneficiaryRepository extends JpaRepository<Beneficiary,Long> {

    @Modifying
    @Transactional
    @Query("update Beneficiary b set b.status=?1 where b.id=?2")
    void updateStatus(Boolean status, Long id);

    @Query("select b from Beneficiary b where b.dni= ?1")
    Optional<Beneficiary> findByDni(String email);
}
