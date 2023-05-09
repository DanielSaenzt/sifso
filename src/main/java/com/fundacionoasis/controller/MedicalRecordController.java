package com.fundacionoasis.controller;

import com.fundacionoasis.entity.BloodType;
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

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/medical")
@CrossOrigin("*")

public class MedicalRecordController {

    @Autowired
    private BeneficiaryService beneficiaryService;

    @Autowired
    private MedicalRecordService medicalRecordService;
    @GetMapping("/listar")
    public ResponseEntity<?> listar(){
        List<MedicalRecord> bloodTypes = medicalRecordService.findAll();
        return new ResponseEntity<>(bloodTypes, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> save(@RequestBody MedicalRecord medicalRecord) throws Exception {

        try{

        MedicalRecordValidator.validationAttribute(medicalRecord);
        MedicalRecord medicalRecordValidated = MedicalRecordValidator.trimAttributes(medicalRecord);

        Validation.validationStringSize(medicalRecordValidated.getDescription_data_pass(), 65535, "La información de reporte pscologico supera la cantidad máxima de caracteres permitida." );

        if(beneficiaryService.findById(medicalRecordValidated.getBeneficiary().getId()).isEmpty()){
            throw new BadRequestCustom("El beneficiario al que intenta hacer un seguimiento psicologico no existe.");
        }

        if(medicalRecordService.save(medicalRecordValidated) == null){
            throw new ErrorException("The user could not be save");
        }

        } catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<User> update(@RequestBody MedicalRecord medicalRecord) throws Exception {
        MedicalRecordValidator.validationAttribute(medicalRecord);

        Validation.validationStringSize(medicalRecord.getDescription_data_pass(),65535, "");

        Validation.validationAttributePresent(medicalRecord.getStatus(),"El estatus es requerido.");

        MedicalRecord medicalRecordValidated = MedicalRecordValidator.trimAttributes(medicalRecord);

        if(beneficiaryService.findById(medicalRecordValidated.getBeneficiary().getId()).isEmpty()){
            throw new BadRequestCustom("El reporte psicológico del beneficiario al que intenta acceder no existe. ");
        }

        if(medicalRecordService.save(medicalRecordValidated) == null){
            throw new ErrorException("The user could not be save");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/status")
    public ResponseEntity<?> patch(@RequestParam("id") Long id, @RequestParam("status") String status) throws Exception {

        if(medicalRecordService.findById(id).isEmpty()){
            throw new ConflictException("El reporte psícologico no se puedo actualizar.");
        }
        if(status == null){
            throw new BadRequestCustom("El status no puede estar vacio.");
        }

        medicalRecordService.updateStatus(status,id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
