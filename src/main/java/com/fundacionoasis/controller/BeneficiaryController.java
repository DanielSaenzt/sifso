package com.fundacionoasis.controller;

import com.fundacionoasis.entity.Beneficiary;
import com.fundacionoasis.entity.BloodType;
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

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/beneficiary")
@CrossOrigin("*")

public class BeneficiaryController {

    @Autowired
    private BeneficiaryService beneficiaryService;
    @Autowired
    private GroupBeneficiaryService groupBeneficiaryService;

    @GetMapping("/listar")
    public ResponseEntity<?> listar(){
        List<Beneficiary> beneficiaries = beneficiaryService.findAll();
        return new ResponseEntity<>(beneficiaries, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> save(@RequestBody Beneficiary beneficiary) throws Exception {
        try{

        BeneficiaryValidator.validationAttribute(beneficiary);
        Beneficiary beneficiaryValidated = BeneficiaryValidator.trimAttributes(beneficiary);


        if(beneficiaryService.findByDni(beneficiaryValidated.getDni()).isPresent()){
            throw new ConflictException("Ya existe en el sistema un beneficiario registrado con el mismo número de identificación.");
        }

        validations(beneficiaryValidated);

        if(beneficiaryService.save(beneficiaryValidated) == null){
            throw new ErrorException("The user could not be save");
        }
        } catch (Exception badRequestCustom){
            return new ResponseEntity<>(badRequestCustom.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Beneficiary> update(@RequestBody Beneficiary beneficiary) throws Exception {
        BeneficiaryValidator.validationAttribute(beneficiary);
        Beneficiary beneficiaryValidated = BeneficiaryValidator.trimAttributes(beneficiary);

        if(beneficiaryService.findById(beneficiaryValidated.getId()).isEmpty()){
            throw new ConflictException("El beneficiario que intenta actualizar no existe.");
        }

        validations(beneficiaryValidated);

        Beneficiary response = beneficiaryService.save(beneficiaryValidated);
        if(response == null){
            throw new ErrorException("No se pudo actualizar el beneficiario.");
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PatchMapping("/status")
    public ResponseEntity<?> patch(@RequestParam("id") Long id, @RequestParam("status") String status) throws Exception {

        if(beneficiaryService.findById(id).isEmpty()){
            throw new ConflictException("El beneficiario que intenta actualizar no existe.");
        }
        if(status == null){
            throw new BadRequestCustom("El estatus es requerido.");
        }

        beneficiaryService.updateStatus(status,id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void validations(Beneficiary beneficiaryValidated) throws BadRequestCustom {

        Validation.validationStringSize(beneficiaryValidated.getDni(), 60, "El número de identificación del beneficiario esta muy largo." );
        Validation.validationStringSize(beneficiaryValidated.getName(), 60, "El nombre del beneficiario esta muy largo." );
        Validation.validationStringSize(beneficiaryValidated.getLastname(), 60, "El apellido del beneficiario esta muy largo.");
        Validation.validationStringSize(beneficiaryValidated.getAddress(), 60, "La dirección del beneficiario esta muy largo." );
        if(beneficiaryValidated.getDiseases()!=null){
            Validation.validationStringSize(beneficiaryValidated.getDiseases(), 500, "El campo enfermedades es demasiado largo." );

        }
        Validation.validationStringSize(beneficiaryValidated.getEps(), 60, "El campo Eps es demasiado largo." );
        Validation.validationStringSize(beneficiaryValidated.getSisben_score(), 60, "El campo Puntaje Sisben es demasiado largo." );
        Validation.validationStringSize(beneficiaryValidated.getCell_phone_number(), 15, "El número de celular del beneficiario es demasiado largo." );
        if(beneficiaryValidated.getRelationship_pre_registering()!=null){
            Validation.validationStringSize(beneficiaryValidated.getRelationship_pre_registering(), 60, "El campo relación del beneficiario es demasiado largo." );

        }






        if(groupBeneficiaryService.findById(beneficiaryValidated.getGroupBeneficiary().getId()).isEmpty()){
            throw new BadRequestCustom("The group benficiary's id not exist");
        }
    }
}
