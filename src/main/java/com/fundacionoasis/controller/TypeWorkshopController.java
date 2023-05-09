package com.fundacionoasis.controller;

import com.fundacionoasis.entity.TypeWorkshop;
import com.fundacionoasis.entity.Workshop;
import com.fundacionoasis.exception.ConflictException;
import com.fundacionoasis.exception.ErrorException;
import com.fundacionoasis.service.TypeWorkshopService;
import com.fundacionoasis.validator.TypeWorkshopValidator;
import com.fundacionoasis.validator.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/type")
@CrossOrigin("*")

public class TypeWorkshopController {

    @Autowired
    private TypeWorkshopService typeWorkshopService;

    @GetMapping("/listar")
    public ResponseEntity<?> listar(){
        List<TypeWorkshop> typeWorkshops = typeWorkshopService.findAll();
        return new ResponseEntity<>(typeWorkshops, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> save(@RequestBody TypeWorkshop typeWorkshop) throws Exception {
        TypeWorkshopValidator.validationAttribute(typeWorkshop);
        TypeWorkshop typeWorkshopValidated = TypeWorkshopValidator.trimAttributes(typeWorkshop);

        if(typeWorkshopService.findByDescription(typeWorkshopValidated.getDescription()).isPresent()){
            throw new ConflictException("already exist a type workshop with the same description");
        }

        Validation.validationStringSize(typeWorkshopValidated.getDescription(), 1000, "The type workshop to save is to large" );

        if(typeWorkshopService.save(typeWorkshopValidated) == null){
            throw new ErrorException("The type workshop could not be save");
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id){
        typeWorkshopService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
