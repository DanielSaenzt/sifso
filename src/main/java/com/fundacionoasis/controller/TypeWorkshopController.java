package com.fundacionoasis.controller;

import com.fundacionoasis.entity.TypeWorkshop;
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

@Controller
@RestController
@RequestMapping("/api/type")
public class TypeWorkshopController {

    @Autowired
    private TypeWorkshopService typeWorkshopService;

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
