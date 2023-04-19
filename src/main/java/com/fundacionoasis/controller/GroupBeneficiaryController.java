package com.fundacionoasis.controller;

import com.fundacionoasis.entity.GroupBeneficiary;
import com.fundacionoasis.entity.Role;
import com.fundacionoasis.exception.ConflictException;
import com.fundacionoasis.exception.ErrorException;
import com.fundacionoasis.repository.IGroupBeneficiaryRepository;
import com.fundacionoasis.service.GroupBeneficiaryService;
import com.fundacionoasis.service.RoleService;
import com.fundacionoasis.validator.GroupBeneficiaryValidator;
import com.fundacionoasis.validator.RolValidator;
import com.fundacionoasis.validator.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/api/group")
public class GroupBeneficiaryController {
    @Autowired
    private GroupBeneficiaryService groupBeneficiaryService;

    @PostMapping("/")
    public ResponseEntity<?> save(@RequestBody GroupBeneficiary groupBeneficiary) throws Exception {
        GroupBeneficiaryValidator.validationAttribute(groupBeneficiary);
        GroupBeneficiary groupBeneficiaryValidated = GroupBeneficiaryValidator.trimAttributes(groupBeneficiary);

        if(groupBeneficiaryService.findByDescription(groupBeneficiaryValidated.getDescription()).isPresent()){
            throw new ConflictException("already exist a group of beneficiary with the same description");
        }

        Validation.validationStringSize(groupBeneficiaryValidated.getDescription(), 120, "The group of beneficiary to save is to large" );

        if(groupBeneficiaryService.save(groupBeneficiaryValidated) == null){
            throw new ErrorException("The group of beneficiary could not be save");
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id){
        groupBeneficiaryService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
