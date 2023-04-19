package com.fundacionoasis.controller;

import com.fundacionoasis.entity.Role;
import com.fundacionoasis.exception.ConflictException;
import com.fundacionoasis.exception.ErrorException;
import com.fundacionoasis.service.RoleService;
import com.fundacionoasis.validator.RolValidator;
import com.fundacionoasis.validator.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/")
    public ResponseEntity<?> save(@RequestBody Role rol) throws Exception {
        RolValidator.validationAttribute(rol);
        Role roleValidated = RolValidator.trimAttributes(rol);

        if(roleService.findByDescription(roleValidated.getDescription()).isPresent()){
            throw new ConflictException("already exist a rol with the same description");
        }

        Validation.validationStringSize(roleValidated.getDescription(), 30, "The rol to save is to large" );

        if(roleService.save(roleValidated) == null){
            throw new ErrorException("The rol could not be save");
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id){
        roleService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
