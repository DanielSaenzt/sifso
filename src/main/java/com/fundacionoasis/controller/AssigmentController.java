package com.fundacionoasis.controller;

import com.fundacionoasis.entity.AssigmentBeneficiaryUser;
import com.fundacionoasis.entity.Beneficiary;
import com.fundacionoasis.exception.BadRequestCustom;
import com.fundacionoasis.exception.ConflictException;
import com.fundacionoasis.exception.ErrorException;
import com.fundacionoasis.service.*;
import com.fundacionoasis.validator.AssigmentValidator;
import com.fundacionoasis.validator.BeneficiaryValidator;
import com.fundacionoasis.validator.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/api/assigment")
public class AssigmentController {


    @Autowired
    private UserService userService;
    @Autowired
    private WorkshopService workshopService;
    @Autowired
    private BeneficiaryService beneficiaryService;
    @Autowired
    private AssigmentService assigmentService;

    @PostMapping("/")
    public ResponseEntity<?> save(@RequestBody AssigmentBeneficiaryUser assigmentBeneficiaryUser) throws Exception {
        AssigmentValidator.validationAttribute(assigmentBeneficiaryUser);

        if(assigmentService.findByUserAndWorkshopAndBeneficiary(assigmentBeneficiaryUser.getUser(),assigmentBeneficiaryUser.getWorkshop(),assigmentBeneficiaryUser.getBeneficiary()).isPresent()){
            throw new ConflictException("already exist a assigment");
        }

        if(assigmentService.save(assigmentBeneficiaryUser) == null){
            throw new ErrorException("The user could not be save");
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<AssigmentBeneficiaryUser> update(@RequestBody AssigmentBeneficiaryUser assigmentBeneficiaryUser) throws Exception {
        AssigmentValidator.validationAttribute(assigmentBeneficiaryUser);

        if(assigmentBeneficiaryUser.getId() == null){
            throw new BadRequestCustom("The assigment does not exist");
        }

        if(assigmentService.findByUserAndWorkshopAndBeneficiary(assigmentBeneficiaryUser.getUser(),assigmentBeneficiaryUser.getWorkshop(),assigmentBeneficiaryUser.getBeneficiary()).isPresent()){
            throw new ConflictException("already exist a assigment");
        }

        AssigmentBeneficiaryUser response = assigmentService.save(assigmentBeneficiaryUser);
        if(response == null){
            throw new ErrorException("The user could not be save");
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PatchMapping("/status")
    public ResponseEntity<?> patch(@RequestParam("id") Long id, @RequestParam("status") Boolean status) throws Exception {

        if(assigmentService.findById(id).isEmpty()){
            throw new BadRequestCustom("The assigment to update does not exist");
        }
        if(status == null){
            throw new BadRequestCustom("The status is required");
        }

        assigmentService.updateStatus(status,id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
