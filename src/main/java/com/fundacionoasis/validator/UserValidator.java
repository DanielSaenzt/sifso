package com.fundacionoasis.validator;

import com.fundacionoasis.entity.User;
import com.fundacionoasis.exception.BadRequestCustom;

public class UserValidator {

    public static void validationAttribute(User user) throws BadRequestCustom {
        Validation.validationAttributePresent(user.getName(), "El nombre es requerido.");
        Validation.validationString(user.getName(), "El nombre es requerido.");

        Validation.validationAttributePresent(user.getPassword(), "La contraseña es requerida.");
        Validation.validationString(user.getPassword(), "La contraseña es requerida.");

        Validation.validationAttributePresent(user.getEmail(), "El correo electrónico es requerido.");
        Validation.validationString(user.getEmail(), "El correo electrónico es requerido.");

        Validation.validationAttributePresent(user.getStatus(), "El estado es requerido.");
        Validation.validationString(user.getStatus(), "El estado es requerido.");
    }

    public static User trimAttributes(User user){
       user.setName(user.getName().trim());
       user.setPassword(user.getPassword().trim());
       user.setEmail(user.getEmail().trim());
       return user;
    }
}
