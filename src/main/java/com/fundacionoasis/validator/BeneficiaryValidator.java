package com.fundacionoasis.validator;

import com.fundacionoasis.entity.Beneficiary;
import com.fundacionoasis.entity.User;
import com.fundacionoasis.exception.BadRequestCustom;

public class BeneficiaryValidator {

    public static void validationAttribute(Beneficiary beneficiary) throws BadRequestCustom {
        Validation.validationAttributePresent(beneficiary.getDni(),"The dni is required");
        Validation.validationString(beneficiary.getDni(),"The dni is required");

        Validation.validationAttributePresent(beneficiary.getName(),"The name is required");
        Validation.validationString(beneficiary.getName(),"The name is required");

        Validation.validationAttributePresent(beneficiary.getLastname(),"The last name is required");
        Validation.validationString(beneficiary.getLastname(),"The last name is required");

        Validation.validationAttributePresent(beneficiary.getAddress(),"The address is required");
        Validation.validationString(beneficiary.getAddress(),"The address is required");

        Validation.validationAttributePresent(beneficiary.getAge(),"The age is required");

        Validation.validationAttributePresent(beneficiary.getGroupBeneficiary(),"The group of beneficiary is required");
        Validation.validationAttributePresent(beneficiary.getGroupBeneficiary().getId(),"The id of group of beneficiary is required");

        Validation.validationAttributePresent(beneficiary.getStatus(),"The status is required");
    }

    public static Beneficiary trimAttributes(Beneficiary beneficiary){
        beneficiary.setDni(beneficiary.getDni().trim());
        beneficiary.setName(beneficiary.getName().trim());
        beneficiary.setLastname(beneficiary.getLastname().trim());
        beneficiary.setAddress(beneficiary.getAddress().trim());
        return beneficiary;
    }
}
