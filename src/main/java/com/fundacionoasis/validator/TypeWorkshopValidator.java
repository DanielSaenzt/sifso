package com.fundacionoasis.validator;

import com.fundacionoasis.entity.TypeWorkshop;
import com.fundacionoasis.exception.BadRequestCustom;

public class TypeWorkshopValidator {
    public static void validationAttribute(TypeWorkshop typeWorkshop) throws BadRequestCustom {
        Validation.validationAttributePresent(typeWorkshop.getDescription(),"The description is required");
        Validation.validationString(typeWorkshop.getDescription(), "The description is required");
    }

    public static TypeWorkshop trimAttributes(TypeWorkshop typeWorkshop){
        typeWorkshop.setDescription(typeWorkshop.getDescription().trim());
        return typeWorkshop;
    }
}
