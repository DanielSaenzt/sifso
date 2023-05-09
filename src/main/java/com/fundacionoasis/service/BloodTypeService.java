package com.fundacionoasis.service;

import com.fundacionoasis.entity.BloodType;
import com.fundacionoasis.entity.Role;
import com.fundacionoasis.repository.IBloodTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BloodTypeService {
    @Autowired
    private IBloodTypeRepository iBloodTypeRepository;

    public List<BloodType> findAll(){
        return iBloodTypeRepository.findAll();
    }

    public BloodType save(BloodType bloodType){
        return iBloodTypeRepository.save(bloodType);
    }

    public void deleteById(Integer id){
        iBloodTypeRepository.deleteById(id);
    }


    public Optional<BloodType> findById(Integer id){
        return iBloodTypeRepository.findById(id);
    }
}
