package com.fundacionoasis.service;

import com.fundacionoasis.entity.DocumentType;
import com.fundacionoasis.entity.SexType;
import com.fundacionoasis.repository.ISexTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SexTypeService {
    @Autowired
    private ISexTypeRepository iSexTypeRepository;

    public List<SexType> findAll(){
        return iSexTypeRepository.findAll();
    }

    public SexType save(SexType sexType ){
        return iSexTypeRepository.save(sexType);
    }

    public void deleteById(Integer id){
        iSexTypeRepository.deleteById(id);
    }


    public Optional<SexType> findById(Integer id){
        return iSexTypeRepository.findById(id);
    }
}
