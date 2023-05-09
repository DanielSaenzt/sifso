package com.fundacionoasis.service;

import com.fundacionoasis.entity.Beneficiary;
import com.fundacionoasis.entity.BloodType;
import com.fundacionoasis.repository.IBeneficiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BeneficiaryService {

    @Autowired
    private IBeneficiaryRepository beneficiaryRepository;

    public List<Beneficiary> findAll(){
        return beneficiaryRepository.findAll();
    }

    public Beneficiary save(Beneficiary beneficiary){
        return beneficiaryRepository.save(beneficiary);
    }

    public Optional<Beneficiary> findById(Long id){
        return beneficiaryRepository.findById(id);
    }

    public Optional<Beneficiary> findByDni(String dni){
        return beneficiaryRepository.findByDni(dni);
    }

    public void updateStatus(String status, Long id){
        beneficiaryRepository.updateStatus(status,id);
    }
}
