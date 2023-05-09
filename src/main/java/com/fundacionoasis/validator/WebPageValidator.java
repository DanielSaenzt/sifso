package com.fundacionoasis.validator;

import com.fundacionoasis.entity.*;
import com.fundacionoasis.exception.BadRequestCustom;

public class WebPageValidator {
    public static void validationAttributeHome(HomePage homePage) throws BadRequestCustom {
        Validation.validationAttributePresent(homePage.getTitulo(),"El título es requerido.");
        Validation.validationAttributePresent(homePage.getTituloSecundario(),"El título secundario es requerido.");
        Validation.validationAttributePresent(homePage.getContenido(),"El contenido es requerido.");
        Validation.validationString(homePage.getTitulo(),"El título es requerido.");
        Validation.validationString(homePage.getTituloSecundario(),"El título secundario es requerido.");
        Validation.validationString(homePage.getContenido(),"El contenido es requerido.");


    }
    public static void validationAttributeProgram(Program program) throws BadRequestCustom {
        Validation.validationAttributePresent(program.getTitulo(),"El título es requerido.");
        Validation.validationAttributePresent(program.getDescripcion(),"La descripción es requerida.");
        Validation.validationString(program.getTitulo(),"El título es requerido.");
        Validation.validationString(program.getDescripcion(),"La descripción es requerida.");


    }
    public static void validationAttributeProyectos(Proyectos program) throws BadRequestCustom {
        Validation.validationAttributePresent(program.getTitulo(),"El título es requerido.");
        Validation.validationAttributePresent(program.getDescripcion(),"La descripción es requerida.");
        Validation.validationString(program.getTitulo(),"El título es requerido.");
        Validation.validationString(program.getDescripcion(),"La descripción es requerida.");


    }
    public static void validationAttributeDonaciones(Donaciones donaciones) throws BadRequestCustom {
        Validation.validationAttributePresent(donaciones.getDescripcionVoluntario(),"La descripción del voluntariado es requerido.");
        Validation.validationAttributePresent(donaciones.getDescripcionPlanPadrino(),"La descripción del plan padrino es requerido.");
        Validation.validationString(donaciones.getDescripcionVoluntario(),"a descripción del voluntariado es requerido.");
        Validation.validationString(donaciones.getDescripcionPlanPadrino(),"La descripción del plan padrino es requerido.");


    }
    public static void validationAttributeFotter(Footer footer) throws BadRequestCustom {
        Validation.validationAttributePresent(footer.getDireccion(),"La dirección es requerida.");
        Validation.validationAttributePresent(footer.getTelefono(),"El telefono es requerido.");
        Validation.validationAttributePresent(footer.getCelular(),"El celular es requerido");
        Validation.validationString(footer.getDireccion(),"La dirección es requerida.");
        Validation.validationString(footer.getTelefono(),"El telefono es requerido.");
        Validation.validationString(footer.getCelular(),"El celular es requerido");


    }

    public static Workshop trimAttributes(Workshop workshop){
        workshop.setDescription(workshop.getDescription().trim());
        workshop.setTypeBeneficiary(workshop.getTypeBeneficiary().trim());
        return workshop;
    }
}
