package com.fundacionoasis.service;

import com.fundacionoasis.entity.AttendanceReport;
import com.fundacionoasis.entity.TypeWorkshop;
import com.fundacionoasis.repository.ITypeWorkshopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeWorkshopService {

    @Autowired
    private ITypeWorkshopRepository typeWorkshopRepository;

    public List<TypeWorkshop> findAll(){
        return typeWorkshopRepository.findAll();
    }
    public TypeWorkshop save(TypeWorkshop typeWorkshop){
        return typeWorkshopRepository.save(typeWorkshop);
    }

    public void deleteById(Integer id){
        typeWorkshopRepository.deleteById(id);
    }

    public Optional<TypeWorkshop> findById(Integer id){
        return typeWorkshopRepository.findById(id);
    }

    public Optional<TypeWorkshop> findByDescription(String description){
        return typeWorkshopRepository.findByDescription(description);
    }
}
