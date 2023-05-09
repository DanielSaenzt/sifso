package com.fundacionoasis.service;

import com.fundacionoasis.entity.Beneficiary;
import com.fundacionoasis.entity.MedicalRecord;
import com.fundacionoasis.repository.IBeneficiaryRepository;
import com.fundacionoasis.repository.IMedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicalRecordService {

    @Autowired
    private IMedicalRecordRepository medicalRecordRepository;
    public List<MedicalRecord> findAll(){
        return medicalRecordRepository.findAll();
    }



    public MedicalRecord save(MedicalRecord medicalRecord){
        return medicalRecordRepository.save(medicalRecord);
    }

    public Optional<MedicalRecord> findById(Long id){
        return medicalRecordRepository.findById(id);
    }

    public void updateStatus(String status, Long id){
        medicalRecordRepository.updateStatus(status,id);
    }
}
