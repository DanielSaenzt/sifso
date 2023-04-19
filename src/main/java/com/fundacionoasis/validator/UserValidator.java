package com.fundacionoasis.validator;

import com.fundacionoasis.entity.User;
import com.fundacionoasis.exception.BadRequestCustom;

public class UserValidator {

    public static void validationAttribute(User user) throws BadRequestCustom {
        Validation.validationAttributePresent(user.getName(),"The name is required");
        Validation.validationString(user.getName(),"The name is required");

        Validation.validationAttributePresent(user.getPassword(),"The password is required");
        Validation.validationString(user.getPassword(),"The password is required");

        Validation.validationAttributePresent(user.getEmail(),"The email is required");
        Validation.validationString(user.getEmail(),"The email is required");

        Validation.validationAttributePresent(user.getStatus(),"The status is required");
        Validation.validationAttributePresent(user.getStatus(),"The status is required");
        Validation.validationString(user.getStatus(),"The status is required");
    }

    public static User trimAttributes(User user){
       user.setName(user.getName().trim());
       user.setPassword(user.getPassword().trim());
       user.setEmail(user.getEmail().trim());
       return user;
    }
}
