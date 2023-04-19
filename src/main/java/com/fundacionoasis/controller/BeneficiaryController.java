package com.fundacionoasis.controller;

import com.fundacionoasis.entity.Beneficiary;
import com.fundacionoasis.exception.BadRequestCustom;
import com.fundacionoasis.exception.ConflictException;
import com.fundacionoasis.exception.ErrorException;
import com.fundacionoasis.service.BeneficiaryService;
import com.fundacionoasis.service.GroupBeneficiaryService;
import com.fundacionoasis.validator.BeneficiaryValidator;
import com.fundacionoasis.validator.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/api/beneficiary")
public class BeneficiaryController {

    @Autowired
    private BeneficiaryService beneficiaryService;
    @Autowired
    private GroupBeneficiaryService groupBeneficiaryService;

    @PostMapping("/")
    public ResponseEntity<?> save(@RequestBody Beneficiary beneficiary) throws Exception {
        BeneficiaryValidator.validationAttribute(beneficiary);
        Beneficiary beneficiaryValidated = BeneficiaryValidator.trimAttributes(beneficiary);

        if(beneficiaryService.findByDni(beneficiaryValidated.getDni()).isPresent()){
            throw new ConflictException("already exist a beneficiary with the same dni");
        }

        validations(beneficiaryValidated);

        if(beneficiaryService.save(beneficiaryValidated) == null){
            throw new ErrorException("The user could not be save");
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Beneficiary> update(@RequestBody Beneficiary beneficiary) throws Exception {
        BeneficiaryValidator.validationAttribute(beneficiary);
        Beneficiary beneficiaryValidated = BeneficiaryValidator.trimAttributes(beneficiary);

        if(beneficiaryService.findById(beneficiaryValidated.getId()).isEmpty()){
            throw new ConflictException("The beneficiary to update does not exist");
        }

        validations(beneficiaryValidated);

        Beneficiary response = beneficiaryService.save(beneficiaryValidated);
        if(response == null){
            throw new ErrorException("The beneficiary could not be update");
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PatchMapping("/status")
    public ResponseEntity<?> patch(@RequestParam("id") Long id, @RequestParam("status") Boolean status) throws Exception {

        if(beneficiaryService.findById(id).isEmpty()){
            throw new ConflictException("The beneficiary to update does not exist");
        }
        if(status == null){
            throw new BadRequestCustom("The status is require");
        }

        beneficiaryService.updateStatus(status,id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void validations(Beneficiary beneficiaryValidated) throws BadRequestCustom {
        Validation.validationStringSize(beneficiaryValidated.getDni(), 60, "The beneficiary's dni is to large" );
        Validation.validationStringSize(beneficiaryValidated.getName(), 60, "The beneficiary's name is to large" );
        Validation.validationStringSize(beneficiaryValidated.getLastname(), 60, "The beneficiary's lastname is to large");
        Validation.validationStringSize(beneficiaryValidated.getAddress(), 60, "The beneficiary's address is to large" );

        if(groupBeneficiaryService.findById(beneficiaryValidated.getGroupBeneficiary().getId()).isEmpty()){
            throw new BadRequestCustom("The group benficiary's id not exist");
        }
    }
}
