package com.fundacionoasis.service;

import com.fundacionoasis.entity.GroupBeneficiary;
import com.fundacionoasis.repository.IGroupBeneficiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GroupBeneficiaryService {

    @Autowired
    private IGroupBeneficiaryRepository groupBeneficiaryRepository;

    public GroupBeneficiary save(GroupBeneficiary groupBeneficiary){
        return groupBeneficiaryRepository.save(groupBeneficiary);
    }

    public void deleteById(Integer id){groupBeneficiaryRepository.deleteById(id);
    }

    public Optional<GroupBeneficiary> findById(Integer id){
        return groupBeneficiaryRepository.findById(id);
    }

    public Optional<GroupBeneficiary> findByDescription(String description){
        return groupBeneficiaryRepository.findByDescription(description);
    }
}
