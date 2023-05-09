package com.fundacionoasis.validator;

import com.fundacionoasis.entity.MedicalRecord;
import com.fundacionoasis.exception.BadRequestCustom;

public class MedicalRecordValidator {

    public static void validationAttribute(MedicalRecord medicalRecord) throws BadRequestCustom {
        Validation.validationAttributePresent(medicalRecord.getBeneficiary(),"El beneficiario es requerido para realizar el seguimiento psicologico.");
        Validation.validationAttributePresent(medicalRecord.getBeneficiary().getId(),"El beneficiario es requerido para realizar el seguimiento psicologico.");

        Validation.validationAttributePresent(medicalRecord.getDate(),"La fecha es requerida.");
    }

    public static MedicalRecord trimAttributes(MedicalRecord medicalRecord){
        if(medicalRecord.getDescription_data_pass() != null){
            medicalRecord.setDescription_data_pass(medicalRecord.getDescription_data_pass());
        }
        return medicalRecord;
    }
}
