package com.fundacionoasis.controller;

import com.fundacionoasis.entity.Planning;
import com.fundacionoasis.entity.Workshop;
import com.fundacionoasis.exception.BadRequestCustom;
import com.fundacionoasis.exception.ConflictException;
import com.fundacionoasis.exception.ErrorException;
import com.fundacionoasis.service.WorkshopService;
import com.fundacionoasis.validator.PlanningValidator;
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
@RequestMapping("/api/worshop")
public class WorkshopController {
    @Autowired
    private WorkshopService workshopService;

    @PostMapping("/")
    public ResponseEntity<?> save(@RequestBody Workshop workshop) throws Exception {
        WorkShopValidator.validationAttribute(workshop);
        Workshop workshopValidated = WorkShopValidator.trimAttributes(workshop);

        if(workshopService.findById(workshop.getId()).isPresent()){
            throw new ConflictException("already exist a workshop");
        }

        Validation.validationStringSize(workshopValidated.getDescription(), 120, "The workshop's description is to large" );
        Validation.validationStringSize(workshopValidated.getTypeBeneficiary(), 20, "The type of beneficiary is to large");


        if(workshopService.save(workshopValidated) == null){
            throw new ErrorException("The user could not be save");
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Workshop> update(@RequestBody Workshop workshop) throws Exception {
        WorkShopValidator.validationAttribute(workshop);
        Workshop workshopValidated = WorkShopValidator.trimAttributes(workshop);
        List<Planning> planningsValidated = new ArrayList<>();
        for (Planning planning: workshopValidated.getPlannings()) {
            PlanningValidator.validationAttribute(planning);
            planningsValidated.add(PlanningValidator.trimAttributes(planning));
        }
        workshopValidated.setPlannings(planningsValidated);

        if(workshopService.findById(workshop.getId()).isEmpty()){
            throw new ConflictException("the workshop to edit not exist");
        }

        Validation.validationStringSize(workshopValidated.getDescription(), 120, "The workshop's description is to large" );
        Validation.validationStringSize(workshopValidated.getTypeBeneficiary(), 20, "The type of beneficiary is to large");

        Workshop response = workshopService.save(workshopValidated);
        if(response == null){
            throw new ErrorException("The workshop could not be save");
        }
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @PatchMapping("/status")
    public ResponseEntity<?> patch(@RequestParam("id") Long id, @RequestParam("status") Boolean status) throws Exception {

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
