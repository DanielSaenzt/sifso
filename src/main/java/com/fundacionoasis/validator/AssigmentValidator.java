package com.fundacionoasis.validator;

import com.fundacionoasis.entity.AssigmentBeneficiaryUser;
import com.fundacionoasis.entity.Beneficiary;
import com.fundacionoasis.exception.BadRequestCustom;

public class AssigmentValidator {

    public static void validationAttribute(AssigmentBeneficiaryUser assigmentBeneficiaryUser) throws BadRequestCustom {
        Validation.validationAttributePresent(assigmentBeneficiaryUser.getBeneficiary(),"The beneficiary is required");
        Validation.validationAttributePresent(assigmentBeneficiaryUser.getBeneficiary().getId(),"The beneficiary is required");

        Validation.validationAttributePresent(assigmentBeneficiaryUser.getWorkshop(),"The workshop is required");
        Validation.validationAttributePresent(assigmentBeneficiaryUser.getWorkshop().getId(),"The workshop is required");

        Validation.validationAttributePresent(assigmentBeneficiaryUser.getUser(),"The user is required");
        Validation.validationAttributePresent(assigmentBeneficiaryUser.getUser().getId(),"The user is required");
    }

}
