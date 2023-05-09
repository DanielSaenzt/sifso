package com.fundacionoasis.validator;

import com.fundacionoasis.entity.AttendanceReport;
import com.fundacionoasis.exception.BadRequestCustom;

public class AttendeceValidator {
    public static void validationAttribute(AttendanceReport attendanceReport) throws BadRequestCustom {


        Validation.validationAttributePresent(attendanceReport.getWorkshop(), "El taller es necesario para tomar la asistencia.");
        Validation.validationAttributePresent(attendanceReport.getWorkshop().getId(), "El id del taller es necesario para tomar la asistencia");

        Validation.validationAttributePresent(attendanceReport.getBeneficiary(), "El beneficiaro es necesario para tomar la asistencia.");
        Validation.validationAttributePresent(attendanceReport.getBeneficiary().getId(), "El id del beneficiaro es necesario para tomar la asistencia.");

    }
}
