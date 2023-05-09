package com.fundacionoasis.repository;

import com.fundacionoasis.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface IAssigmentRepository extends JpaRepository<AssigmentBeneficiaryUser, Long> {

    @Modifying
    @Transactional
    @Query("update AssigmentBeneficiaryUser a set a.status=?1 where a.id=?2")
    void updateStatus(String status, Long id);

    @Query("select a from AssigmentBeneficiaryUser a where a.user = ?1 and a.workshop=?2 and a.beneficiary=?3")
    Optional<AssigmentBeneficiaryUser> findByUserAndWorkshopAndBeneficiary(User user,
                                                                           Workshop workshop,
                                                                           Beneficiary beneficiary);

}
