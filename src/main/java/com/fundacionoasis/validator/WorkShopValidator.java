package com.fundacionoasis.validator;

import com.fundacionoasis.entity.Planning;
import com.fundacionoasis.entity.User;
import com.fundacionoasis.entity.Workshop;
import com.fundacionoasis.exception.BadRequestCustom;

public class WorkShopValidator {

    public static void validationAttribute(Workshop workshop) throws BadRequestCustom {
        Validation.validationAttributePresent(workshop.getDescription(),"The description is require");
        Validation.validationString(workshop.getDescription(),"The description is require");
        Validation.validationString(workshop.getTypeBeneficiary(),"The type of beneficiary is require");

        Validation.validationAttributePresent(workshop.getTypeWorkshop(),"The type of beneficiary is require");
        Validation.validationAttributePresent(workshop.getTypeWorkshop().getId(),"The type of beneficiary is require");

        Validation.validationAttributePresent(workshop.getStatus(),"The status is require");
    }

    public static Workshop trimAttributes(Workshop workshop){
        workshop.setDescription(workshop.getDescription().trim());
        workshop.setTypeBeneficiary(workshop.getTypeBeneficiary().trim());
        return workshop;
    }

}
