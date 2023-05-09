package com.fundacionoasis.controller;

import com.fundacionoasis.entity.Workshop;
import com.fundacionoasis.exception.BadRequestCustom;
import com.fundacionoasis.exception.ConflictException;
import com.fundacionoasis.exception.ErrorException;
import com.fundacionoasis.service.WorkshopService;
import com.fundacionoasis.validator.Validation;
import com.fundacionoasis.validator.WorkShopValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RestController
@RequestMapping("/api/workshop")
@CrossOrigin("*")

public class WorkshopController {
    @Autowired
    private WorkshopService workshopService;

    @GetMapping("/listar")
    public ResponseEntity<?> listar(){
        List<Workshop> workshops = workshopService.findAll();
        return new ResponseEntity<>(workshops, HttpStatus.OK);
    }
    @PostMapping("/")
    public ResponseEntity<?> save(@RequestBody Workshop workshop) throws Exception {
        try{
        WorkShopValidator.validationAttribute(workshop);
        Workshop workshopValidated = WorkShopValidator.trimAttributes(workshop);

        Validation.validationStringSize(workshopValidated.getTitle(), 120, "El título del taller es demasiado largo.");
        Validation.validationStringSize(workshopValidated.getDescription(), 120, "La descripción del taller es demasiado largo." );
        Validation.validationStringSize(workshopValidated.getTypeBeneficiary(), 40, "El tipo beneficiario al que va dirigido el taller esta demasiado largo.");
        Validation.validationStringSize(workshopValidated.getColor(), 10, "El color del taller es demasiado largo");
        Validation.validationStringSize(workshopValidated.getStatus(), 10, "El estado del taller es demasiado largo");


            if(workshopService.save(workshopValidated) == null){
                throw new ErrorException("The user could not be save");
            }
        }catch (BadRequestCustom badRequestCustom){
               return  new ResponseEntity<>(badRequestCustom.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Workshop> update(@RequestBody Workshop workshop) throws Exception {
        WorkShopValidator.validationAttribute(workshop);
        Workshop workshopValidated = WorkShopValidator.trimAttributes(workshop);

        if(workshopService.findById(workshop.getId()).isEmpty()){
            throw new ConflictException("El taller que intenta actualizar no existe.");
        }

        Validation.validationStringSize(workshopValidated.getTitle(), 120, "El título del taller es demasiado largo.");
        Validation.validationStringSize(workshopValidated.getDescription(), 120, "La descripción del taller es demasiado largo." );
        Validation.validationStringSize(workshopValidated.getTypeBeneficiary(), 40, "El tipo beneficiario al que va dirigido el taller esta demasiado largo.");
        Validation.validationStringSize(workshopValidated.getColor(), 10, "El color del taller es demasiado largo");
        Validation.validationStringSize(workshopValidated.getStatus(), 10, "El estado del taller es demasiado largo");

        Workshop response = workshopService.save(workshopValidated);
        if(response == null){
            throw new ErrorException("The workshop could not be save");
        }
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @PatchMapping("/status")
    public ResponseEntity<?> patch(@RequestParam("id") Long id, @RequestParam("status") String status) throws Exception {

        if(workshopService.findById(id).isEmpty()){
            throw new ConflictException("The workshop to update does not exist");
        }

        if(status == null){
            throw new BadRequestCustom("The status can not be empty");
        }
        workshopService.updateStatus(status,id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
