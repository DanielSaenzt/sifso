package com.fundacionoasis.service;

import com.fundacionoasis.entity.AssigmentBeneficiaryUser;
import com.fundacionoasis.entity.Beneficiary;
import com.fundacionoasis.entity.User;
import com.fundacionoasis.entity.Workshop;
import com.fundacionoasis.repository.IAssigmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AssigmentService {
    @Autowired
    private IAssigmentRepository assigmentRepository;

    public AssigmentBeneficiaryUser save(AssigmentBeneficiaryUser assigmentBeneficiaryUser){
        return assigmentRepository.save(assigmentBeneficiaryUser);
    }

    public Optional<AssigmentBeneficiaryUser> findById(Long id){
        return assigmentRepository.findById(id);
    }

    public void updateStatus(String status, Long id){
        assigmentRepository.updateStatus(status,id);
    }

    public Optional<AssigmentBeneficiaryUser> findByUserAndWorkshopAndBeneficiary(User user,
                                                                                  Workshop workshop,
                                                                                  Beneficiary beneficiary){
        return assigmentRepository.findByUserAndWorkshopAndBeneficiary(user, workshop, beneficiary);

    }
}
