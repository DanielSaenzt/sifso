package com.fundacionoasis.validator;

import com.fundacionoasis.entity.Planning;
import com.fundacionoasis.entity.User;
import com.fundacionoasis.exception.BadRequestCustom;

public class PlanningValidator {

    public static void validationAttribute(Planning planning) throws BadRequestCustom {
        Validation.validationAttributePresent(planning.getDescription(),"The description is required");
        Validation.validationString(planning.getDescription(),"The description is required");

        Validation.validationAttributePresent(planning.getDate(),"The date is required");

    }

    public static Planning trimAttributes(Planning planning){
        planning.setDescription(planning.getDescription());
        if(planning.getComment() != null){
            planning.setComment(planning.getComment());
        }
        return planning;
    }
}
