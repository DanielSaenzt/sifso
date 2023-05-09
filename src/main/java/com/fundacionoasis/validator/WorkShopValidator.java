package com.fundacionoasis.validator;

import com.fundacionoasis.entity.Workshop;
import com.fundacionoasis.exception.BadRequestCustom;

public class WorkShopValidator {

    public static void validationAttribute(Workshop workshop) throws BadRequestCustom {
        Validation.validationAttributePresent(workshop.getTitle(),"El título del taller es requerido.");
        Validation.validationAttributePresent(workshop.getStart(),"La fecha de inicio del taller es requerido.");
        Validation.validationAttributePresent(workshop.getStatus(),"El estado del taller es requerido.");
        Validation.validationAttributePresent(workshop.getTypeWorkshop(),"El tipo de taller es requerido");
        Validation.validationAttributePresent(workshop.getDescription(),"La descripción del taller es requerido");
        Validation.validationAttributePresent(workshop.getStatus(),"El estado del taller es requerido.");
        Validation.validationAttributePresent(workshop.getTypeWorkshop().getId(),"El tipo de taller es requerido.");
        Validation.validationString(workshop.getTitle(),"El título del taller es requerido.");
        Validation.validationString(workshop.getTypeBeneficiary(),"El tipo de beneficiario es requerido.");
        Validation.validationString(workshop.getDescription(),"La descripción del taller es requerido");





        Validation.validationString(workshop.getStatus(),"El estado del taller es requerido.");



    }

    public static Workshop trimAttributes(Workshop workshop){
        workshop.setDescription(workshop.getDescription().trim());
        workshop.setTypeBeneficiary(workshop.getTypeBeneficiary().trim());
        return workshop;
    }

}
