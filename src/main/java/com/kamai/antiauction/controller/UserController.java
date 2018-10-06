package com.kamai.antiauction.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.kamai.antiauction.model.User;
import com.kamai.antiauction.model.transfer.validation.UserValidation;
import com.kamai.antiauction.model.transfer.view.View;
import com.kamai.antiauction.service.UserService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
@Log4j
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        log.info("Class initialized");

        this.userService = userService;
    }

    @JsonView(View.FullView.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(
            @PathVariable("id") Long id
    ) {

        log.info("GET /");

        User user = new User();
        user.setId(3L);
        user.setName("name");
        user.setLogin("login");
        user.setEmail("email");
        user.setPassword("passwd");
        user.setSurname("surname");


        if (user == null) {
            log.info("User with id=" + id + " does not exist");
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @JsonView(View.FullView.class)
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<User> createUser(
            @Validated(UserValidation.New.class)
            @RequestBody User user
    ) {

        log.info("POST /");

        User savedUser = userService.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

    @JsonView(View.FullView.class)
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(
            @Validated(UserValidation.UpdateMainUserInformation.class)
            @RequestBody User user
    ) {

        log.info("PUT /");

        User updatedUser = userService.updateUser(user);

        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteUser(
            @PathVariable("id") Long id
    ) {

        userService.deleteUser(id);

    }
}


