package com.fundacionoasis.controller;

import com.fundacionoasis.entity.User;
import com.fundacionoasis.entity.Workshop;
import com.fundacionoasis.exception.BadRequestCustom;
import com.fundacionoasis.exception.ConflictException;
import com.fundacionoasis.exception.ErrorException;
import com.fundacionoasis.service.RoleService;
import com.fundacionoasis.service.UserService;
import com.fundacionoasis.validator.UserValidator;
import com.fundacionoasis.validator.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;
    @GetMapping("/listar")
    public ResponseEntity<?> listar(){
        List<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @PostMapping("/")
    public ResponseEntity<?> save(@RequestBody User user) throws Exception {
        UserValidator.validationAttribute(user);
        Validation.validationAttributePresent(user.getRol(),"El rol del usuario es requerido.");
        Validation.validationAttributePresent(user.getRol().getId(),"El rol del usuario es requerido.");
        User userValidated = UserValidator.trimAttributes(user);

        if(userService.findByEmail(userValidated.getEmail()).isPresent()){
            throw new ConflictException("Actualmente existe un usuario con el mismo correo electrónico. Por favor ingrese uno diferente.");
        }

        Validation.validationEmail(user.getEmail());
        validations(userValidated);

        if(userService.save(userValidated) == null){
            throw new ErrorException("The user could not be save");
        }

        return new ResponseEntity<>(userService.save(userValidated),HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<User> update(@RequestBody User user) throws Exception {

        UserValidator.validationAttribute(user);
        Validation.validationAttributePresent(user.getRol(),"El rol del usuario es requerido.");
        Validation.validationAttributePresent(user.getRol().getId(),"El rol del usuario es requerido.");

        User userValidated = UserValidator.trimAttributes(user);

        if(userService.findById(userValidated.getId()).isEmpty()){
            throw new ConflictException("El usuario que intenta actualizar no existe.");
        }

        validations(userValidated);

        User response = userService.save(userValidated);
        if(response == null){
            throw new ErrorException("The user could not be update");
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PatchMapping("/status")
    public ResponseEntity<?> patch(@RequestParam("id") Long id, @RequestParam("status") String status) throws Exception {

        if(userService.findById(id).isEmpty()){
            throw new ConflictException("El usuario no existe.");
        }
        if(status == null){
            throw new BadRequestCustom("The status can not be empty");
        }


        userService.updateStatus(status,id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<?> logIn(@RequestBody User user) throws Exception {
        try {
            Validation.validationEmail(user.getEmail());
            User userLogin = userService.findByEmail(user.getEmail()).orElseThrow(() ->  new BadRequestCustom("Lo sentimos, las credenciales ingresadas no son válidas. Por favor, revisa que la dirección de correo y contraseña sean correctas e inténtalo de nuevo."));
            if (userLogin.getPassword().equals(user.getPassword())) {
                if(userLogin.getStatus().equals("Activo")){
                    return new ResponseEntity<>(userLogin, HttpStatus.OK);
                }
                else {
                    throw new BadRequestCustom("Lo sentimos, parece que tu usuario se encuentra inactivo. Por favor, verifica que tu cuenta esté activa y que hayas ingresado correctamente tus credenciales.");

                }
            } else {
                throw new BadRequestCustom("Lo sentimos, las credenciales ingresadas no son válidas. Por favor, revisa que la dirección de correo y contraseña sean correctas e inténtalo de nuevo.");
            }
        } catch (BadRequestCustom badMessage) {
            return new ResponseEntity<>(badMessage.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    private void validations(User userValidated) throws BadRequestCustom {
        Validation.validationStringSize(userValidated.getName(), 60, "El nombre del usuario es demasiado largo." );
        Validation.validationStringSize(userValidated.getPassword(), 255, "La contraseña del usuario es demasiado larga.");
        Validation.validationStringSize(userValidated.getEmail(), 255, "El correo electrónico del usuario es demasiado largo." );
        Validation.validationStringSize(userValidated.getStatus(), 30, "El estado del usuario es demasiado largo." );


        if(roleService.findById(userValidated.getRol().getId()).isEmpty()){
            throw new BadRequestCustom("The role's id of user not exist");
        }
    }
}
