package com.fundacionoasis.controller;

import com.fundacionoasis.entity.User;
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

@Controller
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @PostMapping("/")
    public ResponseEntity<?> save(@RequestBody User user) throws Exception {
        UserValidator.validationAttribute(user);
        Validation.validationAttributePresent(user.getRol(),"The role is required");
        Validation.validationAttributePresent(user.getRol().getId(),"The role is required");
        User userValidated = UserValidator.trimAttributes(user);

        if(userService.findByEmail(userValidated.getEmail()).isPresent()){
            throw new ConflictException("already exist a user with the same email");
        }

        Validation.validationEmail(user.getEmail());
        validations(userValidated);

        if(userService.save(userValidated) == null){
            throw new ErrorException("The user could not be save");
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<User> update(@RequestBody User user) throws Exception {
        UserValidator.validationAttribute(user);
        Validation.validationAttributePresent(user.getRol(),"The role is required");
        Validation.validationAttributePresent(user.getRol().getId(),"The role is required");

        User userValidated = UserValidator.trimAttributes(user);

        if(userService.findById(userValidated.getId()).isEmpty()){
            throw new ConflictException("The user to update does not exist");
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
            throw new ConflictException("The user to update does not exist");
        }
        if(status == null){
            throw new BadRequestCustom("The status can not be empty");
        }

        userService.updateStatus(status,id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void validations(User userValidated) throws BadRequestCustom {
        Validation.validationStringSize(userValidated.getName(), 60, "The user's name is to large" );
        Validation.validationStringSize(userValidated.getPassword(), 255, "The user's password is to large");
        Validation.validationStringSize(userValidated.getEmail(), 255, "The user's email is to large" );
        Validation.validationStringSize(userValidated.getStatus(), 30, "The user's status is to large" );


        if(roleService.findById(userValidated.getRol().getId()).isEmpty()){
            throw new BadRequestCustom("The role's id of user not exist");
        }
    }
}
