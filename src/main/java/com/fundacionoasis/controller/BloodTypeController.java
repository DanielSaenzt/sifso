package com.fundacionoasis.controller;

import com.fundacionoasis.entity.BloodType;
import com.fundacionoasis.entity.Role;
import com.fundacionoasis.exception.ConflictException;
import com.fundacionoasis.exception.ErrorException;
import com.fundacionoasis.service.BloodTypeService;
import com.fundacionoasis.service.RoleService;
import com.fundacionoasis.validator.RolValidator;
import com.fundacionoasis.validator.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/blood")
@CrossOrigin("*")

public class BloodTypeController {

    @Autowired
    private BloodTypeService bloodTypeService;

    @GetMapping("/listar")
    public ResponseEntity<?> listar(){
        List<BloodType> bloodTypes = bloodTypeService.findAll();
        return new ResponseEntity<>(bloodTypes, HttpStatus.OK);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarById(@PathVariable Integer id){
        BloodType bloodType = bloodTypeService.findById(id).orElse(null);
        return new ResponseEntity<>(bloodType, HttpStatus.OK);
    }


}
