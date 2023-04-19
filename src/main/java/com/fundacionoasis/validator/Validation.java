package com.fundacionoasis.validator;

import com.fundacionoasis.exception.BadRequestCustom;

public class Validation {
    public static void validationStringSize(String string, Integer allowSize, String messageError) throws BadRequestCustom {
        if(string.length() > allowSize){
            throw new BadRequestCustom(messageError);
        }
    }

    public static void validationEmail(String email) throws BadRequestCustom {
        if(!email.contains("@") || email.contains(" ") || !(email.contains(".es") || email.contains(".co") || email.contains(".com") )){
            throw new BadRequestCustom("the email is not valid");
        }
    }

    public static void validationAttributePresent(Object attribute, String messageError) throws BadRequestCustom {
        if(attribute == null){
            throw new BadRequestCustom(messageError);
        }
    }

    public static void validationString(String string, String messageError) throws BadRequestCustom {
        if(string.trim() == ""){
            throw new BadRequestCustom(messageError);
        }
    }

}
