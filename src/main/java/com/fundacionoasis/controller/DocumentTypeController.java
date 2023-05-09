package com.fundacionoasis.controller;

import com.fundacionoasis.entity.BloodType;
import com.fundacionoasis.entity.DocumentType;
import com.fundacionoasis.service.DocumentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/document")
@CrossOrigin("*")

public class DocumentTypeController {
    @Autowired
    private DocumentTypeService documentTypeService;

    @GetMapping("/listar")
    public ResponseEntity<?> listar(){
        List<DocumentType> documentTypes = documentTypeService.findAll();
        return new ResponseEntity<>(documentTypes, HttpStatus.OK);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarById(@PathVariable Integer id){
        DocumentType documentType = documentTypeService.findById(id).orElse(null);
        return new ResponseEntity<>(documentType, HttpStatus.OK);
    }
}
