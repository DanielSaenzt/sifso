package com.fundacionoasis.controller;

import com.fundacionoasis.entity.AttendanceReport;
import com.fundacionoasis.exception.BadRequestCustom;
import com.fundacionoasis.service.AttendenceService;
import com.fundacionoasis.validator.AttendeceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/attedence")
@CrossOrigin("*")

public class AttendenceController {
    @Autowired
    private AttendenceService attendenceService;

    @GetMapping("/listar")
    public ResponseEntity<?> listar(){
        List<AttendanceReport> attendanceReports = attendenceService.findAll();
        return new ResponseEntity<>(attendanceReports, HttpStatus.OK);
    }



    @PostMapping("/")
    public ResponseEntity<?> save(@RequestBody AttendanceReport attendanceReport) throws BadRequestCustom {
        try{
            AttendeceValidator.validationAttribute(attendanceReport);
            attendenceService.save(attendanceReport);

        }
        catch (BadRequestCustom badRequestCustom){
            return new ResponseEntity<>(badRequestCustom.getMessage(),HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarById(@PathVariable Long id){
        AttendanceReport attendanceReport = attendenceService.findById(id).orElse(null);
        return new ResponseEntity<>(attendanceReport, HttpStatus.OK);
    }
}
