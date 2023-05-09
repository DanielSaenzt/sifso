package com.fundacionoasis.controller;

import com.fundacionoasis.entity.DocumentType;
import com.fundacionoasis.entity.SexType;
import com.fundacionoasis.service.SexTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/sextype")
@CrossOrigin("*")

public class SexTypeController {
    @Autowired
    private SexTypeService sexTypeService;

    @GetMapping("/listar")
    public ResponseEntity<?> listar(){
        List<SexType> sexTypes = sexTypeService.findAll();
        return new ResponseEntity<>(sexTypes, HttpStatus.OK);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarById(@PathVariable Integer id){
        SexType sexType = sexTypeService.findById(id).orElse(null);
        return new ResponseEntity<>(sexType, HttpStatus.OK);
    }
}
