package com.fundacionoasis.validator;

import com.fundacionoasis.entity.Beneficiary;
import com.fundacionoasis.entity.User;
import com.fundacionoasis.exception.BadRequestCustom;

public class BeneficiaryValidator {

    public static void validationAttribute(Beneficiary beneficiary) throws BadRequestCustom {
        Validation.validationAttributePresent(beneficiary.getDni(),"El número de identificación del beneficiario es necesario.");
        Validation.validationString(beneficiary.getDni(),"El número de identificación del beneficiario es necesario.");

        Validation.validationAttributePresent(beneficiary.getName(),"El nombre del beneficiario es necesario.");
        Validation.validationString(beneficiary.getName(),"El nombre del beneficiario es necesario.");

        Validation.validationAttributePresent(beneficiary.getLastname(),"El apellido del beneficiario es necesario.");
        Validation.validationString(beneficiary.getLastname(),"El apellido del beneficiario es necesario.");

        Validation.validationAttributePresent(beneficiary.getAddress(),"La dirección de beneficiario es necesario");
        Validation.validationString(beneficiary.getAddress(),"La dirección de beneficiario es necesario");

        Validation.validationAttributePresent(beneficiary.getAge(),"La edad del beneficiario es requerida:");

        Validation.validationAttributePresent(beneficiary.getEps(),"La Eps del beneficiario es necesaria.");
        Validation.validationString(beneficiary.getEps(),"La Eps del beneficiario es necesaria.");
        Validation.validationAttributePresent(beneficiary.getCell_phone_number(),"En número del beneficiario es requerido.");
        Validation.validationString(beneficiary.getCell_phone_number(),"En número del beneficiario es requerido.");


        Validation.validationAttributePresent(beneficiary.getSisben_score(),"El puntaje de  Sisben es necesario.");
        Validation.validationString(beneficiary.getSisben_score(),"El puntaje de  Sisben es necesario.");

        Validation.validationAttributePresent(beneficiary.getGroupBeneficiary(),"El grupo al que pertenece el beneficiario es requierido.");
        Validation.validationAttributePresent(beneficiary.getGroupBeneficiary().getId(),"El id del grupo al que pertenece el beneficiario es requierido");

        Validation.validationAttributePresent(beneficiary.getBloodType(),"El tipo de sangre del beneficiario es requerido.");
        Validation.validationAttributePresent(beneficiary.getBloodType().getId(),"El id del tipo de sangre del beneficiario es requerido.");

        Validation.validationAttributePresent(beneficiary.getDocumentType(),"El tipo del documento de beneficiario es requerido.");
        Validation.validationAttributePresent(beneficiary.getDocumentType().getId(),"El id del tipo del documento de beneficiario es requerido.");

        Validation.validationAttributePresent(beneficiary.getSexType(),"El tipo de genero del beneficiario es requerido.");
        Validation.validationAttributePresent(beneficiary.getSexType().getId(),"El id del tipo de genero del beneficiario es requerido.");

        Validation.validationAttributePresent(beneficiary.getStatus(),"El estado es requerido.");
    }

    public static Beneficiary trimAttributes(Beneficiary beneficiary){
        beneficiary.setDni(beneficiary.getDni().trim());
        beneficiary.setName(beneficiary.getName().trim());
        beneficiary.setLastname(beneficiary.getLastname().trim());
        beneficiary.setAddress(beneficiary.getAddress().trim());
        return beneficiary;
    }
}
