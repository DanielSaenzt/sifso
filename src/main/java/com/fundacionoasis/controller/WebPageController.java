package com.fundacionoasis.controller;

import com.fundacionoasis.entity.*;
import com.fundacionoasis.exception.ConflictException;
import com.fundacionoasis.exception.ErrorException;
import com.fundacionoasis.service.WebPageService;
import com.fundacionoasis.validator.Validation;
import com.fundacionoasis.validator.WebPageValidator;
import com.fundacionoasis.validator.WorkShopValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/webpage")
@CrossOrigin("*")

public class WebPageController {

    @Autowired
    private WebPageService webPageService;

    @GetMapping("/listarhome")
    public ResponseEntity<?> listarHome(){
        List<HomePage> users = webPageService.getAllHomes();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @GetMapping("/listarprogram")
    public ResponseEntity<?> listarProgram(){
        List<Program> users = webPageService.getAllProgram();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @GetMapping("/listarproyectos")
    public ResponseEntity<?> listarProyectos(){
        List<Proyectos> users = webPageService.getAllProyectos();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @GetMapping("/listardonaciones")
    public ResponseEntity<?> listarDonaciones(){
        List<Donaciones> users = webPageService.getAllDonaciones();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @GetMapping("/listarfooter")
    public ResponseEntity<?> listarFooter(){
        List<Footer> users = webPageService.getAllFooter();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping ("/savehome")
    public ResponseEntity<?> saveHome(@RequestBody HomePage homePage) throws Exception{
        WebPageValidator.validationAttributeHome(homePage);

        if(webPageService.findByIdHome(homePage.getId()).isEmpty()){
            throw new ConflictException("No existe  el registro.");
        }

        Validation.validationStringSize(homePage.getTitulo(), 100, "El título supera la cantidad de caracteres maxima permitida.");
        Validation.validationStringSize(homePage.getTituloSecundario(), 100, "El título secundario supera la cantidad de caracteres maxima permitida." );
        Validation.validationStringSize(homePage.getContenido(), 2000, "El contenido supera la cantidad de caracteres maxima permitida.");


        HomePage response = webPageService.saveHomes(homePage);
        if(response == null){
            throw new ErrorException("The workshop could not be save");
        }
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }
    @PutMapping ("/saveprogram")
    public ResponseEntity<?> saveProgram(@RequestBody Program program) throws Exception{
        WebPageValidator.validationAttributeProgram(program);

        if(webPageService.findByIdProgram(program.getId()).isEmpty()){
            throw new ConflictException("No existe  el registro.");
        }

        Validation.validationStringSize(program.getTitulo(), 100, "El título supera la cantidad de caracteres maxima permitida.");
        Validation.validationStringSize(program.getImagen(), 100, "El nombre de la imagen  supera la cantidad de caracteres maxima permitida." );
        Validation.validationStringSize(program.getDescripcion(), 2000, "La descripción supera la cantidad de caracteres maxima permitida.");


        Program response = webPageService.saveProgram(program);
        if(response == null){
            throw new ErrorException("The workshop could not be save");
        }
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }
    @PutMapping ("/saveproyecto")
    public ResponseEntity<?> saveProyecto(@RequestBody Proyectos proyectos) throws Exception{
        WebPageValidator.validationAttributeProyectos(proyectos);

        if(webPageService.findByIdProyectos(proyectos.getId()).isEmpty()){
            throw new ConflictException("No existe  el registro.");
        }

        Validation.validationStringSize(proyectos.getTitulo(), 100, "El título supera la cantidad de caracteres maxima permitida.");
        Validation.validationStringSize(proyectos.getImagen(), 100, "El nombre de la imagen  supera la cantidad de caracteres maxima permitida." );
        Validation.validationStringSize(proyectos.getDescripcion(), 2000, "La descripción supera la cantidad de caracteres maxima permitida.");


        Proyectos response = webPageService.saveProyectos(proyectos);
        if(response == null){
            throw new ErrorException("The workshop could not be save");
        }
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }
    @PutMapping ("/savedonaciones")
    public ResponseEntity<?> saveDonaciones(@RequestBody Donaciones donaciones) throws Exception{
        WebPageValidator.validationAttributeDonaciones(donaciones);
        if(webPageService.findByIdDONACIONES(donaciones.getId()).isEmpty()){
            throw new ConflictException("No existe  el registro.");
        }

        Validation.validationStringSize(donaciones.getLinknequi(), 100, "El link de nequi supera la cantidad de caracteres maxima permitida.");
        Validation.validationStringSize(donaciones.getLinkdaviplata(), 100, "El link de davi plata supera la cantidad de caracteres maxima permitida." );
        Validation.validationStringSize(donaciones.getLinkpaypal(), 100, "El link de paypal supera la cantidad de caracteres maxima permitida.");
        Validation.validationStringSize(donaciones.getDescripcionPlanPadrino(), 2000, "La descripción del plan padrino supera la cantidad de caracteres maxima permitida.");

        Validation.validationStringSize(donaciones.getDescripcionVoluntario(), 2000, "La descripción del voluntariado supera la cantidad de caracteres maxima permitida.");
        Validation.validationStringSize(donaciones.getImagenVoluntario(), 100, "La imagen supera la cantidad de caracteres maxima permitida.");


        Donaciones response = webPageService.saveDonaciones(donaciones);
        if(response == null){
            throw new ErrorException("The workshop could not be save");
        }
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }
    @PutMapping ("/savefooter")
    public ResponseEntity<?> saveFooter(@RequestBody Footer footer) throws Exception{
        WebPageValidator.validationAttributeFotter(footer);

        if(webPageService.findByIdFotter(footer.getId()).isEmpty()){
            throw new ConflictException("No existe  el registro.");
        }

        Validation.validationStringSize(footer.getDireccion(), 100, "La dirección supera la cantidad de caracteres maxima permitida.");
        Validation.validationStringSize(footer.getTelefono(), 100, "El telefono de la imagen  supera la cantidad de caracteres maxima permitida." );
        Validation.validationStringSize(footer.getCelular(), 30, "El celular supera la cantidad de caracteres maxima permitida.");
        Validation.validationStringSize(footer.getWhatsapp(), 30, "El link de WhatsApp supera la cantidad de caracteres maxima permitida.");




        Footer response = webPageService.saveFooter(footer);
        if(response == null){
            throw new ErrorException("The workshop could not be save");
        }
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }
}
