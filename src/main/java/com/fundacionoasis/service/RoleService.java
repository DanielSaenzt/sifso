package com.fundacionoasis.service;

import com.fundacionoasis.entity.Role;
import com.fundacionoasis.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private IRoleRepository roleRepository;

    public Role save(Role rol){
        return roleRepository.save(rol);
    }

    public void deleteById(Integer id){
        roleRepository.deleteById(id);
    }

    public Optional<Role> findByDescription(String description){
        return roleRepository.findByDescription(description);
    }

    public Optional<Role> findById(Integer id){
        return roleRepository.findById(id);
    }
}
