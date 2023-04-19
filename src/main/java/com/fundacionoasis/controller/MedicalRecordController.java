package com.fundacionoasis.controller;

import com.fundacionoasis.entity.MedicalRecord;
import com.fundacionoasis.entity.User;
import com.fundacionoasis.exception.BadRequestCustom;
import com.fundacionoasis.exception.ConflictException;
import com.fundacionoasis.exception.ErrorException;
import com.fundacionoasis.service.BeneficiaryService;
import com.fundacionoasis.service.MedicalRecordService;
import com.fundacionoasis.service.RoleService;
import com.fundacionoasis.service.UserService;
import com.fundacionoasis.validator.MedicalRecordValidator;
import com.fundacionoasis.validator.UserValidator;
import com.fundacionoasis.validator.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/api/medical")
public class MedicalRecordController {

    @Autowired
    private BeneficiaryService beneficiaryService;

    @Autowired
    private MedicalRecordService medicalRecordService;

    @PostMapping("/")
    public ResponseEntity<?> save(@RequestBody MedicalRecord medicalRecord) throws Exception {
        MedicalRecordValidator.validationAttribute(medicalRecord);
        MedicalRecord medicalRecordValidated = MedicalRecordValidator.trimAttributes(medicalRecord);

        Validation.validationStringSize(medicalRecordValidated.getDescription(), 60, "The medical record's description is to large" );

        if(beneficiaryService.findById(medicalRecordValidated.getBeneficiary().getId()).isEmpty()){
            throw new BadRequestCustom("The medical record's beneficiary not exist");
        }

        if(medicalRecordService.save(medicalRecordValidated) == null){
            throw new ErrorException("The user could not be save");
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<User> update(@RequestBody MedicalRecord medicalRecord) throws Exception {
        MedicalRecordValidator.validationAttribute(medicalRecord);
        Validation.validationAttributePresent(medicalRecord.getDescription(), "The description is required");
        Validation.validationString(medicalRecord.getDescription(), "The description is required");
        Validation.validationStringSize(medicalRecord.getDescription(),60, "");

        Validation.validationAttributePresent(medicalRecord.getStatus(),"The status is required");

        MedicalRecord medicalRecordValidated = MedicalRecordValidator.trimAttributes(medicalRecord);

        if(beneficiaryService.findById(medicalRecordValidated.getBeneficiary().getId()).isEmpty()){
            throw new BadRequestCustom("The medical record's beneficiary not exist");
        }

        if(medicalRecordService.save(medicalRecordValidated) == null){
            throw new ErrorException("The user could not be save");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/status")
    public ResponseEntity<?> patch(@RequestParam("id") Long id, @RequestParam("status") Boolean status) throws Exception {

        if(medicalRecordService.findById(id).isEmpty()){
            throw new ConflictException("The medical record to update does not exist");
        }
        if(status == null){
            throw new BadRequestCustom("The status can not be empty");
        }

        medicalRecordService.updateStatus(status,id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
