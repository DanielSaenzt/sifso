package com.fundacionoasis.service;

import com.fundacionoasis.entity.User;
import com.fundacionoasis.entity.Workshop;
import com.fundacionoasis.repository.IUserRepository;
import com.fundacionoasis.repository.IWorkshopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WorkshopService {

    @Autowired
    private IWorkshopRepository workshopRepository;

    public Workshop save(Workshop workshop){
        return workshopRepository.save(workshop);
    }

    public Optional<Workshop> findById(Long id){
        return workshopRepository.findById(id);
    }

    public void updateStatus(Boolean status, Long id){
        workshopRepository.updateStatus(status,id);
    }
}
