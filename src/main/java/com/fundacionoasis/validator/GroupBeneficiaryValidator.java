package com.fundacionoasis.validator;

import com.fundacionoasis.entity.GroupBeneficiary;
import com.fundacionoasis.exception.BadRequestCustom;

public class GroupBeneficiaryValidator {

    public static void validationAttribute(GroupBeneficiary groupBeneficiary) throws BadRequestCustom {
        Validation.validationAttributePresent(groupBeneficiary.getDescription(),"The group of beneficiary is required");
        Validation.validationString(groupBeneficiary.getDescription(), "The group of beneficiary is required");
    }

    public static GroupBeneficiary trimAttributes(GroupBeneficiary groupBeneficiary){
        groupBeneficiary.setDescription(groupBeneficiary.getDescription().trim());
        return groupBeneficiary;
    }
}
