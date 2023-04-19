package com.fundacionoasis.validator;

import com.fundacionoasis.entity.Role;
import com.fundacionoasis.exception.BadRequestCustom;

public class RolValidator {

    public static void validationAttribute(Role role) throws BadRequestCustom {
        Validation.validationAttributePresent(role.getDescription(),"The description is required");
        Validation.validationString(role.getDescription(), "The description is required");
    }

    public static Role trimAttributes(Role role){
        role.setDescription(role.getDescription().trim());
        return role;
    }
}
