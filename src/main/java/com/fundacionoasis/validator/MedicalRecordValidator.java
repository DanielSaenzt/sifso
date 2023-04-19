package com.fundacionoasis.validator;

import com.fundacionoasis.entity.MedicalRecord;
import com.fundacionoasis.exception.BadRequestCustom;

public class MedicalRecordValidator {

    public static void validationAttribute(MedicalRecord medicalRecord) throws BadRequestCustom {
        Validation.validationAttributePresent(medicalRecord.getBeneficiary(),"The beneficiary is required");
        Validation.validationAttributePresent(medicalRecord.getBeneficiary().getId(),"The beneficiary is required");

        Validation.validationAttributePresent(medicalRecord.getDate(),"The date is required");
    }

    public static MedicalRecord trimAttributes(MedicalRecord medicalRecord){
        if(medicalRecord.getDescription() != null){
            medicalRecord.setDescription(medicalRecord.getDescription());
        }
        return medicalRecord;
    }
}
